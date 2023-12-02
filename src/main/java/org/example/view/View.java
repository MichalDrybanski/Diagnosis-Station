package org.example.view;

import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);

    public void printMessage(String message){
        System.out.println(message);
    }
    public String getString (String message){
        printMessage(message);
        return scanner.next();
    }
    public int getInt(String message){
        printMessage(message);
        return scanner.nextInt();
    }
}
