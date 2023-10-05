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
public class ScaleADLHierarchy extends Base{

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
	
	public ScaleADLHierarchy(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleADLHierarchy(DataFile.ScaleADLHierarchy_DataFile);
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
	
	
	@Test(dataProvider="ADLHierarchyScale", priority=24)
	void scaleADLHierarchy(String iG2b, String iG2f, String iG2h, String iG2j, String output)
	{
		Reusables.setIOMapping(IOMapping.getADLHierarchyInputs(),IOMapping.getADLHierarchyOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section G
		njChoicePage.FunctionalStatusSectionGandH.click();
				
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG2b, iG2b);
		command.sendKeysWithBuffer(functionalStatusPage.iG2f, iG2f);
		command.sendKeysWithBuffer(functionalStatusPage.iG2h, iG2h);
		command.sendKeysWithBuffer(functionalStatusPage.iG2j, iG2j);
		
		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.adlHierarchyScaleValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="ADLHierarchyScale")
	public static Object[][] getTestDataADLHierarchy() throws IOException
	{
		String[] requiredInputs = IOMapping.getADLHierarchyInputs();
		String output = IOMapping.getADLHierarchyOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleADLHierarchy_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
