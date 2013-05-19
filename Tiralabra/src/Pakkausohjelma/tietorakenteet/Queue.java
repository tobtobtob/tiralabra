

package Pakkausohjelma.tietorakenteet;

/**
 * Jono on toteutettu yksinkertaisena linkitettynä listana.
 * 
 * @author topi
 * @param <Type> Jonon sisältämien olioiden tyyppi. 
 */
public class Queue<Type> {
    
    private Node<Type> alku;
    private Node<Type> loppu;
    
    private class Node<Type>{
        Node<Type> next;
        Type value;

        public Node(Type value) {
            this.value = value;
        }
        
    }
    /**
     * tarkastaa onko jono tyhjä
     * 
     * @return 
     */
    public boolean isEmpty(){
        if(alku == null){
            return true;
        }
        return false;
    }
    
    /**
     * palauttaa ja poistaa jonon ensimmäisen alkion.
     * 
     * @return 
     */
    public Type deQueue(){
        if(isEmpty()){
            return null;
        }
        Node<Type> vanha = alku;
        alku = alku.next;
        return vanha.value;
    }
    /**
     * Laittaa parametrina annetun alkion jonon perään
     * @param t 
     */
    public void enQueue(Type t){
        if(isEmpty()){
            alku =new Node<>(t);
            loppu = alku;
            return;
        }
        loppu.next = new Node<>(t);
        loppu = loppu.next;
    }
}
