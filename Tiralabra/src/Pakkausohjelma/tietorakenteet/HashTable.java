/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;


public class HashTable<Key, Value> {
    
    private class Node<Key, Value>{
        Key key;
        Value value;
        Node<Key,Value> next;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
        
    }
    
    Node<Key, Value>[] values;
    Set<Key> keyset;
    int koko; 

    public HashTable(int koko) {
        
        this.koko = koko;
        values = new Node[koko];
        
    }
    private int hash(Key key){
        return key.hashCode() % koko;
    }
    public Value get(Key key){
        Node<Key, Value> node = values[hash(key)];
        if(node == null){
            return null;
        }
        while(node.key != key){
            node = node.next;
            if(node == null){
                return null;
            }
        }
        return node.value;
    }
    public void put(Key key, Value value){
        int indeksi = hash(key);
        Node<Key, Value> node = values[indeksi];
        
        if(node == null){
            node = new Node<>(key, value);
            values[hash(key)] = node;
            return;
        }
        if(node.key == key){
            node = new Node<>(key, value);
            node.next = values[indeksi].next;
            values[indeksi] = node;
            return;
        }
        Node<Key, Value> edellinen = node;
        while(node != null){
            if(node.key == key){
                Node seuraava = node.next;
                node = new Node<>(key, value);
                edellinen.next = node;
                node.next = seuraava;
                return;
            }
            edellinen = node;
            node = node.next;
        }
        node = new Node<>(key, value);
       
        edellinen.next = node;
        
    }
    
}
