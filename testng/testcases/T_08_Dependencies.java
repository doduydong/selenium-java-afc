package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class T_08_Dependencies {
    @Test
    public void Test_01_Create() {
        System.out.println("Create");
    }

    @Test(dependsOnMethods = "Test_01_Create")
    public void Test_02_Read() {
        System.out.println("Read");
    }

    @Test(dependsOnMethods = "Test_02_Read")
    public void Test_03_Update() {
        System.out.println("Update");
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "Test_03_Update")
    public void Test_04_Delete() {
        System.out.println("Delete");
    }
}
