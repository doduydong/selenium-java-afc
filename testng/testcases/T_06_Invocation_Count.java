package testcases;

import org.testng.annotations.Test;

import java.util.Random;

public class T_06_Invocation_Count {
    String emailAddress;

    @Test(invocationCount = 6)
    public void Test() {
        emailAddress = "dong.afc" + getRandomNumber() + "@gmail.com";

        System.out.println(emailAddress);
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }
}
