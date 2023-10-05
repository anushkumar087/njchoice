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
public class ScaleFunctionalHierarchy extends Base{
	
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
	
	public ScaleFunctionalHierarchy(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleFunctionalHierarchy(DataFile.ScaleFunctionalHierarchy_DataFile);
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
	
	
	@Test(dataProvider="FunctionalHierarchyScale", priority=30)
	void scaleFunctionalHierarchy(String iG1ab, String iG1bb, String iG1cb, String iG1db,
			String iG1gb, String iG2b, String iG2f, String iG2h, String iG2j, String output)
	{
		Reusables.setIOMapping(IOMapping.getFunctionalHierarchyScaleInputs(),IOMapping.getFunctionalHierarchyScaleOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section G
		njChoicePage.FunctionalStatusSectionGandH.click();
				
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG1ab, iG1ab);
		command.sendKeysWithBuffer(functionalStatusPage.iG1bb, iG1bb);
		command.sendKeysWithBuffer(functionalStatusPage.iG1cb, iG1cb);
		command.sendKeysWithBuffer(functionalStatusPage.iG1db, iG1db);
		command.sendKeysWithBuffer(functionalStatusPage.iG1gb, iG1gb);
		
		command.sendKeysWithBuffer(functionalStatusPage.iG2b, iG2b);
		command.sendKeysWithBuffer(functionalStatusPage.iG2f, iG2f);
		command.sendKeysWithBuffer(functionalStatusPage.iG2h, iG2h);
		command.sendKeysWithBuffer(functionalStatusPage.iG2j, iG2j);
		
		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.functionalHierarchyScaleValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="FunctionalHierarchyScale")
	public static Object[][] getTestDataFunctionalHierarchyScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getFunctionalHierarchyScaleInputs();
		String output = IOMapping.getFunctionalHierarchyScaleOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleFunctionalHierarchy_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
