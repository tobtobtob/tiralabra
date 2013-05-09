
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
    public void kirjoitaTiedosto(String tiedosto) throws FileNotFoundException, IOException{
        kirjoitaSanakirjaTiedostoon();
        Scanner s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        indeksi = 7;
        while(s.hasNext()){
            kirjoitaMerkki(s.next());
        }
        
        fos.write(puskuri);
        
        fos.close();
    }
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
     * Metodi debuggausta varten.
     * @param node
     * @param merkkijono 
     */
    public  void luoSanakirja(Node node, String merkkijono, HashMap<Character, String> sanakirja){
        if(node.oikea == null){
//            System.out.println(node.merkki + ": "+merkkijono);
            sanakirja.put(node.merkki, merkkijono);
            return;
        }
        luoSanakirja(node.vasen, merkkijono+"1", sanakirja);
        luoSanakirja(node.oikea, merkkijono+"0", sanakirja);
        
    }
    private void kirjoitaMerkki(String next) throws IOException {
        String koodi = sanakirja.get(next.charAt(0));
        
        for (char c : koodi.toCharArray()) {
            kirjoitaBitti(c);
        }
    }

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
    public void kirjoitaSanakirjaTiedostoon() throws IOException{
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
