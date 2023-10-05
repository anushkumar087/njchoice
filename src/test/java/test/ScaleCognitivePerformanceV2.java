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
public class ScaleCognitivePerformanceV2 extends Base{
	
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
	
	public ScaleCognitivePerformanceV2(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCognitivePerformanceV2(DataFile.ScaleCognitivePerformanceV2_DataFile);
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
	
	
	@Test(dataProvider="CognitivePerformanceScaleV2", priority=23)
	void scaleCognitivePerformanceV2(String iC1, String iC2a, String iD1, String iG1cb, 
			String iG1db, String iG2e, String output)
	{
		Reusables.setIOMapping(IOMapping.getCognitivePerformanceV2Inputs(),IOMapping.getCognitivePerformanceV2Output(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section C
		njChoicePage.cognitionSectionCandD.click();
				
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iC1, iC1);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC2a, iC2a);
		command.sendKeysWithBuffer(communicationAndVisionPage.iD1, iD1);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section G
		njChoicePage.FunctionalStatusSectionGandH.click();
				
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG1cb, iG1cb);
		command.sendKeysWithBuffer(functionalStatusPage.iG1db, iG1db);
		command.sendKeysWithBuffer(functionalStatusPage.iG2e, iG2e);
		
		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.cognitivePerformanceScale2Value,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="CognitivePerformanceScaleV2")
	public static Object[][] getTestDataCognitivePerformanceScaleV2() throws IOException
	{
		String[] requiredInputs = IOMapping.getCognitivePerformanceV2Inputs();
		String output = IOMapping.getCognitivePerformanceV2Output();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleCognitivePerformanceV2_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
