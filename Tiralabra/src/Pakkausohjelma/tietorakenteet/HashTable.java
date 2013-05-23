/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Pakkausohjelma.tietorakenteet;


public class HashTable<K, V> {
    /**
     * Hajautustaulun alkio, johon on helppo liittää lista samaan taulukon
     * paikkaan kuvautuneita alkioita
     * 
     * @param <K> Avaimen tyyppi
     * @param <V> Arvon tyyppi
     */
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
    /**
     * Yksinkertainen hajautusfunktio. Parantelen tätä vielä.
     * @param key
     * @return 
     */
    private int hash(K key){
        return key.hashCode() % koko;
    }
    /**
     * Palauttaa avaimeen "key" liittyvän arvon
     * 
     * @param key
     * @return 
     */
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
    /**
     * Metodi tarkistaa, sisältyykö parametrina annettu avain hajautustauluun.
     * 
     * @param key
     * @return 
     */
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
    /**
     * Lisää annetun avain-arvo -parin hajautustauluun. 
     * 
     * @param key
     * @param value 
     */
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
    /**
     * Palauttaa taulukon, joka sisältää hajautustaulun avaimet.
     * @return 
     */
    public K[] keySet(){
        return keyset.get();
    }
    
}
