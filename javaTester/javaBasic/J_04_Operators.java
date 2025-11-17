package javaBasic;

public class J_04_Operators {

    public static void main(String[] args) {
        int number = 10;

        number += 6; // number = number + 6
        System.out.println(number);
        System.out.println(number / 4);
        System.out.println(number % 3);

        int num1 = 10, num2 = 13;
        int num3 = num1++ + --num2 - 6;

        System.out.println(num1); // 11
        System.out.println(num2); // 12
        System.out.println(num3); // 10 + 12 - 6

        System.out.println(number == num3);
        System.out.println((number > num1) && (number > num2));
        System.out.println((number > num1) || (number < num2));
        System.out.println(!(number < num2));

        int age = 28;
        String message = (age >= 18) ? "Valid!" : "Invalid!";
        System.out.println(message);
    }

}
