
package Pakkausohjelma;

import Pakkausohjelma.tietorakenteet.HashTable;
import Pakkausohjelma.tietorakenteet.Node;
import Pakkausohjelma.tietorakenteet.Queue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Pakkaaja vastaa tekstitiedoston pakkaamisesta.
 * 
 * @author topi
 */
public class Pakkaaja {
    
    private FileOutputStream fos;
    private String tiedostonNimi;
    private HashTable<Character, HashTable<Character, String>> sanakirja;
    private byte puskuri;
    private int indeksi;
    private HuffmanKoodaaja huff;
    private HashTable<Character, HashTable<Character, Integer>> aakkosto;
    private HashTable<Character, Node> puut;
    /**
     * Konstruktorissa alustetaan puiden rakentamiseen tarvittava huffmankoodaaja.
     */
    public Pakkaaja() {
        huff = new HuffmanKoodaaja();
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
        HashTable<Character, HashTable<Character, Integer>> aakkosto = lueTiedosto(vanhaNimi); 
        puut = rakennaPuut();
        
        sanakirja = new HashTable<>(100);
        Object[] keyset= puut.keySet();
        for (Object c : keyset) {
            sanakirja.put((char) c, new HashTable<Character, String>(100));
            luoSanakirja(puut.get((char)c), "",sanakirja.get((char)c) );
        }
        System.out.println("sanakirja luotu");
        try {
            
        
            kirjoitaTiedosto(vanhaNimi);
        } catch (IOException ex) {
            Logger.getLogger(Pakkausohjelma.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("pakkaus valmis!");
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
        
        System.out.println("kirjoitetaan pakattu tiedosto...");
        
        String valimerkki = "¤";
        kirjoitaAakkostoTiedostoon(puut, valimerkki);
        Scanner s = new Scanner(new File(tiedosto));
        
        
        s.useDelimiter("");
        indeksi = 7;
        char edellinen = s.next().charAt(0);
        
        fos.write(edellinen);
       
                
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
    public HashTable<Character, HashTable<Character, Integer>> lueTiedosto(String tiedosto){
        System.out.println("luetaan tiedostoa...");
        aakkosto = new HashTable<>(100);
        Scanner s;
        try {
  
            s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        char edellinen = s.next().charAt(0);
        while (s.hasNext()){
            
            char c = s.next().charAt(0);
            if(!aakkosto.containsKey(edellinen)){
                aakkosto.put(edellinen, new HashTable<Character, Integer>(50));
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
    public  void luoSanakirja(Node node, String merkkijono, HashTable<Character, String> sanakirja){
        
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
    public void kirjoitaAakkostoTiedostoon(HashTable<Character, Node> aakkosto, String valimerkki) throws IOException{
       
        Object[] keyset = aakkosto.keySet();
        
        for (Object merkki : keyset) {
            
            fos.write((char) merkki);
            Queue<Node> jono = new Queue<>();
            jono.enQueue(aakkosto.get((char) merkki));
            while(!jono.isEmpty()){
                Node n = jono.deQueue();
                if(n.vasen != null){
                    fos.write((char) valimerkki.charAt(0));
                    jono.enQueue(n.vasen);
                    jono.enQueue(n.oikea);
                    
                }
                else{
                fos.write(n.merkki);
                }
                
            }           
        }
        fos.write(valimerkki.charAt(0));
        
    }
    /**
     * Rakentaa jokaiselle aakkoston merkille oman huffman-puun.
     * 
     * @return hajautustaulu sisältäen puut. 
     */
    
    private HashTable<Character, Node> rakennaPuut() {
        HashTable<Character, Node> puut;
        puut = new HashTable<>(100);
        
        Object[] keyset = aakkosto.keySet();
        
        for (Object c :keyset) {
            
            puut.put((char) c,huff.rakennaPuu(aakkosto.get((char)c)));
            
        }
        return puut;
    }
    
    
}
