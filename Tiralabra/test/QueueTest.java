/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pakkausohjelma.tietorakenteet.Queue;
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
public class QueueTest {
    private Queue q;
    public QueueTest() {
    }
    
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void LisaysJaPoistoTesti(){
        q = new Queue<Integer>();
        q.enQueue(1);
        q.enQueue(5);
        q.enQueue(700);
        assertEquals(1,q.deQueue());
        assertEquals(5, q.deQueue());
        assertEquals(700, q.deQueue());
    }
    @Test
    public void tyhjastaJonostaPoisto(){
        q = new Queue<Integer>();
        assertEquals(q.deQueue(), null);
    }
    @Test
    public void tyhjanJononTestaus(){
        q = new Queue<Integer>();
        assertTrue(q.isEmpty() == true);
    }
}
