package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class S_07_Custom_DropDown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Custom_DropDown() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectOptionInCustomDropDown("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", "Faster");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(), "Faster");

        selectOptionInCustomDropDown("//span[@id='files-button']", "//ul[@id='files-menu']//div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(), "ui.jQuery.js");

        selectOptionInCustomDropDown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(), "10");

        selectOptionInCustomDropDown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']//div", "Prof.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(), "Prof.");
    }

    @Test
    public void TC_02_Editable_DropDown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        selectOptionInEditableDropDown("//input[@class='search']", "Argentina");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Argentina");

        selectOptionInEditableDropDown("//input[@class='search']", "Australia");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Australia");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void selectOptionInCustomDropDown(String dropdownXPath, String allOptionsXPath, String expectedValue) {
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(dropdownXPath))).click();
        List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
        for (WebElement option : allOptions) {
            if (option.getText().equals(expectedValue)) {
                option.click();
                sleepForSeconds(1);
                break;
            }
        }
    }

    public void selectOptionInEditableDropDown(String dropdownXPath, String expectedValue) {
        WebElement dropdown = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownXPath)));
        dropdown.clear();
        dropdown.sendKeys(expectedValue);
        dropdown.sendKeys(Keys.ENTER);
        sleepForSeconds(1);
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
