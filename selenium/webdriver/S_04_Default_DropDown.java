package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_04_Default_DropDown {
    WebDriver driver;
    Select select;
    String country, city;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://rode.com/en-au/support/where-to-buy");

        country = "Vietnam";
        city = "Da Nang";
    }

    @Test
    public void TC_01_Default_DropDown() {
        select = new Select(driver.findElement(By.xpath("//select[@id='country']")));

        Assert.assertFalse(select.isMultiple());

        Assert.assertEquals(select.getOptions().size(), 243);

        Assert.assertEquals(select.getFirstSelectedOption().getText(), "Select country/region from list below");

        select.selectByVisibleText(country);

        Assert.assertEquals(select.getFirstSelectedOption().getText(), country);

        driver.findElement(By.xpath("//input[@id='map_search_query']")).sendKeys(city);

        List<WebElement> dealers = driver.findElements(By.xpath("//h3[text()='Dealers']/parent::div/div/div//i[contains(@class,'map')]/following-sibling::p"));
        for (WebElement dealer : dealers) {
            Assert.assertTrue(dealer.getText().contains(city));
            Assert.assertTrue(dealer.getText().contains(country));
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
