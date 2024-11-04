package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SJ_05_Handle_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Default_Dropdown() {
		driver.get("https://rode.com/en/support/where-to-buy");

		String country = "Vietnam", city = "Da Nang";

		Select select = new Select(driver.findElement(By.xpath("//select[@id='country']")));

		Assert.assertFalse(select.isMultiple());

		select.selectByVisibleText(country);

		Assert.assertEquals(select.getFirstSelectedOption().getText(), country);

		driver.findElement(By.xpath("//input[@id='map_search_query']")).sendKeys(city);

		driver.findElement(By.xpath("//button[text()='Search']")).click();

		List<WebElement> addresses = driver.findElements(By.xpath("//h3[text()='Dealers']/parent::div//div[1]/p"));
		for (WebElement address : addresses) {
			Assert.assertTrue(address.getText().contains(country));
			Assert.assertTrue(address.getText().contains(city));
		}
	}

	@Test
	public void TC_02_Custom_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		String value = "Christian";

		selectOptionInCustomDropdown("//i[@class='dropdown icon']", "//div[@class='visible menu transition']/div/span", value);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), value);
	}

	@Test
	public void TC_03_Editable_Dropdown() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		String value1 = "Argentina";
		String value2 = "Australia";

		selectOptionInCustomDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", value1);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), value1);

		selectOptionInEditableDropdown("//input[@class='search']", "//div[@class='visible menu transition']/div/span", value2);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), value2);
	}

	@Test
	public void TC_04_MultiSelect_Dropdown() {
		driver.get("https://demos.telerik.com/kendo-ui/multiselect/index");

		String[] values = { "Cycling", "Swimming", "Boxing" };

		selectOptionsInMultiSelectDropdown("//input[@role='combobox']", "//ul[@id='multiselect_listbox']/li/span", values);

		Assert.assertTrue(verifySelectedOptionsInMultiSelectDropdown("//div[@class='k-input-values']//span[@class='k-chip-label']", values));
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

	public void selectOptionInCustomDropdown(String dropdownXPath, String allOptionsXPath, String expectedOption) {
		driver.findElement(By.xpath(dropdownXPath)).click();
		sleepForSeconds(1);
		List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedOption)) {
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", option);
				sleepForSeconds(1);
				option.click();
				sleepForSeconds(1);
				break;
			}
		}
	}

	public void selectOptionInEditableDropdown(String dropdownXPath, String allOptionsXPath, String expectedOption) {
		WebElement dropdown = driver.findElement(By.xpath(dropdownXPath));
		dropdown.clear();
		dropdown.sendKeys(expectedOption);
		sleepForSeconds(1);
		List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
		for (WebElement option : allOptions) {
			if (option.getText().trim().equals(expectedOption)) {
				option.click();
				sleepForSeconds(1);
				break;
			}
		}
	}

	public void selectOptionsInMultiSelectDropdown(String dropdownXPath, String allOptionsXPath, String[] expectedOptions) {
		for (String expectedOption : expectedOptions) {
			driver.findElement(By.xpath(dropdownXPath)).click();
			sleepForSeconds(1);
			List<WebElement> allOptions = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allOptionsXPath)));
			for (WebElement option : allOptions) {
				if (option.getText().trim().equals(expectedOption)) {
					option.click();
					sleepForSeconds(1);
					break;
				}
			}
		}
	}

	public boolean verifySelectedOptionsInMultiSelectDropdown(String actualSelectedXPath, String[] expectedOptions) {
		List<WebElement> actualSelected = driver.findElements(By.xpath(actualSelectedXPath));
		if (actualSelected.size() != expectedOptions.length) {
			System.out.println("Number of values is not correct");
			return false;
		}
		for (int i = 0; i < actualSelected.size(); i++) {
			String actualText = actualSelected.get(i).getText();
			if (!actualText.equals(expectedOptions[i])) {
				System.out.println(actualText + " does not equal " + expectedOptions[i]);
				return false;
			}
		}
		System.out.println("Actual values match the Expected values");
		return true;
	}

}
