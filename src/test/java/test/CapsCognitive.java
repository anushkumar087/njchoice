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
import pagefactory.DiseaseDiagnosesPage;
import pagefactory.FunctionalStatusPage;
import pagefactory.HealthConditionsPage;
import pagefactory.IntakePage;
import pagefactory.MedicationsAndTreatmentsPage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.OralAndNutritionalStatusPage;
import pagefactory.ReferralPage;
import pagefactory.SocialSupportPage;
import util.AppCommonActions;
import util.DataFile;
import util.IOMapping;
import util.OptimizedCommands;
import util.Reusables;
import util.TestData;

@Listeners(listener.ListenerTest.class)
public class CapsCognitive extends Base {
	
	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	CommunicationAndVisionPage communicationAndVisionPage;
	FunctionalStatusPage functionalStatus;
	OralAndNutritionalStatusPage oralAndNutritionalStatusPage;
	DiseaseDiagnosesPage diseaseDiagnosesPage;
	MedicationsAndTreatmentsPage medicationsAndTreatmentsPage;
	SocialSupportPage socialSupportPage;
	IntakePage intakePage;
	
	public CapsCognitive(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsCognitive(DataFile.CapsCognitive_DataFile);
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
	
	
	@Test(dataProvider="CognitiveCap", priority=38)
	void capCognitive(String iB10,String iC1,String iC2a,String iC3a,String iC3b,String iC3c,String iC4,String iC5,String iD1,String iD2,
			String iE1e,String iE1h,String iE3a,String iE3c,String iG2a,String iI1c,String iI1d,String iJ6c,String output)
	{
		Reusables.setIOMapping(IOMapping.getCognitiveInputs(),IOMapping.getCognitiveOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section B
		njChoicePage.intakeSectionB.click();
				
		intakePage = PageFactory.initElements(driver, IntakePage.class);
		
		intakePage.clickEditButtonIfDisplayed(intakePage);
		
		// Enter all required values
		command.sendKeysWithBuffer(intakePage.iB10, iB10);
		
		intakePage.saveOrUpdateAfterEnteringRequiredFields(intakePage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section C and enter required values
		njChoicePage.cognitionSectionCandD.click();
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iC1, iC1);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC2a, iC3a);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3a, iC3a);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3b, iC3b);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3c, iC3c);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC4, iC4);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC5, iC5);
		command.sendKeysWithBuffer(communicationAndVisionPage.iD1, iD1);
		command.sendKeysWithBuffer(communicationAndVisionPage.iD2, iD2);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section F
		njChoicePage.moodAndBehaviorSectionE.click();
				
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE1e, iE1e);
		command.sendKeysWithBuffer(moodAndBehavior.iE1h, iE1h);
		command.sendKeysWithBuffer(moodAndBehavior.iE3a, iE3a);
		command.sendKeysWithBuffer(moodAndBehavior.iE3c, iE3c);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
				
		
		// Go to section G and enter required values
		njChoicePage.FunctionalStatusSectionGandH.click();
		functionalStatus = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatus.clickEditButtonIfDisplayed(functionalStatus);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatus.iG2a, iG2a);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section I and enter required values
		njChoicePage.diseaseDiagnosesSectionI.click();
		diseaseDiagnosesPage = PageFactory.initElements(driver, DiseaseDiagnosesPage.class);
		
		diseaseDiagnosesPage.clickEditButtonIfDisplayed(diseaseDiagnosesPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1c, iI1c);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1d, iI1d);
		
		diseaseDiagnosesPage.saveOrUpdateAfterEnteringRequiredFields(diseaseDiagnosesPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);

		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.bowelCapValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="CognitiveCap")
	public static Object[][] getTestDataCapCognitive() throws IOException
	{
		String[] requiredInputs = IOMapping.getCognitiveInputs();
		String output = IOMapping.getCognitiveOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsCognitive_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
