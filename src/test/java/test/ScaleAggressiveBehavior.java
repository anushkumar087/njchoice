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
public class ScaleAggressiveBehavior extends Base{

	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	public ScaleAggressiveBehavior(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleAggressiveBehavior(DataFile.ScaleAggressiveBehavior_DataFile);
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
	
	
	@Test(dataProvider="AggressiveBehaviorScale", priority=6)
	void scaleAggressiveBehavior(String ie3b, String ie3c, String ie3d, String ie3e, String output)
	{
		Reusables.setIOMapping(IOMapping.getABSInputs(),IOMapping.getABSOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		njChoicePage.moodAndBehaviorSectionE.click();
		
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE3b, ie3b);
		command.sendKeysWithBuffer(moodAndBehavior.iE3c, ie3c);
		command.sendKeysWithBuffer(moodAndBehavior.iE3d, ie3d);
		command.sendKeysWithBuffer(moodAndBehavior.iE3e, ie3e);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.aggressiveBehaviorValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="AggressiveBehaviorScale")
	public static Object[][] getTestDataDRS() throws IOException
	{
		String[] requiredInputs = IOMapping.getABSInputs();
		String output = IOMapping.getABSOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleAggressiveBehavior_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
