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
public class ScaleIADLCapacityHierarchy extends Base{
	
	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	FunctionalStatusPage functionalStatus;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	public ScaleIADLCapacityHierarchy(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleSelfReportMood(DataFile.ScaleIADLCapacityHierarchy_DataFile);
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
	
	
	@Test(dataProvider="IADLCapacityHierarchyScale", priority=11)
	void scaleIADLCapacityHierarchy(String ig1ab, String ig1bb, String ig1cb, String ig1db, String ig1gb, String output)
	{
		Reusables.setIOMapping(IOMapping.getIADLCapacityHierarchyInputs(),IOMapping.getIADLCapacityHierarchyOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Go to section G and enter required values
		njChoicePage.FunctionalStatusSectionGandH.click();
		functionalStatus = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatus.clickEditButtonIfDisplayed(functionalStatus);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatus.iG1ab, ig1ab);
		command.sendKeysWithBuffer(functionalStatus.iG1bb, ig1bb);
		command.sendKeysWithBuffer(functionalStatus.iG1cb, ig1cb);
		command.sendKeysWithBuffer(functionalStatus.iG1db, ig1db);
		command.sendKeysWithBuffer(functionalStatus.iG1gb, ig1gb);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		command.waitAfterNavigatingToCapsAndAlgoTab();
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		capsAlgoPage.refreshButton.click();
		command.waitAfterClickingRefreshOnCapsAndAlgoTab();
		
		Reusables.validateOutput(capsAlgoPage.iadlCapacityHierarchyScale,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="IADLCapacityHierarchyScale")
	public static Object[][] getTestDataIADLCapacityHierarchyScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getIADLCapacityHierarchyInputs();
		String output = IOMapping.getIADLCapacityHierarchyOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleIADLCapacityHierarchy_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
