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

public class S_03_TextBox_TextArea {
    WebDriver driver;
    String firstName, lastName, fullName, emailAddress, password;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");

        firstName = "Dong";
        lastName = "Do";
        fullName = firstName + " " + lastName;
        emailAddress = "dong.afc" + getRandomNumber() + "@gmail.com";
        password = "SeJava4@";
    }

    @Test
    public void TC_01_TextBox_TextArea() {
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(firstName);

        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);

        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(emailAddress);

        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(password);

        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(password);

        driver.findElement(By.xpath("//button[@id='register-button']")).click();

        driver.findElement(By.xpath("//a[@class='ico-account']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"), firstName);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"), lastName);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='Contact us']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FullName']")).getAttribute("value"), fullName);

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"), emailAddress);

        driver.findElement(By.xpath("//textarea[@id='Enquiry']")).sendKeys("Selenium\nJava\nAFC");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int getRandomNumber() {
        return new Random().nextInt(10000);
    }

}
