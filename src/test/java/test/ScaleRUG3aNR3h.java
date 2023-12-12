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
public class ScaleRUG3aNR3h extends Base{
	
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
	
	public ScaleRUG3aNR3h(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleRUG3aNR3h(DataFile.ScaleRUG3aNR3h_DataFile);
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
	
	
	@Test(dataProvider="RUG3aNR3hScale", priority=47)
	void scaleRUG3aNR3h(String iC1,String iC2a,String iD1,String iE3a,String iE3b,String iE3c,String iE3d,String iE3e,String iE3f,String iG1aa,String iG1da,
			String iG1ea,String iG2a,String iG2g,String iG2h,String iG2i,String iG2j,String iI1e,String iI1f,String iI1i,String iI1r,String iI1u,String iJ2h
			,String iJ2i,String iJ2j,String iJ2n,String iJ2r,String iJ2s,String iJ6c,String iK2a,String iK2b,String iK3,String iL1,String iL4,String iL5
			,String iL7,String iN2a,String iN2b,String iN2d,String iN2e,String iN2f,String iN2g,String iN2h,String iN2i,String iN2j,String iN2k,String iN2n
			,String iN3eb,String iN3fb,String iN3gb,String output)
	{
		Reusables.setIOMapping(IOMapping.getRUG3aNR3hInputs(),IOMapping.getRUG3aNR3hOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Go to section C and enter required values
		njChoicePage.cognitionSectionCandD.click();
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iC1, iC1);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC2a, iC2a);
		command.sendKeysWithBuffer(communicationAndVisionPage.iD1, iD1);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section G and enter required values
		njChoicePage.FunctionalStatusSectionGandH.click();
		functionalStatus = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatus.clickEditButtonIfDisplayed(functionalStatus);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatus.iG1aa, iG1aa);
		command.sendKeysWithBuffer(functionalStatus.iG1da, iG1da);
		command.sendKeysWithBuffer(functionalStatus.iG1ea, iG1ea);
		command.sendKeysWithBuffer(functionalStatus.iG2a, iG2a);
		command.sendKeysWithBuffer(functionalStatus.iG2g, iG2g);
		command.sendKeysWithBuffer(functionalStatus.iG2h, iG2h);
		command.sendKeysWithBuffer(functionalStatus.iG2i, iG2i);
		command.sendKeysWithBuffer(functionalStatus.iG2j, iG2j);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section E and enter required values 
		njChoicePage.moodAndBehaviorSectionE.click();
		moodAndBehavior = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		
		moodAndBehavior.clickEditButtonIfDisplayed(moodAndBehavior);
		
		// Enter all required values
		command.sendKeysWithBuffer(moodAndBehavior.iE3a, iE3a);
		command.sendKeysWithBuffer(moodAndBehavior.iE3b, iE3b);
		command.sendKeysWithBuffer(moodAndBehavior.iE3c, iE3c);
		command.sendKeysWithBuffer(moodAndBehavior.iE3d, iE3d);
		command.sendKeysWithBuffer(moodAndBehavior.iE3e, iE3e);
		command.sendKeysWithBuffer(moodAndBehavior.iE3f, iE3f);
		
		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
				
				
		
		// Go to section I and enter required values
		njChoicePage.diseaseDiagnosesSectionI.click();
		diseaseDiagnosesPage = PageFactory.initElements(driver, DiseaseDiagnosesPage.class);
		
		diseaseDiagnosesPage.clickEditButtonIfDisplayed(diseaseDiagnosesPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1e, iI1e);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1f, iI1f);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1i, iI1i);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1r, iI1r);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1u, iI1u);
		
		diseaseDiagnosesPage.saveOrUpdateAfterEnteringRequiredFields(diseaseDiagnosesPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		// Enter Section J
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ2h, iJ2h);
		command.sendKeysWithBuffer(healthConditions.iJ2i, iJ2i);
		command.sendKeysWithBuffer(healthConditions.iJ2j, iJ2j);
		command.sendKeysWithBuffer(healthConditions.iJ2n, iJ2n);
		command.sendKeysWithBuffer(healthConditions.iJ2r, iJ2r);
		command.sendKeysWithBuffer(healthConditions.iJ2s, iJ2s);
		command.sendKeysWithBuffer(healthConditions.iJ6c, iJ6c);

		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section K and L
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
		
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2a, iK2a);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2b, iK2b);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK3, iK3);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL1, iL1);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL4, iL4);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL5, iL5);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL7, iL7);


		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		

		// Enter Section N
		njChoicePage.medicationsAndTreatmentsSectionMandN.click();
				
		medicationsAndTreatmentsPage = PageFactory.initElements(driver, MedicationsAndTreatmentsPage.class);
		medicationsAndTreatmentsPage.clickEditButtonIfDisplayed(medicationsAndTreatmentsPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2a, iN2a);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2b, iN2b);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2d, iN2d);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2e, iN2e);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2f, iN2f);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2g, iN2g);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2h, iN2h);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2i, iN2i);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2j, iN2j);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2k, iN2k);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2n, iN2n);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN3gb, iN3gb);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN3fb, iN3fb);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN3eb, iN3eb);
		
		
		medicationsAndTreatmentsPage.saveOrUpdateAfterEnteringRequiredFields(medicationsAndTreatmentsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.rug3aNR3hValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="RUG3aNR3hScale")
	public static Object[][] getTestDataRUG3aNR3hScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getRUG3aNR3hInputs();
		String output = IOMapping.getRUG3aNR3hOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleRUG3aNR3h_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}


}
