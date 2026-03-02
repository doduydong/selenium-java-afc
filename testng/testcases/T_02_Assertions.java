package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class T_02_Assertions {
    @Test
    public void Test_01() {
        Assert.assertTrue(13 > 10);
    }

    @Test
    public void Test_02() {
        Assert.assertFalse(13 < 10);
    }

    @Test
    public void Test_03() {
        String project = "Selenium Java";

        Assert.assertEquals(project.toUpperCase(), "SELENIUM JAVA");
    }
}
