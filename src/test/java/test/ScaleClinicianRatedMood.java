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
public class ScaleClinicianRatedMood  extends Base{

	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	public ScaleClinicianRatedMood(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleClinicianRatedMood(DataFile.ScaleClinicianRatedMood_DataFile);
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
	
	
	@Test(dataProvider="ClinicianRatedMoodScale", priority=7)
	void scaleClinicianRatedMood(String ie1e, String ie1f, String ie1i, String ie1k, String output)
	{
		Reusables.setIOMapping(IOMapping.getClinicianRatedMoodInputs(),IOMapping.getClinicianRatedMoodOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		njChoicePage.moodAndBehaviorSectionE.click();
		
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE1e, ie1e);
		command.sendKeysWithBuffer(moodAndBehavior.iE1f, ie1f);
		command.sendKeysWithBuffer(moodAndBehavior.iE1i, ie1i);
		command.sendKeysWithBuffer(moodAndBehavior.iE1k, ie1k);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		command.waitAfterNavigatingToCapsAndAlgoTab();
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		capsAlgoPage.refreshButton.click();
		command.waitAfterClickingRefreshOnCapsAndAlgoTab();
		
		Reusables.validateOutput(capsAlgoPage.clinicianRatedMoodValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
		
	}
	
	
	@DataProvider(name="ClinicianRatedMoodScale")
	public static Object[][] getTestDataClinicianRatedMoodScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getClinicianRatedMoodInputs();
		String output = IOMapping.getClinicianRatedMoodOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleClinicianRatedMood_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
