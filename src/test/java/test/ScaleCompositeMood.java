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
import pagefactory.CommunicationAndVisionPage;
import pagefactory.FunctionalStatusPage;
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
public class ScaleCompositeMood extends Base{
	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	CommunicationAndVisionPage communicationAndVisionPage;
	FunctionalStatusPage functionalStatusPage;
	
	public ScaleCompositeMood(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCompositeMood(DataFile.ScaleCompositeMood_DataFile);
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
	
	
	@Test(dataProvider="CompositeMoodScale", priority=21)
	void scaleCompositeMood(String iE1e, String iE1f, String iE1i, String iE1k,
			String iE2a, String iE2b, String iE2c,String output)
	{
		Reusables.setIOMapping(IOMapping.getScaleCompositeMoodInputs(),IOMapping.getScaleCompositeMoodOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section E
		njChoicePage.moodAndBehaviorSectionE.click();
				
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE1e, iE1e);
		command.sendKeysWithBuffer(moodAndBehavior.iE1f, iE1f);
		command.sendKeysWithBuffer(moodAndBehavior.iE1i, iE1i);
		command.sendKeysWithBuffer(moodAndBehavior.iE1k, iE1k);
		command.sendKeysWithBuffer(moodAndBehavior.iE2a, iE2a);
		command.sendKeysWithBuffer(moodAndBehavior.iE2b, iE2b);
		command.sendKeysWithBuffer(moodAndBehavior.iE2c, iE2c);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.compositeMoodScaleValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="CompositeMoodScale")
	public static Object[][] getTestDataCompositeMoodScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getScaleCompositeMoodInputs();
		String output = IOMapping.getScaleCompositeMoodOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleCompositeMood_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
