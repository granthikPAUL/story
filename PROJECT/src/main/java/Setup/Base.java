package Setup;

import java.io.IOException;
//import java.io.File;
//import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
//import Utils.DateUtils;
import Utils.ExtentReportManager;

import Webpages.Homepage;

public class Base extends Utils.customizeHtmlReport {

	public static WebDriver driver;
	WebDriverWait wait;
	String filePath = System.getProperty("user.dir") + "\\mainResources1\\properties\\config.properties";
	public static Properties prop;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public static ExtentTest logger;
	public static int indexSI = 1;
	public static String browserName;
	// Creating softAssert object
	SoftAssert softAssert = new SoftAssert();

	// Invoking MainSpring Website
	@Test
	public WebDriver openBrowser() throws IOException {

		try {
			prop = ReadProperties.readFile(filePath);
			
			// To Invoke the browser
			driver = DriverSetup.getWebDriver(browserName);
			driver.manage().window().maximize();
		
			wait = new WebDriverWait(driver, 60);
			reportPass("This browser: " + browserName + " is opened successfully");
			updateResult(indexSI++, "The Browser is invoked successfully", "Pass", "Test "+indexSI);
		} catch (Exception e) {
			updateResult(indexSI++, "The Browser is not invoked successfully", "Fail", "Test "+indexSI);
			e.printStackTrace();
		}

		try {
			openUrl();
			Login();
			updateResult(indexSI++, "The MainSpring wesbite Homepage is invoked successfully", "Pass", "Test "+indexSI);

		} catch (Exception e) {
			updateResult(indexSI++, "The MainSpring wesbite Homepage is not invoked successfully", "Fail",  "Test "+indexSI);
			e.printStackTrace();
		}

		return driver;
	}

	public void openUrl() {

		// To open the Url
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
		reportPass(prop.getProperty("url") + " is opened successfully");

	}

	public void Login() throws Exception {
		try {
			enterEmail();
			clickEmailNext();
			enterPassword();
			clickSignIn();

			// Pause the program to enter the OTP
			Thread.sleep(5000);
			enterOTP();
			// verifyotp();
			acceptSignin();
			// waiting for to login
			wait.until(ExpectedConditions.titleIs("mainspring"));
			// nextPage();
//			updateResult(indexSI++, "The element has been located successfully", "Pass", "Test 2");
		} catch (Exception e) {
//			updateResult(indexSI++, "The element has not been located successfully", "Fail", "Test 2");
		}
	}

	public void enterEmail() throws Exception {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("loginHeader_Xpath"))));

		logger.log(Status.INFO, "Entering required Email ID");
		WebElement element = driver.findElement(By.xpath(prop.getProperty("email_Xpath")));
		element.sendKeys((prop.getProperty("email")));
		reportPass("Email entered successfully");

	}

	public void clickEmailNext() throws Exception {

		logger.log(Status.INFO, "Clicking on Email Next Button");
		WebElement element = driver.findElement(By.xpath(prop.getProperty("emailNext_Xpath")));
		element.click();
		reportPass("Email Next Button clicked successfully");

	}

	public void enterPassword() throws Exception {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("password_Xpath"))));

		logger.log(Status.INFO, "Entering Password");
		WebElement element = driver.findElement(By.xpath(prop.getProperty("password_Xpath")));
		element.sendKeys(prop.getProperty("password"));
		reportPass("Entered password successfully");

	}

	public void clickSignIn() throws Exception {

		logger.log(Status.INFO, "Clicking on Sign In Button");
		WebElement element = driver.findElement(By.xpath(prop.getProperty("passwordSignin_Xpath")));
		element.click();
		reportPass("Clicked on Sign In Button successfully");

	}

	public void enterOTP() {

		logger.log(Status.INFO, "Entering OTP");
		WebDriverWait wait2 = new WebDriverWait(driver, -1);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("otp_Xpath"))));
		reportPass("Entered OTP successfully");

	}

	public void verifyotp() throws Exception {

		logger.log(Status.INFO, "Verifying OTP");
		WebElement element = driver.findElement(By.xpath("verifyotp_xpath"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		reportPass("Verified OTP successfully");

	}

	public void acceptSignin() throws Exception {
		WebDriverWait wait2 = new WebDriverWait(driver, 30);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("staySignedInMsg_Xpath"))));

		logger.log(Status.INFO, "Accepting Sign In");
		WebElement element = driver.findElement(By.xpath(prop.getProperty("signedInSubmit_Xpath")));
		element.click();
		reportPass("Sign In accepted successfully");

	}

	// Reporting functions
	public static void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		Assert.fail(reportString);

	}

	public static void reportPass(String reportString) {

		logger.log(Status.PASS, reportString);

	}

	public static Homepage nextPage1() {
		// sending driver to next page
		return PageFactory.initElements(driver, Homepage.class);
	}

	public void closeBrowser() throws IOException {
		try {
			logger.log(Status.INFO,"closing browser");
			logger.log(Status.PASS,"closed");
			driver.close();
//			updateResult(indexSI++, "The Browser is closed successfully", "Pass", "Test 11");

		} catch (Exception e) {
//			updateResult(indexSI++, "The Browser is not closed successfully", "Fail", "Test 11");
			e.printStackTrace();
		}
	}
}
