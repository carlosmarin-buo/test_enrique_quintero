/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.testproject;


/*  Date:09/30/2025
    Project: Java Code
    Owner: GetBuo
    Developer: Enrique Quintero
    Version: 1.0
    Class: TestProject
    Goal: The Main class is core process.
*/
public class TestProject {

    public static void main(String[] args) {
        try {
            CustomQueue queue = new CustomQueue(10);
            
            Thread producer = new Thread(new Producer(queue));
            Thread consumer = new Thread(new Consumer(queue));
            
            producer.start();
            consumer.start();
            producer.interrupt();
            producer.join(); // esperar a que termine el productor
            
            queue.enqueue(Consumer.POISON_PILL); // 👈 enviar señal de parada
            consumer.interrupt();
            
            consumer.join(); // esperar a que termine el consumidor
            System.out.println("--- Thank you all working well!!!");
        } catch (InterruptedException ex) {
            System.getLogger(TestProject.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
