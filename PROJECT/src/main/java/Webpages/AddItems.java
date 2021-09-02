package Webpages;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import Setup.ReadProperties;

public class AddItems extends Setup.Base {

	static WebDriver driver;
	Properties prop;
	Properties dataValid;
	Properties dataInvalid;
	WebDriverWait wait;

	String filePath1 = System.getProperty("user.dir") + "\\mainResources1\\properties\\AddItems.properties";
	String filePath2 = System.getProperty("user.dir") + "\\mainResources1\\properties\\ValidDataFile.properties";
	String filePath3 = System.getProperty("user.dir") + "\\mainResources1\\properties\\InvalidDataFile.properties";

	// Receiving the driver instance using consturctor
	public AddItems(WebDriver driver) {
		AddItems.driver = driver;
		prop = ReadProperties.readFile(filePath1);
		dataValid = ReadProperties.readFile(filePath2);
		// dataInvalid = ReadProperties.readFile(filePath3);
		wait = new WebDriverWait(driver, 10);
	}

	// Create new Risk Item
	public void addRiskItem() throws IOException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click()", getElement(getPropertyValue("addbutton")));
			wait.until(ExpectedConditions.titleContains("Risks List"));
			updateResult(indexSI++, "The New Risk has been created successfully", "Pass",  "Test "+indexSI);
		} catch (Exception e) {
			updateResult(indexSI++, "The New Risk has not been created successfully", "Fail",  "Test "+indexSI);
		}
	}

	// getting into frame
	public void switchFrame() throws IOException {
		try {
			driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			driver.switchTo().frame(getElement(getPropertyValue("middleframe")));
			updateResult(indexSI++, "The Window has been switched to iframe successfully", "Pass",  "Test "+indexSI);
		} catch (Exception e) {
			updateResult(indexSI++, "The Window has not been switched to iframe successfully", "Fail",  "Test "+indexSI);
		}

	}

	// coming back from iframe
	public void parentFrame() {
		driver.switchTo().defaultContent();
	}

	// Getting the values for given key from property file
	public String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

	public String getValidData(String key) {
		return dataValid.getProperty(key);
	}

	public String getInvalidData(String key) {
		return dataInvalid.getProperty(key);
	}

	// locating the element
	public WebElement getElement(String path) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
		return driver.findElement(By.xpath(path));
	}

	// locating name input box
	public WebElement getName() {
		return getElement(getPropertyValue("name"));
	}

	// locating description input box
	public WebElement getDescription() {
		return getElement(getPropertyValue("description"));
	}

	// locating Target closure date field
	public WebElement getTargetClosureDate() {

		return getElement(getPropertyValue("TargetClosureDate"));
	}

	// selecting the dropdown value based on input
	public void selectDropDown(String value, String path) {
		Select sc = new Select(getElement(path));
		sc.selectByVisibleText(value);
	}

	// locating and clicking on save button to create actionitem
	public void saveButton() throws IOException {

		try {
			wait.until(ExpectedConditions.titleContains("Risk"));

			logger.log(Status.INFO, "After entering all the required details, clicking on Save Button now.");
			WebElement element = getElement(getPropertyValue("savebutton"));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
			reportPass("Clicked on Save Button successfully");
			updateResult(indexSI++, "The Risk Test Data has been saveed successfully", "Pass",  "Test "+indexSI);
		} catch (Exception e) {
			updateResult(indexSI++, "The Risk Test Data has not been saved successfully", "Fail",  "Test "+indexSI);
		}

		try {
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				Alert alert = driver.switchTo().alert();
				String msg = alert.getText();
				Assert.assertEquals("Please enter Target Closure Date later than Date Identified.", msg,
						"Alert Message Incorrect:");
				System.out.println(msg);
				alert.accept();
			}
		} catch (Exception e) {
		}
		parentFrame();

	}

	// locating the return button to go back
	public void returnBack() throws IOException {

		wait.until(ExpectedConditions.titleContains("Risk"));
		switchFrame();
		logger.log(Status.INFO, "Clicking on Cancel Button now.");
		getElement(getPropertyValue("cancelbutton")).click();
		reportPass("Returned back successfully");

		try {
			if (wait.until(ExpectedConditions.alertIsPresent()) != null) {
				Alert alert = driver.switchTo().alert();
				String actual = alert.getText();
				Assert.assertEquals(actual, "The changes made to the eForm will be lost if you exit without saving.",
						"Incorrect Alert Message");
				alert.accept();
			}
		} catch (Exception e) {

		}
		parentFrame();

	}

}