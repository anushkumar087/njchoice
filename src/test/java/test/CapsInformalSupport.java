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
import pagefactory.IntakePage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.ReferralPage;
import pagefactory.SocialSupportPage;
import util.AppCommonActions;
import util.DataFile;
import util.IOMapping;
import util.OptimizedCommands;
import util.Reusables;
import util.TestData;

@Listeners(listener.ListenerTest.class)
public class CapsInformalSupport extends Base{
	
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
	IntakePage intakePage;
	SocialSupportPage socialSupportPage;
	
	public CapsInformalSupport(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsInformalSupport(DataFile.CapsInformalSupport_DataFile);
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
	
	
	@Test(dataProvider="InformalSupportCaps", priority=33)
	void capsInformalSupport(String iB7a, String iF4,String iG1ab, String iG1bb, String iG1gb, String iG1hb, String iP1a1, String output)
	{
		Reusables.setIOMapping(IOMapping.getInformalSupportCapInputs(),IOMapping.getInformalSupportCapOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section B
		njChoicePage.intakeSectionB.click();
				
		intakePage = PageFactory.initElements(driver, IntakePage.class);
		
		intakePage.clickEditButtonIfDisplayed(intakePage);
		
		// Enter all required values
		command.sendKeysWithBuffer(intakePage.iB7a, iB7a);
		
		intakePage.saveOrUpdateAfterEnteringRequiredFields(intakePage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section F
		njChoicePage.moodAndBehaviorSectionE.click();
				
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iF4, iF4);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section G
		njChoicePage.FunctionalStatusSectionGandH.click();
				
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG1ab, iG1ab);
		command.sendKeysWithBuffer(functionalStatusPage.iG1bb, iG1bb);
		command.sendKeysWithBuffer(functionalStatusPage.iG1gb, iG1gb);
		command.sendKeysWithBuffer(functionalStatusPage.iG1hb, iG1hb);
		
		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section P
		njChoicePage.socialSupportSectionPQandR.click();
				
		socialSupportPage = PageFactory.initElements(driver, SocialSupportPage.class);
		
		socialSupportPage.clickEditButtonIfDisplayed(socialSupportPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(socialSupportPage.iP1a1, iP1a1);
		
		socialSupportPage.saveOrUpdateAfterEnteringRequiredFields(socialSupportPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.informalSupportValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="InformalSupportCaps")
	public static Object[][] getTestDataInformalSupport() throws IOException
	{
		String[] requiredInputs = IOMapping.getInformalSupportCapInputs();
		String output = IOMapping.getInformalSupportCapOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsInformalSupport_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
