package javaBasic;

public class J_01_Variables {
    String name; // Instance variable
    static int number; // Static variable
    static final String PROJECT = "Selenium Java"; // Constant

    public static void main(String[] args) {
        J_01_Variables obj1 = new J_01_Variables();
        obj1.name = "Dong";
        System.out.println(obj1.name);

        J_01_Variables obj2 = new J_01_Variables();
        obj1.name = "YuDo";
        System.out.println(obj1.name);

        System.out.println(number);
        int number = 10; // Local variable
        System.out.println(number);

        System.out.println(PROJECT);
    }

}
