package javaBasic;

public class J_09_While_Loop {

    public static void main(String[] args) {
        int number = 0;

        while (number <= 10) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
            number++;
        }

        System.out.println("* " + number);

        do {
            if (number % 2 != 0) {
                System.out.println(number);
            }
            number--;
        } while (number >= 0 && number <= 10);

        System.out.println("* " + number);
    }

}
