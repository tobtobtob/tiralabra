/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;


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
    
    public boolean isEmpty(){
        if(alku == null){
            return true;
        }
        return false;
    }
    public Type deQueue(){
        if(isEmpty()){
            return null;
        }
        Node<Type> vanha = alku;
        alku = alku.next;
        return vanha.value;
    }
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
