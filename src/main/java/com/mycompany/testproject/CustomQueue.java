/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.testproject;

/*  Date:09/30/2025
    Project: Java Code
    Owner: GetBuo
    Developer: Enrique Quintero
    Version: 1.0
    Class: CustomQueue
    Goal: This class the main objetive move async messages bewteen Producer and consumer.
*/

import java.util.LinkedList;
import java.util.Queue;

public class CustomQueue {
    private final Queue<String> queue;
    private final int capacity;

    public CustomQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void enqueue(String item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait(); // espera si está llena
        }
        queue.add(item);
        System.out.println("[ENQUEUE] " + item + " (size: " + queue.size() + ")");
        notifyAll(); // notifica a los consumidores
    }

    public synchronized String dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // espera si está vacía
        }
        String item = queue.poll();
        System.out.println("[DEQUEUE] " + item + " (size: " + queue.size() + ")");
        notifyAll(); // notifica a los productores
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
    
     public int capacity() {
        return capacity;
    }
}    