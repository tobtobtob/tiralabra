/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;

import java.util.Comparator;

/**
 * minimikeon käyttämä apuluokka Nodejen vertailuun.
 * 
 * @author topi
 */
public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return o1.yleisyys-o2.yleisyys;
    }

}
