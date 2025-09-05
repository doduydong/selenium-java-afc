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

        By elementBy = RelativeLocator.with(By.xpath("//a")).above(threadsBy).below(englishBy).toLeftOf(messengerBy).toRightOf(signUpBy).near(helpBy);
        System.out.println(driver.findElement(elementBy).getText());
    }

    @Test
    public void TC_03_XPath_CSS() {
        driver.get("https://live.techpanda.org/index.php/mobile.html");

        // XPath
        driver.findElement(By.xpath("//input[@id='search']"));
        driver.findElement(By.xpath("//*[@id='search']"));
        driver.findElement(By.xpath("//input[@*='search']"));

        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));

        driver.findElement(By.xpath("//input[@title='Sign up for our newsletter']"));
        driver.findElement(By.xpath("//input[starts-with(@title,'Sign up')]"));
        driver.findElement(By.xpath("//input[contains(@title,'up for our')]"));
        driver.findElement(By.xpath("//address[starts-with(text(),'© 2015')]"));
        driver.findElement(By.xpath("//address[contains(text(),'Magento Demo Store')]"));
        driver.findElement(By.xpath("//address[contains(string(),'© 2015')]"));
        driver.findElement(By.xpath("//address[contains(.,'Demo Store')]"));

        driver.findElement(By.xpath("//a[@class='product-image' and @title='IPhone']"));
        driver.findElement(By.xpath("//a[@class='product-image' or @title='IPhone']"));
        driver.findElement(By.xpath("//a[@class='product-image' and not(@title='IPhone')]"));

        driver.findElement(By.xpath("//ul[contains(@class,'products-grid')]/li[1]"));
        driver.findElement(By.xpath("//ul[contains(@class,'products-grid')]/li[position()=1]"));
        driver.findElement(By.xpath("//ul[contains(@class,'products-grid')]/li[last()]"));
        driver.findElement(By.xpath("//ul[contains(@class,'products-grid')]/li[last()-1]"));

        driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']/button"));

        // CSS
        driver.findElement(By.cssSelector("li[class='item last']>div a[title='IPhone']"));

        // Id attribute
        driver.findElement(By.cssSelector("input[id='search']"));
        driver.findElement(By.cssSelector("input#search"));
        driver.findElement(By.cssSelector("#search"));

        // Class attribute
        driver.findElement(By.cssSelector("input[class='input-text required-entry validate-email']"));
        driver.findElement(By.cssSelector("input.validate-email"));
        driver.findElement(By.cssSelector(".validate-email"));
        driver.findElement(By.cssSelector(".input-text.required-entry.validate-email"));

        // And, Or, Not
        driver.findElement(By.cssSelector("a[class='product-image'][title='IPhone']"));
        driver.findElement(By.cssSelector("a[class='product-image'],[title='IPhone']"));
        driver.findElement(By.cssSelector("a:not([class='product-image'])[title='IPhone']"));

        // Starts-with
        driver.findElement(By.cssSelector("input[title^='Sign up']"));

        // Contains
        driver.findElement(By.cssSelector("input[title*='up for our']"));

        // Ends-with
        driver.findElement(By.cssSelector("input[title$='our newsletter']"));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
