package com.mycompany.test.queue;

/*  Date:09/30/2025
    Project: Java Code
    Owner: GetBuo
    Developer: Enrique Quintero
    Version: 1.0
    Class: QueueTest
    Goal: The Main objetive is testing using Junit process.
*/

import com.mycompany.testproject.CustomQueue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testEnqueueDequeue() throws InterruptedException {
        CustomQueue queue = new CustomQueue(2);
        queue.enqueue("800-TEST");
        queue.enqueue("900-CALL");
        assertEquals(2, queue.size());
        String val = queue.dequeue();
        assertNotNull(val);
        assertEquals(1, queue.size());
    }

    @Test
    void testCapacityLimit() throws InterruptedException {
        CustomQueue queue = new CustomQueue(1);
        queue.enqueue("800-TEST");
        assertEquals(1, queue.size());
    }
}