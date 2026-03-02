package testcases;

import org.testng.annotations.Test;

public class T_05_Priority {
    @Test(priority = 1)
    public void Create() {
        System.out.println("Create");
    }

    @Test(priority = 2)
    public void Read() {
        System.out.println("Read");
    }

    @Test(priority = 3)
    public void Update() {
        System.out.println("Update");
    }

    @Test(priority = 4)
    public void Delete() {
        System.out.println("Delete");
    }
}
