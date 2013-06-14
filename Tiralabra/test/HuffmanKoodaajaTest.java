/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pakkausohjelma.HuffmanKoodaaja;
import Pakkausohjelma.tietorakenteet.HashTable;
import Pakkausohjelma.tietorakenteet.Node;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author topi
 */
public class HuffmanKoodaajaTest {
    private HuffmanKoodaaja huff;
    private HashTable<Character, Integer> taulu;
    
    
    public HuffmanKoodaajaTest() {
    }
    
    
    @Before
    public void setUp() {
        huff = new HuffmanKoodaaja();
        taulu = new HashTable<>(8);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void simppelinPuunRakennusTesti(){
        taulu.put('a', 100);
        taulu.put('b', 1);
        taulu.put('c', 2);
        
        Node puu = huff.rakennaPuu(taulu);
        Node apu = puu;
        assertTrue(apu.oikea.merkki == 'a');
        apu = puu;
        assertTrue(apu.vasen.oikea.merkki == 'c');
        apu = puu;
        assertTrue(apu.vasen.vasen.merkki == 'b');
    }
    @Test
    public void isompiAakkostoSamaYleisyys(){
        String aakkoset = "abcdefghijklmnop";
        for (int i = 0; i < aakkoset.length(); i++) {
            taulu.put(aakkoset.charAt(i), 1);
        }
        taulu.put('å', 1000);
        Node puu = huff.rakennaPuu(taulu);
        assertEquals(puu.oikea.merkki, 'å');
        
        
    }
   
}
