/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakkaaja;
import java.io.File;
import java.io.FileNotFoundException;
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
    public static Solmu rakennaPuu(HashMap<Character, Integer> aakkosto){
        PriorityQueue<Solmu> keko = new PriorityQueue<Solmu>();
        for (char s : aakkosto.keySet()) {
            Solmu n = new Solmu(s, aakkosto.get(s));
            keko.add(n);
        }
        while (keko.size() > 1){
            Solmu node1 = keko.poll();
            Solmu node2 = keko.poll();
            Solmu uusi = new Solmu('_', node1.yleisyys+node2.yleisyys);
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
    public static void tulostaMerkit(Solmu node, String merkkijono){
        if(node.oikea == null){
            System.out.println(node.merkki + ": "+merkkijono);
            return;
        }
        tulostaMerkit(node.vasen, merkkijono+"1");
        tulostaMerkit(node.oikea, merkkijono+"0");
        
    }
    public static HashMap<Character, String> luoSanakirja(String tiedosto){
        
        return null;        
    }
    public static void main(String[] args) {
        HashMap<Character, Integer> aakkosto = lueTiedosto("testi.txt");
        
        Solmu puu = rakennaPuu(aakkosto);
        tulostaMerkit(puu, "");
    }
    
}