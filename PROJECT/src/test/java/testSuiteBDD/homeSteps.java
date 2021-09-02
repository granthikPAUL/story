package testSuiteBDD;

import HomePageTestCases.RiskPageTestCases;
import io.cucumber.java.en.*;

public class homeSteps {

	RiskPageTestCases test = new RiskPageTestCases();
		
		@Given("^Driver Setup$")
		public void openBrowserTab() throws Exception
		{
			test.openBrowserTab();
		}
		@When ("^Open Homepage$")
		public void openHomepage() throws Exception
		{
			test.Homepage();
		}
		@Then ("^Open Project$")
		public void openProject() throws Exception
		{
			test.openProject();
		}
		@Then ("^Click SubmitButton$")
		public void clickSubmit() throws Exception
		{
			test.SaveButtonWithValidData();
		}
		@Then("^Save Report$")
		public void saveReport() throws Exception
		{
			test.endReport();
		}
		@ Then("Close Browser")
		public void closeBrowser() throws Exception
		{
			test.closeBrowserWindow();
		}
		
}
		