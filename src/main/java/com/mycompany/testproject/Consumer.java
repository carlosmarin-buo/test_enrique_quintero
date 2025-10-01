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
    Goal: This class the main objetive is consume the message recived by Producter througt CustomQueue.
*/


public class Consumer implements Runnable {
    private final CustomQueue queue;
    
      public static final String POISON_PILL = "__STOP__";

    public Consumer(CustomQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String input = queue.dequeue();
                
                if (POISON_PILL.equals(input)) {
                    System.out.println("[CONSUMER] Recibido POISON_PILL, terminando hilo...");
                    break;
                }

                
                String numericPhone = convertToNumeric(input);
                String padded = padTo11(numericPhone);
                System.out.println("[CONSUMED] " + input + " => " + padded);
                Thread.sleep(800); // simula tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private String convertToNumeric(String text) {
        text = text.toUpperCase();
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) result.append(c);
            else if (c >= 'A' && c <= 'Z') result.append(mapLetterToNumber(c));
        }
        return result.toString();
    }

    private int mapLetterToNumber(char c) {
        if ("ABC".indexOf(c) >= 0) return 2;
        if ("DEF".indexOf(c) >= 0) return 3;
        if ("GHI".indexOf(c) >= 0) return 4;
        if ("JKL".indexOf(c) >= 0) return 5;
        if ("MNO".indexOf(c) >= 0) return 6;
        if ("PQRS".indexOf(c) >= 0) return 7;
        if ("TUV".indexOf(c) >= 0) return 8;
        if ("WXYZ".indexOf(c) >= 0) return 9;
        return 0;
    }

    private String padTo11(String number) {
        if (number.length() > 11) return number.substring(0, 11);
        while (number.length() < 11) number += "0";
        return number;
    }
}