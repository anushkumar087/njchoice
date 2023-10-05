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
public class ScaleCognitivePerformance extends Base{
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
	
	public ScaleCognitivePerformance(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCommunication(DataFile.ScaleCognitivePerformance_DataFile);
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
	
	
	@Test(dataProvider="CognitivePerformanceScale", priority=15)
	void scaleCognitivePerformance(String iC1, String iC2a, String iD1,  String iG2a, String output)
	{
		Reusables.setIOMapping(IOMapping.getScaleCPSInputs(),IOMapping.getScaleCPSOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section C and D
		njChoicePage.cognitionSectionCandD.click();
		
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iC1, iC1);
		if(!iC1.equals("5"))
		{
			command.sendKeysWithBuffer(communicationAndVisionPage.iC2a, iC2a);
			command.sendKeysWithBuffer(communicationAndVisionPage.iD1, iD1);
		}

		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section G
		njChoicePage.FunctionalStatusSectionGandH.click();
				
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG2a, iG2a);
		
		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.cognitivePerformanceScale,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="CognitivePerformanceScale")
	public static Object[][] getTestDataCognitivePerformanceScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getScaleCPSInputs();
		String output = IOMapping.getScaleCPSOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleCognitivePerformance_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
