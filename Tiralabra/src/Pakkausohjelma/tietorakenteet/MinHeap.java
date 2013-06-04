/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;

import java.util.Comparator;

/**
 * Minimikeko
 * @author topi
 * @param <T> 
 */
public class MinHeap <T> {
    
    private int loppu;
    private T[] heap;
    Comparator<T> c;
    /**
     * Konstruktori itse määrätyllä taulukon alkukoolla
     * @param c 
     * @param koko keolle varatun taulukon koko
     */
    public MinHeap(Comparator<T> c, int koko) {
        this.c = c;
        heap = (T[]) new Object[koko];
        loppu = 0;
    }
    /**
     * Konstruktori oletuskoolla 8
     * @param c 
     */
    public MinHeap(Comparator<T> c) {
        this(c, 8);
    }
    
    /**
     * palauttaa ja poistaa pienimmän alkion keosta.
     * @return 
     */
    public T getMin(){
        
        T palautus = heap[0];
        heap[0] = heap[loppu-1];
        int indeksi = 1;
        while(true){
            if(indeksi*2> loppu-1){
                break;
            }
            if(c.compare(heap[indeksi*2-1], heap[indeksi*2])<0){
                if(c.compare(heap[indeksi*2-1], heap[indeksi-1])<0){
                        swap(indeksi, indeksi*2);
                        indeksi = indeksi*2;
                }
                else{
                    break;
                }
            }
            else {
                if(c.compare(heap[indeksi*2], heap[indeksi-1])<0){
                        swap(indeksi, indeksi*2+1);
                        indeksi = indeksi*2+1;
                }
                else{
                    break;
                }
            }
        }
        loppu--;
        
        return palautus;
    }
    /**
     * Lisää parametrina annetun alkion kekoon, säilyttäen keon ehdot.
     * @param t 
     */
    public void add(T t){
        loppu++;
        if(loppu >= heap.length){
            kasvataKokoa();
        }
        heap[loppu-1] = t;
        int uusi = loppu;
        int verrattava = loppu/2;
        while(true){
            if(uusi == 1){
                break;
            }
            if(c.compare(heap[uusi-1], heap[verrattava-1]) < 0){
                swap(uusi, verrattava);
                uusi = uusi/2;
                verrattava = uusi/2;
            }
            else{
                break;
            }
        }
        
    }
    
    /**
     * Tuplaa keon käytössä olevan taulukon tilan loppuessa
     */
    private void kasvataKokoa() {
        
        T[] uusi = (T[]) new Object[heap.length*2];
        
        for (int i = 0; i < heap.length; i++) {
            uusi[i] = heap[i];
        }
        heap = uusi;
    }
    /**
     * Vaihtaa kahden parametrina annetun alkion paikkaa
     * 
     * @param uusi vaihdettavan alkion indeksi
     * @param verrattava vaihdettavan alkion indeksi
     */
    
    private void swap(int uusi, int verrattava) {
        T apu = heap[uusi-1];
        heap[uusi-1] = heap[verrattava-1];
        heap[verrattava-1] = apu;
    }
    
    /**
     * palauttaa keon alkioiden määrän
     * @return alkioiden määrä
     */
    public int size(){
        return loppu;
    }

    
}
