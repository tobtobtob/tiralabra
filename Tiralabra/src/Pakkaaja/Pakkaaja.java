/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakkaaja;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author topi
 */
public class Pakkaaja {
    
    public static HashMap<Character, Integer> lueTiedosto(String tiedosto){
        
        HashMap<Character, Integer> aakkosto = new HashMap<Character, Integer>();
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
            Logger.getLogger(Pakkaaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aakkosto;
        
    }
    /**
     * Metodi rakentaa annetuista kirjainten esiintymismääristä puun,
     * josta voi selvittää kunkin merkin Huffman-koodin
     * 
     * @param Merkkien esiintymistiheydet
     * @return sanakirja puumuodossa
     */
    public static Node rakennaPuu(HashMap<Character, Integer> aakkosto){
        PriorityQueue<Node> keko = new PriorityQueue<Node>();
        for (char s : aakkosto.keySet()) {
            Node n = new Node(s, aakkosto.get(s));
            keko.add(n);
        }
        while (keko.size() > 1){
            Node node1 = keko.poll();
            Node node2 = keko.poll();
            Node uusi = new Node('_', node1.yleisyys+node2.yleisyys);
            uusi.vasen = node1;
            uusi.oikea = node2;
            keko.add(uusi);
        }
        
        
        return keko.poll();
    }
    /**
     * Metodi debuggausta varten.
     * @param node
     * @param merkkijono 
     */
    public static void luoSanakirja(Node node, String merkkijono, HashMap<Character, String> sanakirja){
        if(node.oikea == null){
            System.out.println(node.merkki + ": "+merkkijono);
            sanakirja.put(node.merkki, merkkijono);
            return;
        }
        luoSanakirja(node.vasen, merkkijono+"1", sanakirja);
        luoSanakirja(node.oikea, merkkijono+"0", sanakirja);
        
    }
    
    public static void main(String[] args) {
        HashMap<Character, Integer> aakkosto = lueTiedosto("testi.txt");
        
        Node sanakirjapuu = rakennaPuu(aakkosto);
        HashMap<Character, String> sanakirja = new HashMap<Character, String>();
        luoSanakirja(sanakirjapuu, "", sanakirja);
        Kirjoittaja kirjoittaja;
        try {
            kirjoittaja = new Kirjoittaja("pakattuTesti", sanakirja);
        
            kirjoittaja.kirjoitaTiedosto("testi.txt");
        } catch (IOException ex) {
            Logger.getLogger(Pakkaaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        Purkaja p = new Purkaja();
        p.setSanakirja(sanakirjapuu);
        try {
            p.puraTiedosto("pakattuTesti", "purettuTesti.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pakkaaja.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pakkaaja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}