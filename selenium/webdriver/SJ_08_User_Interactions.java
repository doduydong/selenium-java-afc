package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SJ_08_User_Interactions {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
	}

	@Test
	public void TC_01_Move_The_Mouse() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepForSeconds(1);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> selectableNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		action.clickAndHold(selectableNumbers.get(7)).moveToElement(selectableNumbers.get(9)).release().perform();
		sleepForSeconds(1);

		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));

		Assert.assertEquals(selectedNumbers.size(), 6);
	}

	@Test
	public void TC_03_Click_Select_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");

		List<WebElement> selectableNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		action.keyDown(Keys.CONTROL).click(selectableNumbers.get(12)).click(selectableNumbers.get(9)).keyUp(Keys.CONTROL).perform();
		sleepForSeconds(1);

		List<WebElement> selectedNumbers = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));

		Assert.assertEquals(selectedNumbers.size(), 2);
	}

	@Test
	public void TC_04_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement dblClickBtn = driver.findElement(By.xpath("//button[text()='Double click me']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dblClickBtn);
		sleepForSeconds(1);

		action.doubleClick(dblClickBtn).perform();
		sleepForSeconds(1);

		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	}

	@Test
	public void TC_05_Context_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		WebElement quitContextMenu = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));

		Assert.assertFalse(quitContextMenu.isDisplayed());

		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepForSeconds(1);

		Assert.assertTrue(quitContextMenu.isDisplayed());

		action.click(quitContextMenu).perform();
		sleepForSeconds(1);

		Alert alert = driver.switchTo().alert();

		Assert.assertEquals(alert.getText(), "clicked: quit");

		alert.accept();
		sleepForSeconds(1);
	}

	@Test
	public void TC_06_Drag_And_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement target = driver.findElement(By.xpath("//div[@id='droptarget']"));

		Assert.assertEquals(target.getText(), "Drag the small circle here.");

		action.dragAndDrop(source, target).perform();
		sleepForSeconds(1);

		Assert.assertEquals(target.getText(), "You did great!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
