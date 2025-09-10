package webdriver;

import org.openqa.selenium.By;
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

public class S_05_Custom_DropDown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Select_DropDown() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        selectOptionInCustomDropDown("//span[@id='speed-button']", "//ul[@id='speed-menu']/li[@class='ui-menu-item']/div", "Faster");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']/span[@class='ui-selectmenu-text']")).getText(), "Faster");

        selectOptionInCustomDropDown("//span[@id='files-button']", "//ul[@id='files-menu']/li[@class='ui-menu-item']/div", "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']/span[@class='ui-selectmenu-text']")).getText(), "ui.jQuery.js");

        selectOptionInCustomDropDown("//span[@id='number-button']", "//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "10");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(), "10");

        selectOptionInCustomDropDown("//span[@id='salutation-button']", "//ul[@id='salutation-menu']/li[@class='ui-menu-item']/div", "Prof.");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']/span[@class='ui-selectmenu-text']")).getText(), "Prof.");
    }

    @Test
    public void TC_02_Editable_DropDown() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        sendKeysAndSelectOptionInEditableDropDown("//div[@role='combobox']/input", "//div[@class='visible menu transition']//span", "Argentina");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@class='search']/following-sibling::div[@class='divider text']")).getText(), "Argentina");

        sendKeysAndSelectOptionInEditableDropDown("//div[@role='combobox']/input", "//div[@class='visible menu transition']//span", "Australia");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@class='search']/following-sibling::div[@class='divider text']")).getText(), "Australia");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void selectOptionInCustomDropDown(String dropDownXPath, String allOptionsXPath, String optionValue) {
        driver.findElement(By.xpath(dropDownXPath)).click();
        sleepForSeconds(1);
        List<WebElement> options = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
        for (WebElement option : options) {
            if (option.getText().equals(optionValue)) {
                option.click();
                sleepForSeconds(1);
                break;
            }
        }
    }

    public void sendKeysAndSelectOptionInEditableDropDown(String dropDownXPath, String allOptionsXPath, String optionValue) {
        WebElement editableDropDown = driver.findElement(By.xpath(dropDownXPath));
        editableDropDown.clear();
        editableDropDown.sendKeys(optionValue);
        sleepForSeconds(1);
        List<WebElement> options = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
        for (WebElement option : options) {
            if (option.getText().equals(optionValue)) {
                option.click();
                sleepForSeconds(1);
                break;
            }
        }
    }

    public void sleepForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
