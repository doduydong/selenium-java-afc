package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class S_05_TextBox_TextArea {
    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("http://live.techpanda.org/");

        firstName = "Dong";
        lastName = "Do";
        fullName = firstName + " " + lastName;
        emailAddress = "dong.afc" + getRandomNumber() + "@gmail.com";
        password = "SeJava4@";
    }

    @Test
    public void TC_01_TextBox_TextArea() {
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);

        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);

        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(emailAddress);

        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName + "!");

        String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfo.contains(fullName));
        Assert.assertTrue(contactInfo.contains(fullName));

        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"), firstName);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"), lastName);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"), emailAddress);

        driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();

        driver.findElement(By.xpath("//h2[@class='product-name']/a[text()='IPhone']")).click();

        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.xpath("//input[@id='Quality 1_5']")).click();

        driver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("Sleek design\nSmooth performance\nEasy to use");

        driver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys("Fast, simple, reliable");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='nickname_field']")).getAttribute("value"), firstName);

        driver.findElement(By.xpath("//button[@title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Your review has been accepted for moderation.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }
}
