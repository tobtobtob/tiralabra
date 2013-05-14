
package Pakkausohjelma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pakkaaja {
    
    FileOutputStream fos;
    String tiedostonNimi;
    HashMap<Character, HashMap<Character, String>> sanakirja;
    byte puskuri;
    int indeksi;
    HuffmanKoodaaja huff;
    HashMap<Character, HashMap<Character, Integer>> aakkosto;
    HashMap<Character, Node> puut;

    public Pakkaaja() {
        huff = new HuffmanKoodaaja();
    }

    public HashMap<Character, Node> getPuut() {
        return puut;
    }
    
    /**
     * Pakkaajan päämetodi, joka alustaa oliomuuttujat, ja kutsuu muut metodit.
     * @param vanhaNimi
     * @param uusiNimi
     * @throws FileNotFoundException 
     */
    public void pakkaaTiedosto(String vanhaNimi, String uusiNimi) throws FileNotFoundException{
        fos = new FileOutputStream(uusiNimi);
        tiedostonNimi = uusiNimi;
        HashMap<Character, HashMap<Character, Integer>> aakkosto = lueTiedosto(vanhaNimi); 
        puut = rakennaPuut();
        
        sanakirja = new HashMap<>();
        for (Character c : puut.keySet()) {
            sanakirja.put(c, new HashMap<Character, String>());
            luoSanakirja(puut.get(c), "",sanakirja.get(c) );
        }
        
        try {
            
        
            kirjoitaTiedosto(vanhaNimi);
        } catch (IOException ex) {
            Logger.getLogger(Pakkausohjelma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Kirjoittaa annetun tiedoston huffman-koodattuna lukien merkki kerrallaan
     * pakattavaa tiedostoa.
     * 
     * @param tiedosto pakattava tiedosto
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void kirjoitaTiedosto(String tiedosto) throws FileNotFoundException, IOException{
        String valimerkki = "¤";
        kirjoitaAakkostoTiedostoon(puut, valimerkki);
        Scanner s = new Scanner(new File(tiedosto));
        
        
        s.useDelimiter("");
        indeksi = 7;
        char edellinen = s.next().charAt(0);
        String ekamerkki = Integer.toBinaryString(edellinen);
        for (char c : ekamerkki.toCharArray() ) {
            kirjoitaBitti(c);
        }
       
                
        while(s.hasNext()){
            char merkki = s.next().charAt(0);
            kirjoitaMerkki(merkki, edellinen);
            edellinen = merkki;
        }
        
        fos.write(puskuri);
        
        fos.close();
    }
    /**
     * Lukee tiedoston läpi kirjaten jokaisen merkin esiintymiskerrat 
     * hajautustauluun.
     * @param tiedosto pakattava tiedosto
     * @return 
     */
    public HashMap<Character, HashMap<Character, Integer>> lueTiedosto(String tiedosto){
        
        aakkosto = new HashMap<>();
        Scanner s;
        try {
  
            s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        char edellinen = s.next().charAt(0);
        while (s.hasNext()){
            
            char c = s.next().charAt(0);
            if(!aakkosto.containsKey(edellinen)){
                aakkosto.put(edellinen, new HashMap<Character, Integer>());
                aakkosto.get(edellinen).put(c, 1);
            }
            else{
                if(!aakkosto.get(edellinen).containsKey(c)){
                    aakkosto.get(edellinen).put(c, 0);
                }
                aakkosto.get(edellinen).put(c, aakkosto.get(edellinen).get(c)+1);
            }
            edellinen = c;
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pakkausohjelma.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aakkosto;
        
    }
    /**
     * rekursiivinen metodi luo annetusta puumuotoisesta aakkostosta hajautustaulun, joka sisältää
     * merkkien yleisyydet pakattavassa tiedostossa.
     * 
     * @param node merkkien yleisyydet
     * @param merkkijono tyhjä merkkijono
     * @param sanakirja aluksi tyhjä hajautustaulu
     */
    public  void luoSanakirja(Node node, String merkkijono, HashMap<Character, String> sanakirja){
        if(node.oikea == null){
//            System.out.println(node.merkki + ": "+merkkijono);
            sanakirja.put(node.merkki, merkkijono);
//            System.out.println(node.merkki + ": " +merkkijono);
            return;
        }
        luoSanakirja(node.vasen, merkkijono+"1", sanakirja);
        luoSanakirja(node.oikea, merkkijono+"0", sanakirja);
        
    }
    /**
     * Kirjoittaa annetun merkin huffman-koodattuna tiedostoon. 
     * @param merkki
     * @throws IOException 
     */
    private void kirjoitaMerkki(char merkki, char edellinen) throws IOException {
        try{
        String koodi = sanakirja.get(edellinen).get(merkki);
        
        
        for (char c : koodi.toCharArray()) {
            kirjoitaBitti(c);
        }
        }catch(Exception e){
            System.out.println("aa");
            System.out.println(merkki + " " + edellinen);
        }
    }
    /**
     * Metodi kirjoittaa merkkimuodossa annetun bitin puskuriin, ja kirjoittaa 
     * puskurin tiedostoon, jos se on täyttynyt.
     * 
     * @param c bitti merkkimuodossa
     * @throws IOException 
     */
    private void kirjoitaBitti(char c) throws IOException {
        if(c == '1'){
            puskuri |=  (1<<indeksi);
        }
        indeksi--;
        if(indeksi < 0){
            fos.write(puskuri);
            puskuri = 0;
            indeksi = 7;
        }
    }
    /**
     * Metodi kirjoittaa tiedoston, jossa on kaikki purkamiseen tarvittavat 
     * koodipuut.
     * 
     * @param aakkosto taulu tiedoston aakkostopuista
     * @param valimerkki merkki, joka ei sisälly tiedoston aakkostoon.
     * @throws IOException 
     */
    public void kirjoitaAakkostoTiedostoon(HashMap<Character, Node> aakkosto, String valimerkki) throws IOException{
        FileWriter fw = new FileWriter("aakkosto.txt");
        for (char c : aakkosto.keySet()) {
            
            fw.write(c);
            Queue<Node> jono = new LinkedList<>();
            jono.add(aakkosto.get(c));
            while(!jono.isEmpty()){
                Node n = jono.poll();
                if(n.vasen != null){
                    fw.write(valimerkki);
                    jono.add(n.vasen);
                    jono.add(n.oikea);
                    
                }
                else{
                fw.write(Character.toString(n.merkki));
                }
                
            }           
        }
        fw.close();
    }

    private HashMap<Character, Node> rakennaPuut() {
        HashMap<Character, Node> puut = new HashMap<>();
        for (Character c : aakkosto.keySet()) {
            
            puut.put(c,huff.rakennaPuu(aakkosto.get(c)));
            
        }
        return puut;
    }
    
    
}
