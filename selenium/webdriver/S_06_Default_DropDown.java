package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_06_Default_DropDown {
    WebDriver driver;
    Select select;
    String gender, city, district, ward;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://egov.danang.gov.vn/reg");

        gender = "Nam";
        city = "thành phố Đà Nẵng";
        district = "quận Hải Châu";
        ward = "phường Bình Hiên";
    }

    @Test
    public void TC_01_Default_DropDown() {
        select = new Select(driver.findElement(By.cssSelector("select#gioiTinh")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText(gender);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), gender);

        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText(city);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), city);

        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_quanhuyen")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText(district);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), district);

        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_phuongxa")));
        Assert.assertFalse(select.isMultiple());
        select.selectByVisibleText(ward);
        Assert.assertEquals(select.getFirstSelectedOption().getText(), ward);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
