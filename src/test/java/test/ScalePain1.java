package test;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Base;
import pagefactory.CapsAndAlgoValuesPage;
import pagefactory.HealthConditionsPage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.ReferralPage;
import util.AppCommonActions;
import util.DataFile;
import util.IOMapping;
import util.OptimizedCommands;
import util.Reusables;
import util.TestData;

@Listeners(listener.ListenerTest.class)
public class ScalePain1 extends Base{

	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	public ScalePain1(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScalePain1(DataFile.ScalePain1_DataFile);
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
	
	
	@Test(dataProvider="ScalePain1", priority=28)
	void scalePain1(String ij5a, String ij5b, String output)
	{	
		Reusables.setIOMapping(IOMapping.getPain1ScaleInputs(),IOMapping.getPain1ScaleOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ5a, ij5a);
		command.sendKeysWithBuffer(healthConditions.iJ5b, ij5b);
		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		command.waitAfterNavigatingToCapsAndAlgoTab();
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		capsAlgoPage.refreshButton.click();
		command.waitAfterClickingRefreshOnCapsAndAlgoTab();
		
		Reusables.validateOutput(capsAlgoPage.pain1ScaleValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="ScalePain1")
	public static Object[][] getTestDataPain1Scale() throws IOException
	{
		String[] requiredInputs = IOMapping.getPain1ScaleInputs();
		String output = IOMapping.getPain1ScaleOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScalePain1_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
