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
import pagefactory.MedicationsAndTreatmentsPage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.OralAndNutritionalStatusPage;
import pagefactory.ReferralPage;
import util.AppCommonActions;
import util.DataFile;
import util.IOMapping;
import util.OptimizedCommands;
import util.Reusables;
import util.TestData;

@Listeners(listener.ListenerTest.class)
public class ScaleDivert extends Base{

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
	
	public ScaleDivert(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleDivert(DataFile.ScaleDivert_DataFile);
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
	
	
	@Test(dataProvider="DivertScale", priority=43)
	void capScaleDivert(String iE1a,String iE1b,String iE1c,String iE1d,String iE1e,String iE1f,String iE1g,String iG5b,String iG6,String iH2,
			String iI1j,String iI1k,String iI1l,String iI1m,String iI1r,String iI1s,String iI1u,String iJ1,String iJ2c,String iJ2e,String iJ3,String iJ8a,
			String iJ8b,String iK2a,String iK2b,String iL3,String iN2e,String iN4a,String iN4b,String output)
	{
		Reusables.setIOMapping(IOMapping.getDivertScaleInputs(),IOMapping.getDivertScaleOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
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

		moodAndBehavior.saveOrUpdateAfterEnteringRequiredFields(moodAndBehavior);
		
		
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section G and enter required values
		njChoicePage.FunctionalStatusSectionGandH.click();
		functionalStatus = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatus.clickEditButtonIfDisplayed(functionalStatus);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatus.iG5b, iG5b);
		command.sendKeysWithBuffer(functionalStatus.iG6, iG6);
		command.sendKeysWithBuffer(functionalStatus.iH2, iH2);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section I and enter required values
		njChoicePage.diseaseDiagnosesSectionI.click();
		diseaseDiagnosesPage = PageFactory.initElements(driver, DiseaseDiagnosesPage.class);
		
		diseaseDiagnosesPage.clickEditButtonIfDisplayed(diseaseDiagnosesPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1j, iI1j);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1k, iI1k);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1l, iI1l);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1m, iI1m);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1r, iI1r);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1s, iI1s);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1u, iI1u);
		
		diseaseDiagnosesPage.saveOrUpdateAfterEnteringRequiredFields(diseaseDiagnosesPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);

		// Enter Section J
		njChoicePage.healthConditionsSectionJ.click();
		
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ3, iJ3);
		command.sendKeysWithBuffer(healthConditions.iJ2c, iJ2c);
		command.sendKeysWithBuffer(healthConditions.iJ2e, iJ2e);
		command.sendKeysWithBuffer(healthConditions.iJ8a, iJ8a);
		command.sendKeysWithBuffer(healthConditions.iJ8b, iJ8b);
		command.sendKeysWithBuffer(healthConditions.iJ1, iJ1);
		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section K and L
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
		
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2a, iK2a);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2b, iK2b);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL3, iL3);


		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section N
		njChoicePage.medicationsAndTreatmentsSectionMandN.click();
				
		medicationsAndTreatmentsPage = PageFactory.initElements(driver, MedicationsAndTreatmentsPage.class);
		medicationsAndTreatmentsPage.clickEditButtonIfDisplayed(medicationsAndTreatmentsPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2e, iN2e);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN4a, iN4a);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN4b, iN4b);
		
		medicationsAndTreatmentsPage.saveOrUpdateAfterEnteringRequiredFields(medicationsAndTreatmentsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.urinaryIncontinenceValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="DivertScale")
	public static Object[][] getTestDataScaleDivert() throws IOException
	{
		String[] requiredInputs = IOMapping.getDivertScaleInputs();
		String output = IOMapping.getDivertScaleOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScaleDivert_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
