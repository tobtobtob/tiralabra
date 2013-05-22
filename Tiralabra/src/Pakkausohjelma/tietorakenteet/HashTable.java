/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;


public class HashTable<K, V> {
    
    private class Node<K, V>{
        K key;
        V value;
        Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
    }
    
    Node<K, V>[] values;
    List<K> keyset;
    int koko; 

    public HashTable(int koko) {
        
        this.koko = koko;
        
        values = new Node[koko];
        keyset = new List<>(koko);
    }
    private int hash(K key){
        return key.hashCode() % koko;
    }
    public V get(K key){
        Node<K, V> node = values[hash(key)];
        if(node == null){
            return null;
        }
        while(node.key.hashCode() != key.hashCode()){
            node = node.next;
            if(node == null){
                return null;
            }
        }
        return node.value;
    }
    public boolean containsKey(K key){
        Node node = values[hash(key)];
        while(node != null){
            if(node.key.hashCode() == key.hashCode()){
                return true;
            }
            node = node.next;
        }
        return false;
    }
    public void put(K key, V value){
        
        int indeksi = hash(key);
        Node<K, V> node = values[indeksi];
        
        if(node == null){
            node = new Node<>(key, value);
            values[hash(key)] = node;
            keyset.add(key);
            return;
        }
        if(node.key.hashCode() == key.hashCode()){
            node = new Node<>(key, value);
            node.next = values[indeksi].next;
            values[indeksi] = node;
            return;
        }
        Node<K, V> edellinen = node;
        while(node != null){
            if(node.key.hashCode() == key.hashCode()){
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
        keyset.add(key);
        edellinen.next = node;
        
    }
    public K[] keySet(){
        return keyset.get();
    }
    
}
