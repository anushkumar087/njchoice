package test;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Base;
import pagefactory.CapsAndAlgoValuesPage;
import pagefactory.HealthConditionsPage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.ReferralPage;
import util.OptimizedCommands;
import util.DataFile;
import util.AppCommonActions;
import util.IOMapping;
import util.TestData;

public class TestSample extends Base{

	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	@BeforeTest
	public void setUp()
	{
		base = new Base();
		commonActions = new AppCommonActions();
		command = new OptimizedCommands();
		
		base.initialize();
		commonActions.loginToSalesForce(driver);
		commonActions.navigateToNJChoice(driver);
		
		njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);
		command.waitTillElementDisplayed(driver, njChoicePage.CreateNJChoiceAssessmentHeader, 60);
		
		// Fill Up Section A
		njChoicePage.referralSectionA.click();
		referralPage = PageFactory.initElements(driver, ReferralPage.class);
		referralPage.enterAllMandatoryFields();
		referralPage.SaveAndNextButton.click();
		commonActions.handleAlertsAfterSaveReferralSection(driver, command);
		
	}
	
	@Test(dataProvider="CardioCAP")
	void capsCardio(String ij2c, String ij2e, String ij3, String ij8a, String ij8b, String output)
	{	
		njChoicePage.njChoiceAssessmentTab.click();
		
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ2c, ij2c);
		command.sendKeysWithBuffer(healthConditions.iJ2e, ij2e);
		command.sendKeysWithBuffer(healthConditions.iJ3, ij3);
		command.sendKeysWithBuffer(healthConditions.iJ8a, ij8a);
		command.sendKeysWithBuffer(healthConditions.iJ8b, ij8b);
		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		// Wait for 8 secs
		try{Thread.sleep(8000);}catch(Exception e) {}
		
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		
		command.waitForAnyAlertAndAccept(driver, 20);
		
		capsAlgoPage.refreshButton.click();
		
		command.waitForAnyAlertAndAccept(driver, 20);
		
		// Wait for 3 secs
		try{Thread.sleep(3000);}catch(Exception e) {}
		
		String cCardioOutputActual = capsAlgoPage.cCARDIOValue.getText();
		
		Assert.assertTrue(output.startsWith(cCardioOutputActual));
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		
	}
	
	
	@Test(dataProvider="DepressionScale")
	void scaleDRSDepression(String ie1a, String ie1b, String ie1c, String ie1d, String ie1e, String ie1f, String ie1g, String output)
	{
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		njChoicePage.moodAndBehaviorSectionE.click();
		
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE1a, ie1a);
		command.sendKeysWithBuffer(moodAndBehavior.iE1b, ie1b);
		command.sendKeysWithBuffer(moodAndBehavior.iE1c, ie1c);
		command.sendKeysWithBuffer(moodAndBehavior.iE1d, ie1d);
		command.sendKeysWithBuffer(moodAndBehavior.iE1e, ie1e);
		command.sendKeysWithBuffer(moodAndBehavior.iE1f, ie1f);
		command.sendKeysWithBuffer(moodAndBehavior.iE1g, ie1g);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		// Wait for 8 secs
		try{Thread.sleep(8000);}catch(Exception e) {}
		
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		
		command.waitForAnyAlertAndAccept(driver, 20);
		
		capsAlgoPage.refreshButton.click();
		
		command.waitForAnyAlertAndAccept(driver, 20);
		
		// Wait for 3 secs
		try{Thread.sleep(3000);}catch(Exception e) {}
		
		String sDRSOutputActual = capsAlgoPage.sDRSValue.getText();
		
		Assert.assertTrue(output.startsWith(sDRSOutputActual));
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		
	}
	
	
	@Test(dataProvider="SmokingAndDrinkingScale")
	void capsCAddSmokingAndDrinking(String ij8a, String ij8b, String output)
	{
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ8a, ij8a);
		command.sendKeysWithBuffer(healthConditions.iJ8b, ij8b);

		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		// Wait for 8 secs
		try{Thread.sleep(8000);}catch(Exception e) {}
		
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		
		command.waitForAnyAlertAndAccept(driver, 20);
		
		capsAlgoPage.refreshButton.click();
		
		command.waitForAnyAlertAndAccept(driver, 20);
		
		// Wait for 3 secs
		try{Thread.sleep(3000);}catch(Exception e) {}
		
		String cAddOutputActual = capsAlgoPage.CAddValue.getText();
		
		Assert.assertTrue(output.startsWith(cAddOutputActual));
		
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		
	}
	
	@DataProvider(name="CardioCAP")
	public static Object[][] getTestDataCardio() throws IOException
	{
		String[] requiredInputs = IOMapping.getCapsCardioInputs();
		String output = IOMapping.getCapsCardioOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output);
		return testData;
		
	}
	
	@DataProvider(name="DepressionScale")
	public static Object[][] getTestDataDRS() throws IOException
	{
		String[] requiredInputs = IOMapping.getScaleDRSInputs();
		String output = IOMapping.getScaleDRSOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output);
		return testData;
		
	}
	
	@DataProvider(name="SmokingAndDrinkingScale")
	public static Object[][] getTestDataCAdd() throws IOException
	{
		String[] requiredInputs = IOMapping.getCAddInputs();
		String output = IOMapping.getCAddOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output);
		return testData;
		
	}
	
	
	@AfterTest
	public void tearDown()
	{
//		driver.quit();
	}

}
