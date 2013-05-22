/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pakkausohjelma.tietorakenteet.HashTable;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author topi
 */
public class HashTableTest {
    private HashTable<Character, Integer> table;
    
    public HashTableTest() {
    }
    
    @Before
    public void setUp() {
        table = new HashTable<>(10);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void helppoLisaysJaPoisto1(){
        table.put('c' , 6);
        table.put('x' , 7);
        table.put('A' , 0);
        assertEquals((Integer) 6, table.get('c'));
        assertEquals((Integer) 7, table.get('x'));
        assertEquals((Integer) 0, table.get('A'));
    }
    
    @Test
    public void samanLisaysToimii(){
        table.put('v', 7);
        table.put('b', 7);
        table.put('v', 9);
        assertEquals((Integer) 9, table.get('v'));
    }
    @Test
    public void ylivuotolistatToimii(){
        String s = "qwertyuiopasdfghjklzxcvåäbnm,.-";
        for (int i = 0; i < s.length(); i++) {
            table.put((Character) s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            assertEquals((Integer) i, table.get(s.charAt(i)));
        }
    }
    @Test
    public void keySetTesti1(){
        table.put('å', 94);
        table.put('f', 88);
        table.put('t', 2);
        char[] tulos = {'å', 'f', 't'};
        Object[] keyset;
        keyset =  table.keySet();
        for (int i = 0; i < keyset.length; i++) {
            assertTrue(tulos[i] ==(Character) keyset[i]);
        }
    }
    @Test
    public void keySetTesti2(){
        table.put('å', 94);
        table.put('å', 9774);
        table.put('å', 944);
        table.put('f', 88);
        table.put('f', 899);
        table.put('t', 2);
        char[] tulos = {'å', 'f', 't'};
        Object[] keyset;
        keyset =  table.keySet();
        for (int i = 0; i < keyset.length; i++) {
            assertTrue(tulos[i] ==(Character) keyset[i]);
        }
    }
    @Test
    public void containsKeyTesti(){
        table.put('ö', 6);
        table.put('ö', 8);
        table.put('å', 54);
        table.put('p', 333);
        assertTrue(table.containsKey('ö'));
        assertFalse(table.containsKey('y'));
        assertTrue(table.containsKey('å'));
    }
}
