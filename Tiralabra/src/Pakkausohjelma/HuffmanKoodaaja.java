
package Pakkausohjelma;

import Pakkausohjelma.tietorakenteet.HashTable;
import Pakkausohjelma.tietorakenteet.MinHeap;
import Pakkausohjelma.tietorakenteet.Node;
import Pakkausohjelma.tietorakenteet.NodeComparator;
import java.util.HashMap;
import java.util.PriorityQueue;
/**
 * Huffmankoodaaja on pakkaajan tarvitsema apuluokka puiden rakentamiseen.
 * @author topi
 */
public class HuffmanKoodaaja {

    /**
     * Metodi rakentaa annetusta kirjainten esiintymismäärät
     * sisältävästä hajautustaulusta puun,
     * josta voi selvittää kunkin merkin Huffman-koodin
     *
     * @param Merkkien esiintymistiheydet
     * @return sanakirja puumuodossa
     */
    public Node rakennaPuu(HashTable<Character, Integer> aakkosto) {
        
        NodeComparator nc = new NodeComparator();
        MinHeap<Node> keko = new MinHeap<>(nc);
        Object[] keyset = aakkosto.keySet();
        for (Object merkki : keyset) {
            Node n = new Node((char)merkki, aakkosto.get((char)merkki));
            keko.add(n);
        }
        while (keko.size() > 1) {
            Node node1 = keko.getMin();
            Node node2 = keko.getMin();
            Node uusi = new Node('_', node1.yleisyys + node2.yleisyys);
            uusi.vasen = node1;
            uusi.oikea = node2;
            keko.add(uusi);
        }
        return keko.getMin();
    }
   
    
}
