package Webpages;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Setup.ReadProperties;

public class ProjectPage extends Setup.Base {

	public static WebDriver driver;
	Properties prop;
	Actions action;
	WebDriverWait wait;
	String filePath = System.getProperty("user.dir") + "\\mainResources1\\properties\\ProjectPage.properties";

	public ProjectPage() {

	}

	// Receiving the driver instance using constructor
	public ProjectPage(WebDriver driver) {
		ProjectPage.driver = driver;
		prop = ReadProperties.readFile(filePath);
		action = new Actions(driver);
		wait = new WebDriverWait(driver, 60);

	}

	// Getting the values for given key from property file
	public String getPropertyValue(String key) {
		return prop.getProperty(key);
	}

	// To get the element
	public WebElement getElement(String path) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
		return driver.findElement(By.xpath(path));

	}

	// Opening the Menu bar
	public void openMonitorMenu() throws IOException {
		try {
			logger.log(Status.INFO, "opening monitor menu");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getPropertyValue("monitormenu"))));
	
			WebElement execute = driver.findElement(By.xpath(getPropertyValue("monitormenu")));
			action.moveToElement(execute).build().perform();
		reportPass("clicked");
		updateResult(indexSI++,"The Monitor Button has been located successfully","Pass", "Test "+indexSI);
		}
		catch(Exception e){
			updateResult(indexSI++,"The Monitor Button has not been located successfully","Fail", "Test "+indexSI);
		}
	}

	// Clicking on the project
	public void openRiskItem() throws IOException {
		try {
			logger.log(Status.INFO, "opening risk item");
		getElement(getPropertyValue("RiskItem_module")).click();
		wait.until(ExpectedConditions.titleContains("Risks List"));
		reportPass("opend risk item");
		updateResult(indexSI++,"The Risk Button has been clicked successfully","Pass", "Test "+indexSI);
		}
		catch(Exception e){
			updateResult(indexSI++,"The Risk Button has not been clicked successfully","Fail", "Test "+indexSI);
		}
	}

	// navigating to next page
	public static AddItems nextPage() {
		return PageFactory.initElements(driver, AddItems.class);
	}
	public static InvalidAdd nextPage2() {
		return PageFactory.initElements(driver, InvalidAdd.class);
	}
}