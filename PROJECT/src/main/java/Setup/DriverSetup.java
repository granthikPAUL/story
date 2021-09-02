package Setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class DriverSetup {

	static WebDriver driver;
	
	public static WebDriver getWebDriver(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\mainResources1\\drivers\\chromedriver.exe");
			ChromeOptions co = new ChromeOptions();
			// To disable the infobars
			co.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

			// To disable the notifications
			co.addArguments("--disable-notifications");
			
			driver=new ChromeDriver(co);
			
			return driver;		
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\mainResources1\\drivers\\geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setProfile(new FirefoxProfile());

			// To disable the notifications
			firefoxOptions.addPreference("dom.webnotifications.enabled", false);

			driver = new FirefoxDriver(firefoxOptions);

			return driver;
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir") + "\\mainResources1\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();

			return driver;
		}
		
		
		return null;
	}
}
