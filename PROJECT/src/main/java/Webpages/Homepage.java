package Webpages;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import Setup.ReadProperties;


public class Homepage extends Setup.Base {

	public static WebDriver driver;
	Properties prop;
	Actions action;
	WebDriverWait wait;
	String filePath=System.getProperty("user.dir") + "\\mainResources1\\properties\\Homepage.properties";
	
	private static By project= By.xpath("//a[contains(text(),'CFO_Onsite')]");
	
	public Homepage() 
	{
		
	}
	public Homepage(WebDriver driver)
	{
		Homepage.driver=driver;
		prop=ReadProperties.readFile(filePath);
		action=new Actions(driver);
		wait=new WebDriverWait(driver, 10);
	}
	//Getting the values for given key from property file
		public String getPropertyValue(String key)
		{
			return prop.getProperty(key);
		}
		
		//To get the web element 
		public WebElement getElement(String path)
		{
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(path))));
			return driver.findElement(By.xpath(path));
			
		}
		
		//Opening the Menu bar
		public void openMenu() throws IOException
		{
			try{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("menubar"))));
			
			logger.log(Status.INFO, "Moving on Menu Bar option");
			action.moveToElement(getElement(getPropertyValue("menubar"))).build().perform();
			
			reportPass("Moved on Menu Bar option successfully");
			updateResult(indexSI++,"Menu has been opened successfully","Pass", "Test "+indexSI);
			}
			catch(Exception e){
				updateResult(indexSI++,"Menu has been opened successfully","Fail", "Test "+indexSI);
			}
		}
		

		//Clicking on the project
		public void openProject() throws IOException
		{
			try{
				logger.log(Status.INFO, "Clicking on Project");
				System.out.println("1");
				driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/span")).click();
				System.out.println("5");
				driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/span")).click();
				System.out.println("3");
				driver.findElement(By.xpath("//*[@id=\"projectIcon\"]/ul/li[1]/a")).click();
				System.out.println("2");
				reportPass("clicked");
				
			updateResult(indexSI++,"Project has been opened successfully","Pass", "Test "+indexSI);
			}
			catch(Exception e){
				updateResult(indexSI++,"Project has been opened successfully","Fail", "Test "+indexSI);
			}
		}
		
		//Navigating to next page
		public static ProjectPage nextPage()
		{
			return PageFactory.initElements(driver, ProjectPage.class);
		}
}