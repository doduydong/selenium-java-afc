package javaBasic;

public class J_10_Arrays {

    public static void main(String[] args) {
        // int[] numbers = {13, 10, 97, 0, 1, 6, 7, 11};

        int[] numbers = new int[8];

        numbers[0] = 13;
        numbers[1] = 10;
        numbers[2] = 97;
        numbers[3] = 0;
        numbers[4] = 1;
        numbers[5] = 6;
        numbers[6] = 7;
        numbers[7] = 11;

        System.out.println(numbers.length);
        System.out.println(numbers[6]);

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == 1) {
                System.out.println("Action!");
            }
        }

        for (int number : numbers) {
            if (number % 2 == 0) {
                System.out.println("Even number: " + number);
            } else {
                System.out.println("Odd number: " + number);
            }
        }
    }

}
