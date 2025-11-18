package javaBasic;

import java.util.Scanner;

public class J_06_If_Else {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your age:");
        int age = sc.nextInt();

        if (age >= 18) {
            System.out.println("Valid!");
        } else {
            System.out.println("Invalid!");
        }

        System.out.println("Enter 3 numbers:");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if (a >= b && a >= c) {
            System.out.println(a + " is the largest number");
        } else if (b >= a && b >= c) {
            System.out.println(b + " is the largest number");
        } else {
            System.out.println(c + " is the largest number");
        }

        sc.close();
    }

}
