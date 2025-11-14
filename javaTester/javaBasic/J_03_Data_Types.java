package javaBasic;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class J_03_Data_Types {
    private static final Logger log = LoggerFactory.getLogger(J_03_Data_Types.class);
    // Primitive Data Types
    byte bNumber;
    short sNumber;
    int iNumber;
    long lNumber;
    float fNumber;
    static double dNumber;
    boolean status;
    char letter;

    // Reference Types
    String name = "Dong";
    String[] tools;
    J_03_Data_Types object;
    WebDriver driver;

    public static void main(String[] args) {
        // Primitive Type
        int number1 = 10;
        int number2 = number1;

        System.out.println("Number 1 before: " + number1);
        System.out.println("Number 2 before: " + number2);

        number2 = 13;

        System.out.println("Number 1 after: " + number1);
        System.out.println("Number 2 after: " + number2);

        // Reference Type
        J_03_Data_Types object1 = new J_03_Data_Types();
        J_03_Data_Types object2 = object1;

        System.out.println("Name 1 before: " + object1.name);
        System.out.println("Name 2 before: " + object2.name);

        object2.name = "YuDo";

        System.out.println("Name 1 after: " + object1.name);
        System.out.println("Name 2 after: " + object2.name);
    }

}
