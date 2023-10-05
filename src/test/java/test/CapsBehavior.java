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
import pagefactory.MedicationsAndTreatmentsPage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.OralAndNutritionalStatusPage;
import pagefactory.ReferralPage;
import util.AppCommonActions;
import util.DataFile;
import util.IOMapping;
import util.OptimizedCommands;
import util.Reusables;
import util.TestData;

@Listeners(listener.ListenerTest.class)
public class CapsBehavior extends Base {
	
	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	OralAndNutritionalStatusPage oralAndNutritionalStatusPage;
	CapsAndAlgoValuesPage capsAlgoPage;
	CommunicationAndVisionPage communicationAndVisionPage;
	FunctionalStatusPage functionalStatusPage;
	MoodAndBehaviorPage moodAndBehaviorPage;
	
	public CapsBehavior(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCommunication(DataFile.CapsBehavior_DataFile);
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
	
	
	@Test(dataProvider="BehaviorCaps", priority=19)
	void capsBehavior(String iE3a, String iE3b, String iE3c, String iE3d, 
			String iE3e, String iE3f, String output)
	{
		Reusables.setIOMapping(IOMapping.getCapsBehaviorInputs(),IOMapping.getCapsBehaviorOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section G and H
		njChoicePage.moodAndBehaviorSectionE.click();
		
		moodAndBehaviorPage = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		moodAndBehaviorPage.clickEditButtonIfDisplayed(moodAndBehaviorPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehaviorPage.iE3a, iE3a);
		command.sendKeysWithBuffer(moodAndBehaviorPage.iE3b, iE3b);
		command.sendKeysWithBuffer(moodAndBehaviorPage.iE3c, iE3c);
		command.sendKeysWithBuffer(moodAndBehaviorPage.iE3d, iE3d);
		command.sendKeysWithBuffer(moodAndBehaviorPage.iE3e, iE3e);
		command.sendKeysWithBuffer(moodAndBehaviorPage.iE3f, iE3f);

		moodAndBehaviorPage.saveOrUpdateAfterEnteringRequiredFields(moodAndBehaviorPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.behaviorCapsValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="BehaviorCaps")
	public static Object[][] getTestDataBehaviorCaps() throws IOException
	{
		String[] requiredInputs = IOMapping.getCapsBehaviorInputs();
		String output = IOMapping.getCapsBehaviorOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsBehavior_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
