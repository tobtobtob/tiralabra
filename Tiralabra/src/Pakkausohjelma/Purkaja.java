
package Pakkausohjelma;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class Purkaja {
    
    private HashMap<Character, Node> sanakirja;
    
    private FileWriter fw;
    private byte puskuri;
    private int indeksi;
    private HuffmanKoodaaja huff;

    public Purkaja() {
        huff = new HuffmanKoodaaja();
    }

    public void setSanakirja(HashMap<Character, Node> sanakirja) {
        this.sanakirja = sanakirja;
    }

    
    public void puraTiedosto(String tiedosto, String uusinimi, String aakkosto) throws FileNotFoundException, IOException{
        FileInputStream s = new FileInputStream(new File(tiedosto));
        Node node;
        sanakirja = luePuut(aakkosto, '¤');
        char edellinen = Character.toChars(s.read())[0];
        
        puskuri =  (byte) s.read();
        indeksi = 1 << 7;
        fw = new FileWriter(new File(uusinimi));
         fw.write(edellinen);
        
        while(s.available()> 0){
           node = sanakirja.get(edellinen);
           while(node.oikea != null){
               if(indeksi<0){
                   puskuri = (byte) s.read();
                   indeksi = 7;
               }
               if(((1<< indeksi) & puskuri) == 0){
                   node = node.oikea;                   
               }
               else{
                   node = node.vasen;                   
               }
               indeksi--;
               
           }
           fw.write(node.merkki);
           edellinen = node.merkki;
            
        }
        fw.close();
    }
    public HashMap<Character, Node> luePuut(String aakkosto,char tyhjaNode) throws FileNotFoundException{
        HashMap<Character, Node> puut= new HashMap<>();
        Scanner s = new Scanner(new File(aakkosto));
        s.useDelimiter("");
        while(s.hasNext()){
            if(!s.hasNext()){
                break;
            }
            char c = s.next().charAt(0);
            Node n = new Node(s.next().charAt(0), 0);
            puut.put(c, n);
            Queue<Node> jono = new LinkedList<>();
            jono.add(n);
            while(!jono.isEmpty()){
                Node node = jono.poll();
                if(node.merkki == tyhjaNode){
                    Node vasen = new Node(s.next().charAt(0), 0);
                    Node oikea = new Node(s.next().charAt(0), 0);
                    node.oikea = oikea;
                    node.vasen = vasen;
                    jono.add(vasen);
                    jono.add(oikea);
                }
            }
            
        }
        return puut;
    }
//    public HashMap<Character, Integer> lueAakkosto(String aakkosto) throws FileNotFoundException{
//        HashMap<Character, Integer> aakkostoTaulu = new HashMap<Character, Integer>();
//        Scanner s = new Scanner(new File(aakkosto));
//        String kaikki = "";
//        while(s.hasNext()){
//            kaikki += s.nextLine();
//        }
//        String[] taulu1 = kaikki.split("@");
//        for (String string : taulu1) {
//            if("".equals(string)){
//                continue;
//            }
//            String[] taulu2 = string.split("#");
//            
//            if("".equals(taulu2[0])){
//               
//                aakkostoTaulu.put('\n', Integer.parseInt(taulu2[1]));
//                continue;
//            }
//            aakkostoTaulu.put(taulu2[0].charAt(0), Integer.parseInt(taulu2[1]));
//        }
//        return aakkostoTaulu;
//    }
}
