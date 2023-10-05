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
import pagefactory.CommunicationAndVisionPage;
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
public class ScaleCommunication extends Base{


	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	CommunicationAndVisionPage communicationAndVisionPage;
	
	public ScaleCommunication(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCommunication(DataFile.ScaleCommunication_DataFile);
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
	
	
	@Test(dataProvider="CommunicationScale", priority=8)
	void scaleCommunication(String iD1, String iD2, String output)
	{
		Reusables.setIOMapping(IOMapping.getSCommInputs(),IOMapping.getSCommOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		njChoicePage.cognitionSectionCandD.click();
		
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iD1, iD1);
		command.sendKeysWithBuffer(communicationAndVisionPage.iD2, iD2);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutputSComm(capsAlgoPage.sCommValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="CommunicationScale")
	public static Object[][] getTestDataDRS() throws IOException
	{
		String[] requiredInputs = IOMapping.getSCommInputs();
		String output = IOMapping.getSCommOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleCommunication_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
