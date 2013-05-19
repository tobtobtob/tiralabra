/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;

import java.util.Comparator;


public class MinHeap <Type> {
    
    private int loppu;
    private Type[] heap;
    Comparator<Type> c;

    public MinHeap(Comparator<Type> c) {
        this.c = c;
        heap = (Type[]) new Object[8];
        loppu = 0;
    }
    
    
    public Type getMin(){
        
        Type palautus = heap[0];
        heap[0] = heap[loppu-1];
        int indeksi = 1;
        while(true){
            if(indeksi*2>= loppu-1){
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
    public void add(Type t){
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
    

    private void kasvataKokoa() {
        
        Type[] uusi = (Type[]) new Object[heap.length*2];
        
        for (int i = 0; i < heap.length; i++) {
            uusi[i] = heap[i];
        }
        heap = uusi;
    }

    private void swap(int uusi, int verrattava) {
        Type apu = heap[uusi-1];
        heap[uusi-1] = heap[verrattava-1];
        heap[verrattava-1] = apu;
    }
    public int size(){
        return loppu;
    }

    
}
