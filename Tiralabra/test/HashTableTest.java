/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pakkausohjelma.tietorakenteet.HashTable;
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
        String s = "qwertyuiopasdfghjklzxcvbnm,.-";
        for (int i = 0; i < s.length(); i++) {
            table.put(s.charAt(i), i);
        }
        for (int i = 0; i < s.length(); i++) {
            assertEquals((Integer) i, table.get(s.charAt(i)));
        }
    }
}
