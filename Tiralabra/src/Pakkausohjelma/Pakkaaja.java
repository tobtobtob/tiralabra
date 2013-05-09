/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakkaaja;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    int puskuri;
    int indeksi;
    
    public Kirjoittaja(String tiedostonNimi, HashMap<Character, String> sanakirja) throws IOException{
        this.tiedostonNimi = tiedostonNimi;
        fos = new FileOutputStream(tiedostonNimi);
        this.sanakirja = sanakirja;
    }
    
    public void kirjoitaTiedosto(String tiedosto) throws FileNotFoundException, IOException{
        Scanner s = new Scanner(new File(tiedosto));
        s.useDelimiter("");
        indeksi = 7;
        while(s.hasNext()){
            kirjoitaMerkki(s.next());
        }
        if(puskuri > 0){
            fos.write(puskuri);
        }
        fos.close();
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
    
    
    
}
