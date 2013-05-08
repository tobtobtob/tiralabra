/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pakkaaja;

/**
 *
 * @author topi
 */
public class Solmu implements Comparable<Solmu> {
    
    Solmu vasen;
    Solmu oikea;
    char merkki;
    int yleisyys;

    public Solmu(char merkki, int yleisyys) {
        
        this.merkki = merkki;
        this.yleisyys = yleisyys;
    }
    

    @Override
    public int compareTo(Solmu o) {
        return this.yleisyys-o.yleisyys;
    }
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
