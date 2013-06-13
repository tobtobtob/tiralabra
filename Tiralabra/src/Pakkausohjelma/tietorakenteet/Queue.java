

package Pakkausohjelma.tietorakenteet;

/**
 * Jono on toteutettu yksinkertaisena linkitettynä listana.
 * 
 * @author topi
 * @param T Jonon sisältämien olioiden tyyppi. 
 */
public class Queue<T> {
    
    private Node<T> alku;
    private Node<T> loppu;
    /**
     * Jonon sisäluokka on yksi jonon alkio
     * @param <Type> jonon alkioiden tyyppi
     */
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
    public T deQueue(){
        if(isEmpty()){
            return null;
        }
        Node<T> vanha = alku;
        alku = alku.next;
        return vanha.value;
    }
    /**
     * Laittaa parametrina annetun alkion jonon perään
     * @param t 
     */
    public void enQueue(T t){
        if(isEmpty()){
            alku =new Node<>(t);
            loppu = alku;
            return;
        }
        loppu.next = new Node<>(t);
        loppu = loppu.next;
    }
}
