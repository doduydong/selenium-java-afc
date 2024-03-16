package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class S_13_Handle_Upload_File {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	String javaFile = "java.jpg";
	String cSharpFile = "cSharp.jpg";
	String javaScriptFile = "javaScript.jpg";
	String rubyFile = "ruby.jpg";
	String pythonFile = "python.jpg";

	String javaFilePath = projectPath + "\\uploadFiles\\" + javaFile;
	String cSharpFilePath = projectPath + "\\uploadFiles\\" + cSharpFile;
	String javaScriptFilePath = projectPath + "\\uploadFiles\\" + javaScriptFile;
	String rubyFilePath = projectPath + "\\uploadFiles\\" + rubyFile;
	String pythonFilePath = projectPath + "\\uploadFiles\\" + pythonFile;

	String multipleFilesPath = javaFilePath + "\n" + cSharpFilePath + "\n" + javaScriptFilePath + "\n" + rubyFilePath + "\n" + pythonFilePath;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	}

	@Test
	public void TC_01_Single_File() {
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(javaFilePath);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javaFile + "']")).isDisplayed());

		driver.findElement(By.xpath("//td//button[contains(@class,'start')]")).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + javaFile + "']")).isDisplayed());

		Assert.assertTrue(isImageLoadedByJS("//a[@title='" + javaFile + "']/img"));

		driver.findElement(By.xpath("//td//button[contains(@class,'delete')]")).click();
		sleepInSecond(2);
	}

	@Test
	public void TC_02_Multiple_Files() {
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(multipleFilesPath);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javaFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + cSharpFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + javaScriptFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + rubyFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + pythonFile + "']")).isDisplayed());

		List<WebElement> startBtns = driver.findElements(By.xpath("//td//button[contains(@class,'start')]"));
		for (WebElement btn : startBtns) {
			btn.click();
			sleepInSecond(2);
		}

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + javaFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + cSharpFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + javaScriptFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + rubyFile + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + pythonFile + "']")).isDisplayed());

		Assert.assertTrue(isImageLoadedByJS("//a[@title='" + javaFile + "']/img"));
		Assert.assertTrue(isImageLoadedByJS("//a[@title='" + cSharpFile + "']/img"));
		Assert.assertTrue(isImageLoadedByJS("//a[@title='" + javaScriptFile + "']/img"));
		Assert.assertTrue(isImageLoadedByJS("//a[@title='" + rubyFile + "']/img"));
		Assert.assertTrue(isImageLoadedByJS("//a[@title='" + pythonFile + "']/img"));

		List<WebElement> deleteBtns = driver.findElements(By.xpath("//td//button[contains(@class,'delete')]"));
		for (WebElement btn : deleteBtns) {
			btn.click();
			sleepInSecond(2);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public WebElement getWebElement(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public boolean isImageLoadedByJS(String xpathLocator) {
		return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0;", getWebElement(xpathLocator));
	}

}
