
package Pakkausohjelma.tietorakenteet;
/**
 * Huffman-koodin muodostamisessa käytetyn puun yksinkertainen toteutus.
 * @author topi
 */
public class Node implements Comparable<Node> {
    
    public Node vasen;
    public Node oikea;
    public char merkki;
    public int yleisyys;
    /**
     * Alustaa uuden solmun asettaen merkin ja yleisyyden.
     * @param merkki
     * @param yleisyys 
     */
    public Node(char merkki, int yleisyys) {
        
        this.merkki = merkki;
        this.yleisyys = yleisyys;
    }
    
    /**
     * Järjestää solmut yleisyyden perusteella.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Node o) {
        return this.yleisyys-o.yleisyys;
    }
    /**
     * Metodeja debuggausta varten
     * @return 
     */
    @Override
    public String toString(){
        return "("+merkki + " : "+yleisyys + ") ";
    }
    public void print(){
        if(vasen != null){
            vasen.print();
        }
        System.out.print(this);
        if (oikea != null){
            oikea.print();
        }
    }
           
}
