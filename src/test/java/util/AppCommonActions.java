package util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.Base;
import pagefactory.LoginPage;
import pagefactory.NJChoicePage;
import pagefactory.RegistrationPage;

public class AppCommonActions {
	
	OptimizedCommands command;
	LoginPage login;
	RegistrationPage register;
	NJChoicePage njChoicePage;
	
	public AppCommonActions()
	{
		command = new OptimizedCommands();
	}
	
	public void loginToSalesForce(WebDriver driver)
	{
		login = PageFactory.initElements(driver, LoginPage.class);
		login.loginToSandbox();
		
		register = PageFactory.initElements(driver, RegistrationPage.class);
		register.remindMeLaterLink.click();
		
	}
	
	public void navigateToNJChoice(WebDriver driver)
	{
		driver.navigate().to(Base.prop.getProperty("NJChoiceURL").toString());
		njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);
		command.waitTillElementDisplayed(driver, njChoicePage.CreateNJChoiceAssessmentHeader, 60);
	}
	
	public void handleAlertsAfterSaveReferralSection(WebDriver driver, OptimizedCommands command)
	{
		boolean noErrorsReportedAlert = command.waitForAlertAndAccept(driver, DataFile.noErrorsReportedAlert, 20);
		Assert.assertTrue(noErrorsReportedAlert);
		
		boolean informationSavedAlert = command.waitForAlertAndAccept(driver, DataFile.informationSavedAlert, 20);
		Assert.assertTrue(informationSavedAlert);
		
		String njChoiceUniqueId = command.getAlertText(driver, 20);
		
		boolean someAlert = command.waitForAnyAlertAndAccept(driver, 20);
		Assert.assertTrue(someAlert);
		
		System.out.println("njChoiceUniqueId = "+njChoiceUniqueId);
	}
	
	public void handleAlertsAfterSaveOrUpdateInputsSection(WebDriver driver, OptimizedCommands command)
	{
		
		boolean informationSavedAlert = command.waitForAlertAndAccept(driver, DataFile.informationSavedAlert, 15);
		boolean informationUpdatedAlert = command.waitForAlertAndAccept(driver, DataFile.informationUpdatedAlert, 15);
		Assert.assertTrue(informationSavedAlert||informationUpdatedAlert);
		
		boolean someAlert = command.waitForAnyAlertAndAccept(driver, 20);
		Assert.assertTrue(someAlert);
		
	}


}
