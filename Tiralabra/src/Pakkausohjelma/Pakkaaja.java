
package Pakkausohjelma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pakkaaja {
    
    FileOutputStream fos;
    String tiedostonNimi;
    HashMap<Character, String> sanakirja;
    byte puskuri;
    int indeksi;
    HuffmanKoodaaja huff;
    HashMap<Character, Integer> aakkosto;

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
        HashMap<Character, Integer> aakkosto = lueTiedosto(vanhaNimi);        
        Node sanakirjapuu = huff.rakennaPuu(aakkosto);
        sanakirja = new HashMap<>();
        luoSanakirja(sanakirjapuu, "", sanakirja);
        
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
        kirjoitaAakkostoTiedostoon();
        Scanner s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        indeksi = 7;
        while(s.hasNext()){
            kirjoitaMerkki(s.next());
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
    public HashMap<Character, Integer> lueTiedosto(String tiedosto){
        
        aakkosto = new HashMap<Character, Integer>();
        Scanner s;
        try {
  
            s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        while (s.hasNext()){
            char c = s.next().charAt(0);
            if(!aakkosto.containsKey(c)){
                aakkosto.put(c, 1);
            }
            else{
                aakkosto.put(c, aakkosto.get(c)+1);
            }
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
            System.out.println(node.merkki + ": " +merkkijono);
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
    private void kirjoitaMerkki(String merkki) throws IOException {
        String koodi = sanakirja.get(merkki.charAt(0));
        
        for (char c : koodi.toCharArray()) {
            kirjoitaBitti(c);
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
     * Metodi kirjoittaa erillisen tiedoston, joka sisältää pakattavan 
     * tiedoston merkkien määrät. 
     * @throws IOException 
     */
    public void kirjoitaAakkostoTiedostoon() throws IOException{
        FileWriter fw = new FileWriter("aakkosto.txt");
        boolean eka = true;
        for (char c : aakkosto.keySet()) {
            if(!eka){
                fw.write('@');
               
            }
            
            eka = false;
            fw.write(c);
            fw.write('#');
            fw.write(aakkosto.get(c)+"");
            
            
        }
        fw.close();
    }
    
    
}
