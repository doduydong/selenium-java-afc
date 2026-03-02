package testcases;

import org.testng.annotations.*;

public class T_01_Annotations {
    @BeforeSuite
    public void beforeSuite() {
        System.out.println("BeforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("BeforeClass");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("BeforeMethod");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest");
    }

    @Test
    public void Test_01() {
        System.out.println("Test 01");
    }

    @Test
    public void Test_02() {
        System.out.println("Test 02");
    }
}
