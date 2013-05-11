
package Pakkausohjelma;

import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanKoodaaja {

    /**
     * Metodi rakentaa annetusta kirjainten esiintymismäärät
     * sisältävästä hajautustaulusta puun,
     * josta voi selvittää kunkin merkin Huffman-koodin
     *
     * @param Merkkien esiintymistiheydet
     * @return sanakirja puumuodossa
     */
    public Node rakennaPuu(HashMap<Character, Integer> aakkosto) {
        PriorityQueue<Node> keko = new PriorityQueue<>();
        for (char s : aakkosto.keySet()) {
            Node n = new Node(s, aakkosto.get(s));
            keko.add(n);
        }
        while (keko.size() > 1) {
            Node node1 = keko.poll();
            Node node2 = keko.poll();
            Node uusi = new Node('_', node1.yleisyys + node2.yleisyys);
            uusi.vasen = node1;
            uusi.oikea = node2;
            keko.add(uusi);
        }
        return keko.poll();
    }
   
    
}
