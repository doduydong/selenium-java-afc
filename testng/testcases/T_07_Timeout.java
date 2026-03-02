package testcases;

import org.testng.annotations.Test;

public class T_07_Timeout {
    @Test(timeOut = 1000)
    public void Test_01() throws InterruptedException {
        Thread.sleep(2000);
    }
}
