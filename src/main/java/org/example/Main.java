package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(".........................WELCOME...................");
        System.out.println("PLZZ ENTER ONE OPTION");
        System.out.println();
        System.out.println("1. CREATE ");
        System.out.println("2. INSERT ");
        System.out.println("3. DELETE ");
        System.out.println("4  UPDATE ");
        System.out.println("5  FETCH  ");
        System.out.println("6  EXIT   ");
        System.out.println();
        while (true){
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if(choice==6)
                break;
            Connect.connect(choice);
        }
        System.out.println();
        System.out.println("..............................");
        System.out.println("THANK YOU");
    }
}
