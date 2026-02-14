package util;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.Base;
import pagefactory.CapsAndAlgoValuesPage;
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
		
		
	}
	
	public void navigateToNJChoice(WebDriver driver)
	{
		driver.navigate().to(Base.prop.getProperty("NJChoiceURL").toString());
		njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);
		try {
			waitForPageLoadComplete(driver, 15);
			command.waitTillElementDisplayed(driver, njChoicePage.CreateNJChoiceAssessmentHeader, 60);
		}
		catch (TimeoutException | NoSuchElementException e) {
			try {Thread.sleep(10000);} catch (InterruptedException e1) {e1.printStackTrace();}
			showPromptThatEnteringCodeManuallyMissed(driver);
	        throw new RuntimeException(
	            "Required element not found"
	        );
	    }
	}
	
	public void showPromptThatEnteringCodeManuallyMissed(WebDriver driver) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    String script =
	        "var newHeader = document.createElement('header');" +
	        "newHeader.style.position = 'fixed';" +
	        "newHeader.style.top = '0';" +
	        "newHeader.style.left = '0';" +
	        "newHeader.style.right = '0';" +
	        "newHeader.style.backgroundColor = '#fff3cd';" +
	        "newHeader.style.zIndex = '10000';" +
	        "newHeader.style.padding = '20px';" +
	        "newHeader.innerHTML = " +
	        "'<h1 style=\"color:red; text-align:center; font-size:44px; margin:0;\">" +
	        "Manual code entry was missed" +
	        "</h1>' +" +
	        "'<p style=\"text-align:center; font-size:22px; margin:8px 0 0; color:#856404; font-weight:bold;\">" +
	        "The 2-minute time window has expired" +
	        "</p>';" +
	        "document.body.insertBefore(newHeader, document.body.firstChild);";

	    js.executeScript(script);
	}
	
	public void waitForPageLoadComplete(WebDriver driver, int timeoutInSeconds) {
	    new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds))
	        .until(webDriver ->
	            ((JavascriptExecutor) webDriver)
	                .executeScript("return document.readyState")
	                .equals("complete")
	        );
	}
	
	public void handleAlertsAfterSaveReferralSection(WebDriver driver, OptimizedCommands command)
	{
		boolean noErrorsReportedAlert = command.waitForAlertAndAccept(driver, DataFile.noErrorsReportedAlert, 20);
		Assert.assertTrue(noErrorsReportedAlert);
		
		boolean informationUpdatedAlert = command.waitForAlertAndAccept(driver, DataFile.informationUpdatedAlert, 20);
		boolean informationSavedAlert = command.waitForAlertAndAccept(driver, DataFile.informationSavedAlert, 20);
		Assert.assertTrue(informationSavedAlert||informationUpdatedAlert);
		
		String njChoiceUniqueId = command.getAlertText(driver, 20);
		
//		boolean someAlert = command.waitForAnyAlertAndAccept(driver, 20);
//		Assert.assertTrue(someAlert);
		
//		System.out.println("njChoiceUniqueId = "+njChoiceUniqueId);
	}
	
	public void handleAlertsAfterSaveOrUpdateInputsSection(WebDriver driver, OptimizedCommands command)
	{
		
		boolean informationSavedAlert = command.waitForAlertAndAccept(driver, DataFile.informationSavedAlert, 25);
		boolean informationUpdatedAlert = command.waitForAlertAndAccept(driver, DataFile.informationUpdatedAlert, 25);
		boolean incompleteAssessmentAlert = command.waitForAlertAndAccept(driver, DataFile.incompleteAssessmentAlert, 25);
		boolean noErrorReportedAlert = command.waitForAlertAndAccept(driver, DataFile.noErrorReportedAlert, 25);
		Assert.assertTrue(informationSavedAlert||informationUpdatedAlert||incompleteAssessmentAlert||noErrorReportedAlert);
		
		command.waitForAnyAlertAndAccept(driver, 20);
		command.waitForAnyAlertAndAccept(driver, 20);
		
	}
	
	public CapsAndAlgoValuesPage navigateToCapsAndAlgoTabAndRefreshValues(WebDriver driver, NJChoicePage njChoicePage, OptimizedCommands command, CapsAndAlgoValuesPage capsAlgoPage)
	{
		// Wait for 3 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		command.waitAfterNavigatingToCapsAndAlgoTab();
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		new Actions(driver).scrollToElement(capsAlgoPage.refreshButton).build().perform();
		capsAlgoPage.refreshButton.click();
		command.waitAfterClickingRefreshOnCapsAndAlgoTab();
		return capsAlgoPage;
	}

	public void refreshAndWaitFornjChoiceAssessmentTab(WebDriver driver, NJChoicePage njChoicePage, OptimizedCommands command) {
		
		driver.navigate().refresh();
		for(int i=0; i<6; i++) {
			try {
				command.waitTillElementClickable(driver, njChoicePage.njChoiceAssessmentTab, 10);
				break;
			}
			catch(Exception e) {
				
			}
		}
		
	}


}
