
package Pakkaaja;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;



public class Purkaja {
    
    private Node sanakirja;
    
    private FileWriter fw;
    private int puskuri;
    private int indeksi;

    public Purkaja() {
    }

    public void setSanakirja(Node sanakirja) {
        this.sanakirja = sanakirja;
    }
    
    public void puraTiedosto(String tiedosto, String uusinimi) throws FileNotFoundException, IOException{
        FileInputStream s = new FileInputStream(new File(tiedosto));
        Node node = sanakirja;
        puskuri =  s.read();
        indeksi = (int) Math.pow(2, 7);
        fw = new FileWriter(uusinimi);
        while(s.available()> 0){
           node = sanakirja;
           while(node.oikea != null){
               if(indeksi<0){
                   puskuri =  s.read();
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
    
}
