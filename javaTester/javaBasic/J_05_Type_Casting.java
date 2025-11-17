package javaBasic;

public class J_05_Type_Casting {

    public static void main(String[] args) {
        // Widening Casting
        byte bNumber = 123;
        System.out.println(bNumber);

        short sNumber = bNumber;
        System.out.println(sNumber);

        int iNumber = sNumber;
        System.out.println(iNumber);

        long lNumber = iNumber;
        System.out.println(lNumber);

        float fNumber = lNumber;
        System.out.println(fNumber);

        double dNumber = fNumber;
        System.out.println(dNumber);

        // Narrowing Casting
        double dNum = 1310.97;
        System.out.println(dNum);

        float fNum = (float) dNum;
        System.out.println(fNum);

        long lNum = (long) fNum;
        System.out.println(lNum);

        int iNum = (int) lNum;
        System.out.println(iNum);

        short sNum = (short) iNum;
        System.out.println(sNum);

        byte bNum = (byte) sNum;
        System.out.println(bNum);
    }

}
