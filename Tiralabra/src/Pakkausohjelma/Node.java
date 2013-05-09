
package Pakkausohjelma;

public class Node implements Comparable<Node> {
    
    Node vasen;
    Node oikea;
    char merkki;
    int yleisyys;

    public Node(char merkki, int yleisyys) {
        
        this.merkki = merkki;
        this.yleisyys = yleisyys;
    }
    

    @Override
    public int compareTo(Node o) {
        return this.yleisyys-o.yleisyys;
    }
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
