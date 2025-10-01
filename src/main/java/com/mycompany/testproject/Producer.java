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
    Class: Customer
    Goal: This class the main objetive is produce messages to CustomQueue.
*/

import java.util.Random;

public class Producer implements Runnable {
    private final CustomQueue queue;
    private final String[] samples = {"800-TEST", "888-CALL", "900-HELLO", "777-WORLD", "800-CHAT","800-GETBUO"};

    public Producer(CustomQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            for (int i = 0; i < 20; i++) { // produce 20 elementos
                String phone = samples[rand.nextInt(samples.length)];
                queue.enqueue(phone);
                Thread.sleep(500); // simula tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            try { queue.enqueue(Consumer.POISON_PILL); } catch (InterruptedException ignored) {}
        }
    }
}