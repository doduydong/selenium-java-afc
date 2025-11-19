package javaBasic;

public class J_08_For_Loop {

    public static void main(String[] args) {
        for (int i = 0; i <= 10; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }

        String[] cities = {"Da Nang", "Ha Noi", "Ho Chi Minh"};

        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals("Ha Noi")) {
                System.out.println("Capital");
                break;
            }
        }

        for (String city : cities) {
            if (city.equals("Ha Noi")) {
                continue;
            }
            System.out.println(city);
        }
    }

}
