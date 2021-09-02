package HomePageTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterSuite;
//import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

//import TestData.ReadTestData;
import Webpages.AddItems;
import Webpages.Homepage;
import Webpages.InvalidAdd;
//import Webpages.ModifyRisk;
import Webpages.ProjectPage;
//import Webpages.ViewRisk;
import Setup.Base;

public class RiskPageTestCases extends Setup.Base {

	/*******************************************************************
	 * Function Name : openBrowserTab() Description : To initialize the browser
	 * 
	 * @throws Exception
	 * 
	 *******************************************************************/
	
	@BeforeSuite
	
	public void openBrowserTab() throws Exception {
		logger = report.createTest("Invoking MainSpring Website on browser");
//		String browser = "chrome";
//		Base bs = new Base();
		Base bs = new Base();
		bs.openBrowser();
//		 bs.nextPage1();
	}

	/*******************************************************************
	 * Function Name : cliseBrowserWindow() Description : To close the Browser
	 * 
	 * @throws IOException
	 * 
	 *******************************************************************/

	@AfterSuite
	public void closeBrowserWindow() throws IOException {

		logger = report.createTest("Closing Browser");
		Base bs = new Base();
		bs.closeBrowser();
		report.flush();
	}

	/*******************************************************************
	 * Function Name : Homepage() Description : Navigate to Action Items Module and
	 * perform actions
	 * 
	 * @throws IOException
	 * 
	 *******************************************************************/
	
	@Test(priority = 0)
	public void Homepage() throws IOException {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		logger = report.createTest("Entering the HomePage as per requirement");
		Homepage hp = Base.nextPage1();
		hp.openMenu();
		hp.openProject();

	}

	/*******************************************************************
	 * Function Name : openProject() Description : Navigate to Monitor and then to
	 * Risk Icon to open the project
	 * 
	 * @throws IOException
	 * 
	 *******************************************************************/
	
	@Test(priority = 1, dependsOnMethods = { "Homepage" })
	public void openProject() throws IOException {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		logger = report.createTest("Opening the Project");
		ProjectPage pg = Homepage.nextPage();
		// pg.verifyProject();
		pg.openMonitorMenu();
		pg.openRiskItem();

	}

	/*******************************************************************
	 * Function Name : SaveButtonWithValidData() Description : To enter the required
	 * details in the Risk Module
	 * 
	 * @throws IOException
	 * 
	 *******************************************************************/

	@Test(priority = 2, dependsOnMethods = { "openProject" })
	public void SaveButtonWithValidData() throws IOException {

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		logger = report.createTest("Entering the Mandatory Data");
		AddItems add = ProjectPage.nextPage();
		add.addRiskItem();
//		add.fillForm();
		add.switchFrame();
		logger.log(Status.INFO, "Entering name in the Name Column");
		add.getName().sendKeys(add.getValidData("Name"));
		reportPass("Name entered successfully");
		logger.log(Status.INFO, "Entering description in the Description Column");
		add.getDescription().sendKeys(add.getValidData("Description"));
		reportPass("Description entered successfully");
		logger.log(Status.INFO, "Selecting the Drop Down value of Selivery Impact");
		add.selectDropDown(add.getValidData("ImpactArea"), add.getPropertyValue("ImpactArea"));
		reportPass("Dropdown Value selected successfully");
		logger.log(Status.INFO, "Selecting the Drop Down value of Causes");

		add.selectDropDown(add.getValidData("Causes"), add.getPropertyValue("Causes"));
		reportPass("Dropdown Value selected successfully");

		logger.log(Status.INFO, "Selecting the Drop Down value of Delivery Impact Score");
		add.selectDropDown(add.getValidData("DeliveryImpactScore"), add.getPropertyValue("DeliveryImpactScore"));
		reportPass("Dropdown Value selected successfully");

		logger.log(Status.INFO, "Selecting the Drop Down value of Likelihood Score");
		reportPass("Dropdown Value selected successfully");
		add.selectDropDown(add.getValidData("LikelihoodScore"), add.getPropertyValue("LikelihoodScore"));

		logger.log(Status.INFO, "Selecting the Drop Down value of Risk stage");
		add.selectDropDown(add.getValidData("RiskStage"), add.getPropertyValue("RiskStage"));
		reportPass("Dropdown Value selected successfully");

		logger.log(Status.INFO, "Entering the Risksource Date");
		add.selectDropDown(add.getValidData("Risksource"), add.getPropertyValue("Risksource"));
		reportPass("Risksource entered successfully");

		logger.log(Status.INFO, "Entering the Target Closure Date");
		add.getTargetClosureDate().sendKeys(add.getValidData("TargetClosureDate"));
		reportPass("Target Closure Date entered successfully");

		add.saveButton();
		add.returnBack();
		driver.close();

	}

	@Test(priority = 3, dependsOnMethods = { "openProject" })
	public void SaveButtonWithInValidData() throws Exception {
		openBrowserTab();
		Homepage();
		openProject();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

		logger = report.createTest("Entering the invalid Data");
		InvalidAdd add = ProjectPage.nextPage2();
		add.addRiskItem();
//		add.fillForm();
		add.switchFrame();
		logger.log(Status.INFO, "Entering name in the Name Column");
		add.getName().sendKeys(add.getValidData("Name"));
		reportPass("Name entered successfully");
		logger.log(Status.INFO, "Entering description in the Description Column");
		add.getDescription().sendKeys(add.getValidData("Description"));
		reportPass("Description entered successfully");
		logger.log(Status.INFO, "Selecting the Drop Down value of Selivery Impact");
		add.selectDropDown(add.getValidData("ImpactArea"), add.getPropertyValue("ImpactArea"));
		reportPass("Dropdown Value selected successfully");
		logger.log(Status.INFO, "Selecting the Drop Down value of Causes");

		add.selectDropDown(add.getValidData("Causes"), add.getPropertyValue("Causes"));
		reportPass("Dropdown Value selected successfully");

		logger.log(Status.INFO, "Selecting the Drop Down value of Delivery Impact Score");
		add.selectDropDown(add.getValidData("DeliveryImpactScore"), add.getPropertyValue("DeliveryImpactScore"));
		reportPass("Dropdown Value selected successfully");

		logger.log(Status.INFO, "Selecting the Drop Down value of Likelihood Score");
		reportPass("Dropdown Value selected successfully");
		add.selectDropDown(add.getValidData("LikelihoodScore"), add.getPropertyValue("LikelihoodScore"));

		logger.log(Status.INFO, "Selecting the Drop Down value of Risk stage");
		add.selectDropDown(add.getValidData("RiskStage"), add.getPropertyValue("RiskStage"));
		reportPass("Dropdown Value selected successfully");

		logger.log(Status.INFO, "Entering the Risksource Date");
		add.selectDropDown(add.getValidData("Risksource"), add.getPropertyValue("Risksource"));
		reportPass("Risksource entered successfully");

		logger.log(Status.INFO, "Entering the invalid Target Closure Date");
		add.getTargetClosureDate().sendKeys(add.getValidData("TargetClosureDate"));
		reportPass("Target Closure Date entered successfully");
		logger.log(Status.INFO, "Alert generated");
		add.saveButton();
		reportPass("Alert handled");
		add.returnBack();

	}

	/*******************************************************************
	 * Function Name : endReport() Description : To print the report
	 * 
	 * @throws IOException
	 * 
	 *******************************************************************/

	// Flushing the report
	@AfterTest
	public void endReport() throws IOException {

		report.flush();
	}

}