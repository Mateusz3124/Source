package jpong.app.testapp;

import jpong.app.NodeDist;
import jpong.app.PriorityQueue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {
    @Test
    void testInitialize(){
        PriorityQueue priorityQueue = new PriorityQueue();
        assertTrue(priorityQueue.isEmpty());
    }
    @Test
    void testPopOrder(){
        ArrayList<Integer> poped = new ArrayList<Integer>();
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(new NodeDist(0, 0.3));
        priorityQueue.add(new NodeDist(1, 0.7));
        priorityQueue.add(new NodeDist(2, 0.1));
        priorityQueue.add(new NodeDist(3, 0.9));
        priorityQueue.add(new NodeDist(4, 0.0));
        priorityQueue.add(new NodeDist(5, 0.5));
        priorityQueue.add(new NodeDist(6, 0.4));
        priorityQueue.add(new NodeDist(7, 0.8));
        while (!priorityQueue.isEmpty()){
            poped.add(priorityQueue.pop().getNode());
        }
        assertEquals(poped.size(), 8);
        if(poped.size() == 8) {
            assertEquals((int) poped.get(0), 4);
            assertEquals((int) poped.get(1), 2);
            assertEquals((int) poped.get(2), 0);
            assertEquals((int) poped.get(3), 6);
            assertEquals((int) poped.get(4), 5);
            assertEquals((int) poped.get(5), 1);
            assertEquals((int) poped.get(6), 7);
            assertEquals((int) poped.get(7), 3);
        }
    }
}