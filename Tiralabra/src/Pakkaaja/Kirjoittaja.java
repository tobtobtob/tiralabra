/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakkaaja;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author topi
 */
public class Kirjoittaja {
    
    FileOutputStream fos;
    String tiedostonNimi;
    HashMap<Character, String> sanakirja;
    byte puskuri;
    int indeksi;
    
    public Kirjoittaja(String tiedostonNimi, HashMap<Character, String> sanakirja) throws IOException{
        this.tiedostonNimi = tiedostonNimi;
        fos = new FileOutputStream(tiedostonNimi);
        this.sanakirja = sanakirja;
    }
    
    public void kirjoitaTiedosto(String tiedosto) throws FileNotFoundException, IOException{
        Scanner s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        indeksi = 8;
        while(s.hasNext()){
            kirjoitaMerkki(s.next());
        }
        if(puskuri > 0){
            fos.write(puskuri);
        }
        fos.close();
    }

    private void kirjoitaMerkki(String next) throws IOException {
        String koodi = sanakirja.get(next);
        
        for (char c : next.toCharArray()) {
            kirjoitaBitti(c);
        }
    }

    private void kirjoitaBitti(char c) throws IOException {
        if(c == '1'){
            puskuri += (int) Math.pow(2, indeksi);
        }
        indeksi--;
        if(indeksi < 0){
            fos.write(puskuri);
            indeksi = 8;
        }
    }
    
    
    
}
