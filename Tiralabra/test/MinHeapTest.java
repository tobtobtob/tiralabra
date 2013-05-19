/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pakkausohjelma.tietorakenteet.MinHeap;
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
public class MinHeapTest {
    private MinHeap<Integer> heap;
    private IntComparator ic;
    
    public MinHeapTest() {
    }
    
    @Before
    public void setUp() {
        ic = new IntComparator();
        heap = new MinHeap<>(ic);
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void lisaysJaPoisto1(){
        
        heap.add(3);
        heap.add(1);
        assertEquals((Integer) 1, heap.getMin());
        assertEquals((Integer) 3, heap.getMin());
        assertEquals(0, heap.size());
       
    }
    @Test
    public void lisaysJaPoistoYliOletuskoon(){
        for (int i = 10; i > 0; i--) {
            heap.add(i);
        }
        for (int i = 1; i <= 10; i++) {
            assertEquals((Integer) i, heap.getMin());
        }
    }
    @Test
    public void isoLisaysJaPoisto(){
        for (int i = 100; i > 0; i--) {
            heap.add(i);
        }
        for (int i = 1; i <= 100; i++) {
            assertEquals((Integer) i, heap.getMin());
        }
    }
    @Test
    public void lisaysJaPoisto2(){
        heap.add(3);
        heap.add(3);
        heap.add(3);
        heap.add(1);
        heap.add(3);
        heap.add(3);
        assertEquals((Integer) 1, heap.getMin());
        assertEquals((Integer) 3, heap.getMin());
        assertEquals(4, heap.size());
    }
    
}
