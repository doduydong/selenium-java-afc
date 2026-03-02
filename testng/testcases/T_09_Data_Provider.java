package testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class T_09_Data_Provider {

    @Test(dataProvider = "user_data")
    public void Test_01(String id, String role) {
        System.out.println("User ID: " + id + " | Role: " + role);
    }

    @DataProvider(name = "user_data")
    public Object[][] data() {
        return new Object[][]{
                // #1
                {"AFC-01", "Manual Tester"},
                // #2
                {"AFC-02", "Automation Tester"},
                // #3
                {"AFC-03", "SDET"}};
    }
}
