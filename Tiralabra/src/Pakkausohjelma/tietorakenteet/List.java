/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;


/**
 * List on yksinkertainen lista, jonka operaatioina ovat lisääminen ja
 * koko listan palauttaminen
 * @author topi
 * @param T 
 */
public class List<T >{
    
    T[] lista;
    int loppu;
    
    public List(int koko) {
        loppu = 0;
        
        lista = (T[]) new Object[koko];
         
    }
    public void add(T lisattava){
        lista[loppu] = lisattava;
        loppu++;
        if(loppu>=lista.length){
            kasvataKokoa();
        }
    }

    private void kasvataKokoa() {
        T[] uusi = (T[]) new Object[loppu*2];
        for (int i = 0; i < lista.length; i++) {
            uusi[i] = lista[i];
        }
        lista = uusi;
    }
    public T[] get(){
        T[] palautus = (T[]) new Object[loppu];
        for (int i = 0; i < palautus.length; i++) {
            palautus[i] = lista[i];
        }
        return (T[]) palautus;
    }
    
    
    
}
