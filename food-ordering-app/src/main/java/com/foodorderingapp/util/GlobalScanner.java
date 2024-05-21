package com.foodorderingapp.utils;

import java.util.Scanner;

public class GlobalScanner {
    private static GlobalScanner instance;
    private Scanner scanner;

    private GlobalScanner() {
        scanner = new Scanner(System.in);
    }

    public static GlobalScanner getInstance() {
        if (instance == null) {
            instance = new GlobalScanner();
        }
        return instance;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public double nextDouble() {
        return scanner.nextDouble();
    }

    public String next(){
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}