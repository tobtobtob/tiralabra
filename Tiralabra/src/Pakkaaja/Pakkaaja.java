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
    public static void tulostaMerkit(Node node, String merkkijono){
        if(node.oikea == null){
            System.out.println(node.merkki + ": "+merkkijono);
            return;
        }
        tulostaMerkit(node.vasen, merkkijono+"1");
        tulostaMerkit(node.oikea, merkkijono+"0");
        
    }
    public static void main(String[] args) {
        HashMap<Character, Integer> aakkosto = lueTiedosto("testi.txt");
        
        Node puu = rakennaPuu(aakkosto);
        tulostaMerkit(puu, "");
    }
}