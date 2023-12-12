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
public class CapsAbusiveRelationship extends Base{
	
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
	
	public CapsAbusiveRelationship(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsAbusiveRelationship(DataFile.CapsAbusiveRelationship_DataFile);
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
	
	
	@Test(dataProvider="AbusiveRelationshipCaps", priority=45)
	void capsAbusiveRelationship(String iB7a,String iE1a,String iE1b,String iE1c,String iE1d,String iE1e,String iE1f,String iE1g,String iE1i,String iE1j,String iF1d,
			String iF1e,String iF1f,String iF2,String iJ2t,String iJ6a,String iJ7a,String iK1a,String iK1b,String iK2a,String iK2c,String iM2,String iP2b,String output)
	{
		Reusables.setIOMapping(IOMapping.getAbusiveRelationshipCapInputs(),IOMapping.getAbusiveRelationshipCapOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section B
		njChoicePage.intakeSectionB.click();
				
		intakePage = PageFactory.initElements(driver, IntakePage.class);
		
		intakePage.clickEditButtonIfDisplayed(intakePage);
		
		// Enter all required values
		command.sendKeysWithBuffer(intakePage.iB7a, iB7a);
		
		intakePage.saveOrUpdateAfterEnteringRequiredFields(intakePage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
				
		
		// Go to section E and enter required values 
		njChoicePage.moodAndBehaviorSectionE.click();
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE1a, iE1a);
		command.sendKeysWithBuffer(moodAndBehavior.iE1b, iE1b);
		command.sendKeysWithBuffer(moodAndBehavior.iE1c, iE1c);
		command.sendKeysWithBuffer(moodAndBehavior.iE1d, iE1d);
		command.sendKeysWithBuffer(moodAndBehavior.iE1e, iE1e);
		command.sendKeysWithBuffer(moodAndBehavior.iE1f, iE1f);
		command.sendKeysWithBuffer(moodAndBehavior.iE1g, iE1g);
		command.sendKeysWithBuffer(moodAndBehavior.iE1i, iE1i);
		command.sendKeysWithBuffer(moodAndBehavior.iE1j, iE1j);
		command.sendKeysWithBuffer(moodAndBehavior.iF1d, iF1d);
		command.sendKeysWithBuffer(moodAndBehavior.iF1e, iF1e);
		command.sendKeysWithBuffer(moodAndBehavior.iF1f, iF1f);
		command.sendKeysWithBuffer(moodAndBehavior.iF2, iF2);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section J
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ2t, iJ2t);
		command.sendKeysWithBuffer(healthConditions.iJ6a, iJ6a);
		command.sendKeysWithBuffer(healthConditions.iJ7a, iJ7a);

		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section K and L
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
		
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2a, iK2a);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK1a, iK1a);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2c, iK2c);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK1b, iK1b);


		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section N
		njChoicePage.medicationsAndTreatmentsSectionMandN.click();
				
		medicationsAndTreatmentsPage = PageFactory.initElements(driver, MedicationsAndTreatmentsPage.class);
		medicationsAndTreatmentsPage.clickEditButtonIfDisplayed(medicationsAndTreatmentsPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iM2, iM2);
		
		medicationsAndTreatmentsPage.saveOrUpdateAfterEnteringRequiredFields(medicationsAndTreatmentsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section P and enter required values
		njChoicePage.socialSupportSectionPQandR.click();
		socialSupportPage = PageFactory.initElements(driver, SocialSupportPage.class);
		
		socialSupportPage.clickEditButtonIfDisplayed(socialSupportPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(socialSupportPage.iP2b, iP2b);
		
		socialSupportPage.saveOrUpdateAfterEnteringRequiredFields(socialSupportPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);

		
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.abusiveRelationshipCapsValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="AbusiveRelationshipCaps")
	public static Object[][] getTestDataCapsAbusiveRelationship() throws IOException
	{
		String[] requiredInputs = IOMapping.getAbusiveRelationshipCapInputs();
		String output = IOMapping.getAbusiveRelationshipCapOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsAbusiveRelationship_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
