package javaBasic;

import java.util.Scanner;

public class J_02_User_Input {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();

        System.out.println("Enter your age:");
        String age = sc.nextLine();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);

        sc.close();
    }

}
