package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class S_02_Selenium_Locators {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Locators_By() {
        driver.findElement(By.id("email"));

        driver.findElement(By.className("fb_logo"));

        driver.findElement(By.name("login"));

        driver.findElement(By.tagName("input"));

        driver.findElement(By.linkText("Tiếng Việt"));

        driver.findElement(By.partialLinkText("ếng Việ"));

        driver.findElement(By.xpath("//input[@id='email']"));

        driver.findElement(By.cssSelector("input[id='email']"));
    }

    @Test
    public void TC_02_Relative_Locators() {
        By englishBy = By.xpath("//li[text()='English (UK)']");
        By signUpBy = By.xpath("//a[text()='Sign Up']");
        By messengerBy = By.xpath("//a[text()='Messenger']");
        By threadsBy = By.xpath("//a[text()='Threads']");
        By helpBy = By.xpath("//a[text()='Help']");

        By elementXPath = RelativeLocator.with(By.xpath("//a")).above(threadsBy).below(englishBy).toLeftOf(messengerBy).toRightOf(signUpBy).near(helpBy);
        System.out.println(driver.findElement(elementXPath).getText());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
