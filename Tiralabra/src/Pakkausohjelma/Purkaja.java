
package Pakkausohjelma;

import Pakkausohjelma.tietorakenteet.HashTable;
import Pakkausohjelma.tietorakenteet.Node;
import Pakkausohjelma.tietorakenteet.Queue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Purkaja vastaa nimensä mukaisesti pakatun tiedoston purkamisesta. 
 * @author topi
 */
public class Purkaja {
    
    private HashTable<Character, Node> sanakirja;
    
    private FileWriter fw;
    private byte puskuri;
    private int indeksi;
    

    /**
     * Purkajan päämetodi, joka lukee tiedostoa bitti kerrallaan, liikkuen 
     * samalla edelliseen merkkiin liittyvässä merkkipuussa.
     * 
     * @param tiedosto purettava tiedosto
     * @param uusinimi puretun tiedoston nimi
     * @param aakkosto aakkostotiedoston nimi. 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void puraTiedosto(String tiedosto, String uusinimi, String aakkosto) throws FileNotFoundException, IOException{
        FileInputStream lukija = new FileInputStream(new File(tiedosto));
        char valimerkki = '¤';
        Node node;
        sanakirja = luePuut(aakkosto, valimerkki, lukija); //kovakoodattu välimerkki pitää muuttaa järkevämmäksi
        char edellinen = Character.toChars(lukija.read())[0];
        
        puskuri =  (byte) lukija.read();
        indeksi = 7;
        fw = new FileWriter(new File(uusinimi));
         fw.write(edellinen);
        System.out.println("puretaan tiedostoa...");
        while(lukija.available()> 0){
           node = sanakirja.get(edellinen);
           while(node.oikea != null){
               if(indeksi<0){
                   puskuri = (byte) lukija.read();
                   
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
        System.out.println("purku valmis!");
    }
    /**
     * Rakentaa hajautustaulun aakkoston merkkeihin liittyvistä huffman-koodipuista.
     * Lukee aakkoston parametrina annetusta tiedostosta.
     * 
     * @param aakkosto tiedosto, jossa on puut koodattuna
     * @param tyhjaNode välimerkki, jolla tyhjät nodet on merkattu puutiedostossa. 
     * @return
     * @throws FileNotFoundException 
     */
    public HashTable<Character, Node> luePuut(String aakkosto,char tyhjaNode, FileInputStream lukija) throws FileNotFoundException, IOException{
        System.out.println("luetaan sanakirjaa...");
        HashTable<Character, Node> puut= new HashTable<>(100);
//        Scanner s = new Scanner(new File(aakkosto));
//        s.useDelimiter("");
        while(true){
            
            char c = (char) lukija.read();
            if( c == tyhjaNode){
                break;
            }
            Node n = new Node((char)lukija.read(), 0);
            puut.put(c, n);
            Queue<Node> jono = new Queue<>();
            jono.enQueue(n);
            while(!jono.isEmpty()){
                Node node = jono.deQueue();
                if(node.merkki == tyhjaNode){
                    Node vasen = new Node((char) lukija.read(), 0);
                    Node oikea = new Node((char) lukija.read(), 0);
                    node.oikea = oikea;
                    node.vasen = vasen;
                    jono.enQueue(vasen);
                    jono.enQueue(oikea);
                }
            }
            
        }
        return puut;
    }
}
