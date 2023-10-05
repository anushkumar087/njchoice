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
public class ScaleDepressionSDRS extends Base {
	

	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	public ScaleDepressionSDRS(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleDepressionSDRS(DataFile.ScaleDepressionSDRS_DataFile);
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
	
	
	@Test(dataProvider="DepressionScale", priority=9)
	void scaleDRSDepression(String ie1a, String ie1b, String ie1c, String ie1d, String ie1e, String ie1f, String ie1g, String output)
	{
		Reusables.setIOMapping(IOMapping.getScaleDRSInputs(),IOMapping.getScaleDRSOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
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
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.sDRSValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		
	}
	
	
	@DataProvider(name="DepressionScale")
	public static Object[][] getTestDataDRS() throws IOException
	{
		String[] requiredInputs = IOMapping.getScaleDRSInputs();
		String output = IOMapping.getScaleDRSOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleDepressionSDRS_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
