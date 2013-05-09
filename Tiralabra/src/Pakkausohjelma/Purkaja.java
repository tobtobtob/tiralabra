
package Pakkausohjelma;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;



public class Purkaja {
    
    private Node sanakirja;
    
    private FileWriter fw;
    private byte puskuri;
    private int indeksi;
    private HuffmanKoodaaja huff;

    public Purkaja() {
        huff = new HuffmanKoodaaja();
    }

    
    public void puraTiedosto(String tiedosto, String uusinimi, String aakkosto) throws FileNotFoundException, IOException{
        FileInputStream s = new FileInputStream(new File(tiedosto));
        Node node;
        HashMap<Character, Integer> aakkostoTaulu = lueAakkosto(aakkosto);
        sanakirja = huff.rakennaPuu(aakkostoTaulu);
        puskuri =  (byte) s.read();
        indeksi = (int) Math.pow(2, 7);
        fw = new FileWriter(new File(uusinimi));
        
        while(s.available()> 0){
           node = sanakirja;
           while(node.oikea != null){
               if(indeksi<0){
                   puskuri = (byte) s.read();
                   indeksi = 7;
               }
               if(((int)Math.pow(2, indeksi) & puskuri) == 0){
                   node = node.oikea;                   
               }
               else{
                   node = node.vasen;                   
               }
               indeksi--;
               
           }
           fw.write(node.merkki);
            
        }
        fw.close();
    }
    public HashMap<Character, Integer> lueAakkosto(String aakkosto) throws FileNotFoundException{
        HashMap<Character, Integer> aakkostoTaulu = new HashMap<Character, Integer>();
        Scanner s = new Scanner(new File(aakkosto));
        String kaikki = "";
        while(s.hasNext()){
            kaikki += s.nextLine();
        }
        String[] taulu1 = kaikki.split("@");
        for (String string : taulu1) {
            if("".equals(string)){
                continue;
            }
            String[] taulu2 = string.split("#");
            
            if("".equals(taulu2[0])){
               
                aakkostoTaulu.put('\n', Integer.parseInt(taulu2[1]));
                continue;
            }
            aakkostoTaulu.put(taulu2[0].charAt(0), Integer.parseInt(taulu2[1]));
        }
        return aakkostoTaulu;
    }
}
