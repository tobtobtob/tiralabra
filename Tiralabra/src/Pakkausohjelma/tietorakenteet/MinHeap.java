/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;


public class MinHeap <Type> {
    
    private int loppu;
    private Type[] heap;

    public MinHeap() {
    }
    
    
    public Type getMin(){
        
        Type palautus = heap[0];
        heap[0] = heap[loppu];
        loppu--;
        
        return palautus;
    }
    public void add(Type c){
        loppu++;
        if(loppu >= heap.length){
            kasvataKokoa();
        }
        heap[loppu] = c;
        int uusi = loppu;
        int verrattava = loppu/2;
        while(true){
            if(uusi == verrattava){
                break;
            }
//            if(heap[uusi].compareTo(heap[verrattava]))
        }
    }

    private void kasvataKokoa() {
        
        Type[] uusi = (Type[]) new Object[heap.length*2];
        
        for (int i = 0; i < heap.length; i++) {
            uusi[i] = heap[i];
        }
        heap = uusi;
    }
}
