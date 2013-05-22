/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Pakkausohjelma.tietorakenteet.*;
/**
 *
 * @author topi
 */
public class ListaTest {
    List<Integer> lista;
    public ListaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        lista = new List(5);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void helppoLisaysJaPoistoTesti(){
        lista.add(5);
        lista.add(8888);
        lista.add(-3);
        int[] tulos = {5, 8888, -3};
        Object[] lista2 = lista.get();
        for (int i = 0; i < lista2.length; i++) {
            assertTrue((Integer)lista2[i] == tulos[i]);
        }
    }
    @Test
    public void listanKasvaminenYliKoon(){
        for (int i = 0; i < 21; i++) {
            lista.add(i);
        }
        Object[] lista2 = lista.get();
        for (int i = 0; i < 21; i++) {
            assertTrue((Integer)lista2[i] == i);
        }
    }
}
