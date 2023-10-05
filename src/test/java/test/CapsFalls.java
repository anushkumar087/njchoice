package test;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
public class CapsFalls extends Base{


	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	public CapsFalls(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsFalls(DataFile.CapsFalls_DataFile);
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
	
	
	@Test(dataProvider="CapsFalls", priority=4)
	void capsFalls(String ij1, String output)
	{	
		Reusables.setIOMapping(IOMapping.getCapsFallsInputs(),IOMapping.getCapsFallsOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ1, ij1);
		
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
		
		Reusables.validateOutput(capsAlgoPage.fallsValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		
	}
	
	
	@DataProvider(name="CapsFalls")
	public static Object[][] getTestDataCapsFalls() throws IOException
	{
		String[] requiredInputs = IOMapping.getCapsFallsInputs();
		String output = IOMapping.getCapsFallsOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsFalls_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
