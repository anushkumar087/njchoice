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
public class ScaleDeafblindSeverityIndex extends Base{
	
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
	
	public ScaleDeafblindSeverityIndex(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCommunication(DataFile.ScaleDeafblindSeverityIndex_DataFile);
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
	
	
	@Test(dataProvider="DeafblindSeverityIndexScale", priority=20)
	void scaleDeafblindSeverityIndex(String iD3,  String iD4, String output)
	{
		Reusables.setIOMapping(IOMapping.getScaleDeafblindSeverityIndexInputs(),IOMapping.getScaleDeafblindSeverityIndexOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section D
		njChoicePage.cognitionSectionCandD.click();
				
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iD3, iD3);
		command.sendKeysWithBuffer(communicationAndVisionPage.iD4, iD4);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.deafblindSeverityIndexValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="DeafblindSeverityIndexScale")
	public static Object[][] getTestDataDeafblindSeverityIndexScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getScaleDeafblindSeverityIndexInputs();
		String output = IOMapping.getScaleDeafblindSeverityIndexOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleDeafblindSeverityIndex_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
