package testAll;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import base.Base;
import pagefactory.CapsAndAlgoValuesPage;
import pagefactory.CommunicationAndVisionPage;
import pagefactory.CompletionPage;
import pagefactory.DiseaseDiagnosesPage;
import pagefactory.FunctionalStatusPage;
import pagefactory.HealthConditionsPage;
import pagefactory.IntakePage;
import pagefactory.LevelOfCarePage;
import pagefactory.MedicationsAndTreatmentsPage;
import pagefactory.MoodAndBehaviorPage;
import pagefactory.NJChoicePage;
import pagefactory.OralAndNutritionalStatusPage;
import pagefactory.ReferralPage;
import pagefactory.SocialSupportPage;
import listener.ComprehensiveTestListener;
import util.AppCommonActions;
import util.DataFile;
import util.OptimizedCommands;
import util.TestValidationHelper;

@Listeners(listener.ComprehensiveTestListener.class)
public class ComprehensiveAllSectionsTest extends Base {
	
	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	IntakePage intakePage;
	CommunicationAndVisionPage communicationAndVisionPage;
	MoodAndBehaviorPage moodAndBehaviorPage;
	FunctionalStatusPage functionalStatusPage;
	DiseaseDiagnosesPage diseaseDiagnosesPage;
	HealthConditionsPage healthConditionsPage;
	OralAndNutritionalStatusPage oralAndNutritionalStatusPage;
	MedicationsAndTreatmentsPage medicationsAndTreatmentsPage;
	SocialSupportPage socialSupportPage;
	LevelOfCarePage levelOfCarePage;
	CompletionPage completionPage;
	CapsAndAlgoValuesPage capsAlgoPage;
	
	private Map<String, String> dataMap = new HashMap<>();
	
	public ComprehensiveAllSectionsTest(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp() {
		base = new ComprehensiveAllSectionsTest(DataFile.master_DataFile);
		commonActions = new AppCommonActions();
		command = new OptimizedCommands();
		base.initialize();
		commonActions.loginToSalesForce(driver);
		commonActions.navigateToNJChoice(driver);
	}
	
	@Test(dataProvider="DynamicDataProvider", dataProviderClass=dataproviders.MasterDataProvider.class, priority=1)
	public void testAllSectionsComprehensive(Object[] rowData) throws Exception {
		System.out.println("========================================");
		System.out.println("Starting Comprehensive Test for All Sections");
		System.out.println("========================================");

		TestValidationHelper.clearFailedOutputColumns();
		dataMap = TestValidationHelper.populateDataMap(rowData, DataFile.master_DataFile);
		ComprehensiveTestListener.setTestDataMap(dataMap);
		
		// // ===== SECTION A: Additional Referral Fields =====
		System.out.println("Filling Additional Section A: Referral fields...");
		njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);
		njChoicePage.njChoiceAssessmentTab.click();
		command.waitTillElementDisplayed(driver, njChoicePage.CreateNJChoiceAssessmentHeader, 60);
		njChoicePage.referralSectionA.click();
		referralPage = PageFactory.initElements(driver, ReferralPage.class);
		referralPage.enterAllMandatoryFields();
		referralPage.SaveAndNextButton.click();
		commonActions.handleAlertsAfterSaveReferralSection(driver, command);
		
		// ===== SECTION B: INTAKE =====
		System.out.println("Filling Section B: Intake...");
		fillSectionB();
		
		// ===== SECTION C-D: COGNITION, COMMUNICATION AND VISION =====
		System.out.println("Filling Section C-D: Cognition, Communication and Vision...");
		fillSectionCD();
		
		// ===== SECTION E-F: MOOD AND BEHAVIOR =====
		System.out.println("Filling Section E-F: Mood and Behavior...");
		fillSectionEF();
		
		// ===== SECTION G-H: FUNCTIONAL STATUS =====
		System.out.println("Filling Section G-H: Functional Status...");
		fillSectionGH();
		
		// ===== SECTION I: DISEASE DIAGNOSES =====
		System.out.println("Filling Section I: Disease Diagnoses...");
		fillSectionI();
		
		// ===== SECTION J: HEALTH CONDITIONS =====
		System.out.println("Filling Section J: Health Conditions...");
		fillSectionJ();
		
		// ===== SECTION K-L: ORAL AND NUTRITIONAL STATUS =====
		System.out.println("Filling Section K-L: Oral and Nutritional Status...");
		fillSectionKL();
		
		// ===== SECTION M-N: MEDICATIONS AND TREATMENTS =====
		System.out.println("Filling Section M-N: Medications and Treatments...");
		fillSectionMN();
		
		// ===== SECTION O: LEVEL OF CARE =====
		System.out.println("Filling Section O: Level Of Care...");
		fillSectionO();
		
		// ===== SECTION P-Q-R: SOCIAL SUPPORT =====
		System.out.println("Filling Section P-Q-R: Social Support...");
		fillSectionPQR();
		
		// ===== SECTION S: COMPLETION =====
		System.out.println("Filling Section S: Completion...");
		fillSectionS();
		
		// ===== VALIDATE ALL OUTPUTS =====
		System.out.println("\n========================================");
		System.out.println("Validating ALL Output Values...");
		System.out.println("========================================");
		int[] validationResults = validateAllOutputs();
		commonActions.refreshAndWaitFornjChoiceAssessmentTab(driver,njChoicePage,command);
		int passedCount = validationResults[0];
		int failedCount = validationResults[1];
		int totalValidations = passedCount + failedCount;
		
		// Store validation results in TestNG result for listener to access
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("validationsPassed", passedCount);
		result.setAttribute("validationsFailed", failedCount);
		
		System.out.println("\n========================================");
		System.out.println("Comprehensive Test Completed!");
		System.out.println("Status: " + (failedCount == 0 ? "PASS" : "FAIL"));
		System.out.println("Validations Passed: " + passedCount + "/" + totalValidations);
		System.out.println("========================================");
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	/**
	 * Fill Additional Section A: Referral (fields that are data-driven)
	 */
	private void fillAdditionalSectionA() {
		njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);
		njChoicePage.referralSectionA.click();
		referralPage = PageFactory.initElements(driver, ReferralPage.class);
		
		// Fill additional fields if they exist
		sendKeysIfDataExists(referralPage.gender, "iA2");
		sendKeysIfDataExists(referralPage.genderIdentity, "iA2");
		sendKeysIfDataExists(referralPage.maritalStatus, "iA4");
		enterDateAsDDMMYYYY(referralPage.birthdate, "iA3");
		
		referralPage.saveOrUpdateAfterEnteringRequiredFields(referralPage);
		commonActions.handleAlertsAfterSaveReferralSection(driver, command);
	}
	
	/**
	 * Fill Section B: Intake
	 */
	private void fillSectionB() {
		njChoicePage.intakeSectionB.click();
		intakePage = PageFactory.initElements(driver, IntakePage.class);
		intakePage.clickEditButtonIfDisplayed(intakePage);
		
		// Fill all available fields in Section B
		enterDateAsDDMMYYYY(intakePage.iB1, "iB2");
		sendKeysIfDataExists(intakePage.iB4a, "iB4");
		sendKeysIfDataExists(intakePage.iB6, "iA11b");
		sendKeysIfDataExists(intakePage.iB7a, "iB7a");
		sendKeysIfDataExists(intakePage.iB7b, "iB7b");
		sendKeysIfDataExists(intakePage.iB7c, "iB7c");
		sendKeysIfDataExists(intakePage.iB8, "iA13");
		sendKeysIfDataExists(intakePage.iB9a, "iB5a");
		sendKeysIfDataExists(intakePage.iB9b, "iB5b");
		sendKeysIfDataExists(intakePage.iB9c, "iB5e");
		sendKeysIfDataExists(intakePage.iB9d, "iB5c");
		sendKeysIfDataExists(intakePage.iB9e, "iB5d");
		sendKeysIfDataExists(intakePage.iB10, "iB10");
		
		intakePage.enterUnnecessaryButMandatoryFields();
		
		intakePage.saveOrUpdateAfterEnteringRequiredFields(intakePage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section C-D: Cognition, Communication and Vision
	 */
	private void fillSectionCD() {
		njChoicePage.cognitionSectionCandD.click();
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Fill all available fields in Section C-D
		sendKeysIfDataExists(communicationAndVisionPage.iC1, "iC1");
		sendKeysIfDataExists(communicationAndVisionPage.iC2a, "iC2a");
		sendKeysIfDataExists(communicationAndVisionPage.iC2b, "iC2b");
		sendKeysIfDataExists(communicationAndVisionPage.iC2c, "iC2c");
		sendKeysIfDataExists(communicationAndVisionPage.iC3a, "iC3a");
		sendKeysIfDataExists(communicationAndVisionPage.iC3b, "iC3b");
		sendKeysIfDataExists(communicationAndVisionPage.iC3c, "iC3c");
		sendKeysIfDataExists(communicationAndVisionPage.iC4, "iC4");
		sendKeysIfDataExists(communicationAndVisionPage.iC5, "iC5");
		sendKeysIfDataExists(communicationAndVisionPage.iD1, "iD1");
		sendKeysIfDataExists(communicationAndVisionPage.iD2, "iD2");
		sendKeysIfDataExists(communicationAndVisionPage.iD3, "iD3a");
		sendKeysIfDataExists(communicationAndVisionPage.iD4, "iD4a");
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section E-F: Mood and Behavior
	 */
	private void fillSectionEF() {
		njChoicePage.moodAndBehaviorSectionE.click();
		moodAndBehaviorPage = PageFactory.initElements(driver, MoodAndBehaviorPage.class);
		moodAndBehaviorPage.clickEditButtonIfDisplayed(moodAndBehaviorPage);
		
		// Fill all available fields in Section E-F
		sendKeysIfDataExists(moodAndBehaviorPage.iE1a, "iE1a");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1b, "iE1b");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1c, "iE1c");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1d, "iE1d");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1e, "iE1e");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1f, "iE1f");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1g, "iE1g");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1h, "iE1h");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1i, "iE1i");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1j, "iE1j");
		sendKeysIfDataExists(moodAndBehaviorPage.iE1k, "iE1k");
		sendKeysIfDataExists(moodAndBehaviorPage.iE2a, "iE2a");
		sendKeysIfDataExists(moodAndBehaviorPage.iE2b, "iE2b");
		sendKeysIfDataExists(moodAndBehaviorPage.iE2c, "iE2c");
		sendKeysIfDataExists(moodAndBehaviorPage.iE3a, "iE3a");
		sendKeysIfDataExists(moodAndBehaviorPage.iE3b, "iE3b");
		sendKeysIfDataExists(moodAndBehaviorPage.iE3c, "iE3c");
		sendKeysIfDataExists(moodAndBehaviorPage.iE3d, "iE3d");
		sendKeysIfDataExists(moodAndBehaviorPage.iE3e, "iE3e");
		sendKeysIfDataExists(moodAndBehaviorPage.iE3f, "iE3f");
		sendKeysIfDataExists(moodAndBehaviorPage.iF1a, "iF1a");
		sendKeysIfDataExists(moodAndBehaviorPage.iF1b, "iF1b");
		sendKeysIfDataExists(moodAndBehaviorPage.iF1c, "iF1c");
		sendKeysIfDataExists(moodAndBehaviorPage.iF1d, "iF1d");
		sendKeysIfDataExists(moodAndBehaviorPage.iF1e, "iF1e");
		sendKeysIfDataExists(moodAndBehaviorPage.iF1f, "iF1f");
		sendKeysIfDataExists(moodAndBehaviorPage.iF2, "iF2");
		sendKeysIfDataExists(moodAndBehaviorPage.iF3, "iF3");
		sendKeysIfDataExists(moodAndBehaviorPage.iF4, "iF4");
		sendKeysIfDataExists(moodAndBehaviorPage.iF5, "iF5");
		
		moodAndBehaviorPage.enterUnnecessaryButMandatoryFields();
		
		moodAndBehaviorPage.saveOrUpdateAfterEnteringRequiredFields(moodAndBehaviorPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section G-H: Functional Status
	 */
	private void fillSectionGH() {
		njChoicePage.FunctionalStatusSectionGandH.click();
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Fill all available fields in Section G-H
		sendKeysIfDataExists(functionalStatusPage.iG1aa, "iG1aa");
		sendKeysIfDataExists(functionalStatusPage.iG1ab, "iG1ab");
		sendKeysIfDataExists(functionalStatusPage.iG1ba, "iG1ba");
		sendKeysIfDataExists(functionalStatusPage.iG1bb, "iG1bb");
		sendKeysIfDataExists(functionalStatusPage.iG1ca, "iG1ca");
		sendKeysIfDataExists(functionalStatusPage.iG1cb, "iG1cb");
		sendKeysIfDataExists(functionalStatusPage.iG1da, "iG1da");
		sendKeysIfDataExists(functionalStatusPage.iG1db, "iG1db");
		sendKeysIfDataExists(functionalStatusPage.iG1ea, "iG1ea");
		sendKeysIfDataExists(functionalStatusPage.iG1eb, "iG1eb");
		sendKeysIfDataExists(functionalStatusPage.iG1fa, "iG1fa");
		sendKeysIfDataExists(functionalStatusPage.iG1fb, "iG1fb");
		sendKeysIfDataExists(functionalStatusPage.iG1ga, "iG1ga");
		sendKeysIfDataExists(functionalStatusPage.iG1gb, "iG1gb");
		sendKeysIfDataExists(functionalStatusPage.iG1ha, "iG1ha");
		sendKeysIfDataExists(functionalStatusPage.iG1hb, "iG1hb");
		sendKeysIfDataExists(functionalStatusPage.iG2c, "iG2a");
		sendKeysIfDataExists(functionalStatusPage.iG2b, "iG2b");
		sendKeysIfDataExists(functionalStatusPage.iG2d, "iG2c");
		sendKeysIfDataExists(functionalStatusPage.iG2e, "iG2d");
		sendKeysIfDataExists(functionalStatusPage.iG2j, "iG2e");
		sendKeysIfDataExists(functionalStatusPage.iG2k, "iG2f");
		sendKeysIfDataExists(functionalStatusPage.iG2f, "iG2g");
		sendKeysIfDataExists(functionalStatusPage.iG2g, "iG2h");
		sendKeysIfDataExists(functionalStatusPage.iG2h, "iG2i");
		sendKeysIfDataExists(functionalStatusPage.iG2a, "iG2j");
		sendKeysIfDataExists(functionalStatusPage.iG3a, "iG3");
		sendKeysIfDataExists(functionalStatusPage.iG4a, "iG4a");
		sendKeysIfDataExists(functionalStatusPage.iG4b, "iG4b");
		sendKeysIfDataExists(functionalStatusPage.iG5a, "iG5a");
		sendKeysIfDataExists(functionalStatusPage.iG5b, "iG5b");
		sendKeysIfDataExists(functionalStatusPage.iG6, "iG6");
		sendKeysIfDataExists(functionalStatusPage.iG7, "iG7");
		sendKeysIfDataExists(functionalStatusPage.iH1, "iH1");
		sendKeysIfDataExists(functionalStatusPage.iH2, "iH2");
		sendKeysIfDataExists(functionalStatusPage.iH3, "iH3");
		sendKeysIfDataExists(functionalStatusPage.iH4, "iH4");
		
		functionalStatusPage.enterUnnecessaryButMandatoryFields();
		
		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section I: Disease Diagnoses
	 */
	private void fillSectionI() {
		njChoicePage.diseaseDiagnosesSectionI.click();
		diseaseDiagnosesPage = PageFactory.initElements(driver, DiseaseDiagnosesPage.class);
		diseaseDiagnosesPage.clickEditButtonIfDisplayed(diseaseDiagnosesPage);
		
		// Fill all available fields in Section I
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1a, "iI1a");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1b, "iI1b");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1c, "iI1c");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1d, "iI1d");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1e, "iI1e");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1f, "iI1f");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1g, "iI1g");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1h, "iI1h");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1i, "iI1i");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1j, "iI1j");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1k, "iI1k");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1l, "iI1l");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1m, "iI1m");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1n, "iI1n");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1o, "iI1o");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1p, "iI1p");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1q, "iI1q");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1r, "iI1r");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1s, "iI1s");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1s1, "I1s1");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1t, "iI1t");
		sendKeysIfDataExists(diseaseDiagnosesPage.iI1u, "iI1u");
		
		diseaseDiagnosesPage.enterUnnecessaryButMandatoryFields();
		
		diseaseDiagnosesPage.saveOrUpdateAfterEnteringRequiredFields(diseaseDiagnosesPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section J: Health Conditions
	 */
	private void fillSectionJ() {
		njChoicePage.healthConditionsSectionJ.click();
		healthConditionsPage = PageFactory.initElements(driver, HealthConditionsPage.class);
		healthConditionsPage.clickEditButtonIfDisplayed(healthConditionsPage);
		
		// Fill all available fields in Section J
		sendKeysIfDataExists(healthConditionsPage.iJ1, "iJ1");
		sendKeysIfDataExists(healthConditionsPage.iJ2a, "iJ2a");
		sendKeysIfDataExists(healthConditionsPage.iJ2b, "iJ2b");
		sendKeysIfDataExists(healthConditionsPage.iJ2c, "iJ2c");
		sendKeysIfDataExists(healthConditionsPage.iJ2d, "iJ2d");
		sendKeysIfDataExists(healthConditionsPage.iJ2e, "iJ2e");
		sendKeysIfDataExists(healthConditionsPage.iJ2f, "iJ2f");
		sendKeysIfDataExists(healthConditionsPage.iJ2g, "iJ2g");
		sendKeysIfDataExists(healthConditionsPage.iJ2h, "iJ2h");
		sendKeysIfDataExists(healthConditionsPage.iJ2i, "iJ2i");
		sendKeysIfDataExists(healthConditionsPage.iJ2j, "iJ2j");
		sendKeysIfDataExists(healthConditionsPage.iJ2k, "iJ2k");
		sendKeysIfDataExists(healthConditionsPage.iJ2l, "iJ2l");
		sendKeysIfDataExists(healthConditionsPage.iJ2m, "iJ2m");
		sendKeysIfDataExists(healthConditionsPage.iJ2n, "iJ2n");
		sendKeysIfDataExists(healthConditionsPage.iJ2o, "iJ2o");
		sendKeysIfDataExists(healthConditionsPage.iJ2p, "iJ2p");
		sendKeysIfDataExists(healthConditionsPage.iJ2q, "iJ2q");
		sendKeysIfDataExists(healthConditionsPage.iJ2r, "iJ2r");
		sendKeysIfDataExists(healthConditionsPage.iJ2s, "iJ2s");
		sendKeysIfDataExists(healthConditionsPage.iJ2t, "iJ2t");
		sendKeysIfDataExists(healthConditionsPage.iJ2u, "iJ2u");
		sendKeysIfDataExists(healthConditionsPage.iJ3, "iJ3");
		sendKeysIfDataExists(healthConditionsPage.iJ4, "iJ4");
		sendKeysIfDataExists(healthConditionsPage.iJ5a, "iJ5a");
		sendKeysIfDataExists(healthConditionsPage.iJ5b, "iJ5b");
		sendKeysIfDataExists(healthConditionsPage.iJ5c, "iJ5c");
		sendKeysIfDataExists(healthConditionsPage.iJ5d, "iJ5d");
		sendKeysIfDataExists(healthConditionsPage.iJ5e, "iJ5e");
		sendKeysIfDataExists(healthConditionsPage.iJ6a, "iJ6a");
		sendKeysIfDataExists(healthConditionsPage.iJ6b, "iJ6b");
		sendKeysIfDataExists(healthConditionsPage.iJ6c, "iJ6c");
		sendKeysIfDataExists(healthConditionsPage.iJ7a, "iJ7a");
		sendKeysIfDataExists(healthConditionsPage.iJ8a, "iJ8a");
		sendKeysIfDataExists(healthConditionsPage.iJ8b, "iJ8b");
		
		healthConditionsPage.saveOrUpdateAfterEnteringRequiredFields(healthConditionsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section K-L: Oral and Nutritional Status
	 */
	private void fillSectionKL() {
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Fill all available fields in Section K-L
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK1a, "iK1a");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK1b, "iK1b");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK2a, "iK2a");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK2b, "iK2b");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK2c, "iK2c");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK2d, "iK2d");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK3, "iK3");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK4a, "iK4a");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK4b, "iK4b");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK4c, "iK4d");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iK4d, "iK4c");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL1, "iL1");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL2, "iL2");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL3, "iL3");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL4, "iL4");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL5, "iL5");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL6, "iL6");
		sendKeysIfDataExists(oralAndNutritionalStatusPage.iL7, "iL7");
		
		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section M-N: Medications and Treatments
	 */
	private void fillSectionMN() {
		njChoicePage.medicationsAndTreatmentsSectionMandN.click();
		medicationsAndTreatmentsPage = PageFactory.initElements(driver, MedicationsAndTreatmentsPage.class);
		medicationsAndTreatmentsPage.clickEditButtonIfDisplayed(medicationsAndTreatmentsPage);
		
		// Fill all available fields in Section M-N
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iM2, "iM2");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1a, "iN1a");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1b, "iN1b");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1c, "iN1c");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1d, "iN1d");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1e, "iN1e");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1f, "iN1f");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1g, "iN1g");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN1h, "iN1h");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2a, "iN2a");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2b, "iN2b");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2c, "iN2c");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2d, "iN2d");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2e, "iN2e");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2f, "iN2f");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2g, "iN2g");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2h, "iN2h");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2i, "iN2i");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2j, "iN2j");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2k, "iN2k");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2l, "iN2l");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2m, "iN2m");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN2n, "iN2n");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3aa, "iN3aa");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3ab, "iN3ab");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3ba, "iN3ba");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3bb, "iN3bb");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3ca, "iN3ca");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3cb, "iN3cb");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3da, "iN3da");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3ea, "iN3ea");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3eb, "iN3eb");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3fa, "iN3fa");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3fb, "iN3fb");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3ga, "iN3ga");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3gb, "iN3gb");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3ha, "iN3ha");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN3hb, "iN3hb");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN5, "iN4");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN4a, "iN4a");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN4b, "iN4b");
		sendKeysIfDataExists(medicationsAndTreatmentsPage.iN4c, "iN4c");
		
		medicationsAndTreatmentsPage.enterUnnecessaryButMandatoryFields();
		
		medicationsAndTreatmentsPage.saveOrUpdateAfterEnteringRequiredFields(medicationsAndTreatmentsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	private void fillSectionO() {
		njChoicePage.levelOfCareSectionO.click();
		levelOfCarePage = PageFactory.initElements(driver, LevelOfCarePage.class);
		levelOfCarePage.clickEditButtonIfDisplayed(levelOfCarePage);
		
		levelOfCarePage.fillCompletionSectionWithDefaults();
		
		levelOfCarePage.saveOrUpdateAfterEnteringRequiredFields(levelOfCarePage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Fill Section P-Q-R: Social Support
	 */
	private void fillSectionPQR() {
		njChoicePage.socialSupportSectionPQandR.click();
		socialSupportPage = PageFactory.initElements(driver, SocialSupportPage.class);
		socialSupportPage.clickEditButtonIfDisplayed(socialSupportPage);
		
		// Fill all available fields in Section P-Q-R
		sendKeysIfDataExists(socialSupportPage.iP1a1, "iP1a1");
		sendKeysIfDataExists(socialSupportPage.iP1a2, "iP1a2");
		sendKeysIfDataExists(socialSupportPage.iP1b1, "iP1b1");
		sendKeysIfDataExists(socialSupportPage.iP1b2, "iP1b2");
		sendKeysIfDataExists(socialSupportPage.iP1c1, "iP1c1");
		sendKeysIfDataExists(socialSupportPage.iP1c2, "iP1c2");
		sendKeysIfDataExists(socialSupportPage.iP1d1, "iP1d1");
		sendKeysIfDataExists(socialSupportPage.iP1d2, "iP1d2");
		sendKeysIfDataExists(socialSupportPage.iP2a, "iP2a");
		sendKeysIfDataExists(socialSupportPage.iP2b, "iP2b");
		sendKeysIfDataExists(socialSupportPage.iP3, "iP3");
		sendKeysIfDataExists(socialSupportPage.iQ1a, "iQ1a");
		sendKeysIfDataExists(socialSupportPage.iQ1b, "iQ1b");
		sendKeysIfDataExists(socialSupportPage.iQ1c, "iQ1c");
		sendKeysIfDataExists(socialSupportPage.iQ1d, "iQ1d");
		sendKeysIfDataExists(socialSupportPage.iQ1e, "iQ1e");
		sendKeysIfDataExists(socialSupportPage.iQ2, "iQ2");
		sendKeysIfDataExists(socialSupportPage.iQ3a, "iQ3a");
		sendKeysIfDataExists(socialSupportPage.iQ3b, "iQ3b");
		sendKeysIfDataExists(socialSupportPage.iQ3c, "iQ3c");
		sendKeysIfDataExists(socialSupportPage.iQ4, "iQ4");
		
		socialSupportPage.enterUnnecessaryButMandatoryFields();
		
		socialSupportPage.saveOrUpdateAfterEnteringRequiredFields(socialSupportPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	
	
	/**
	 * Fill Section S: Completion
	 * Uses default values:
	 * - S1: Name from config.properties (key: "name")
	 * - Completion Date: Current date
	 * - S3: "1"
	 */
	private void fillSectionS() {
		njChoicePage.completionSectionS.click();
		completionPage = PageFactory.initElements(driver, CompletionPage.class);
		completionPage.clickEditButtonIfDisplayed(completionPage);
		
		// Fill Section S with default values
		completionPage.fillCompletionSectionWithDefaults();
		
		completionPage.saveOrUpdateAfterEnteringRequiredFields(completionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
	}
	
	/**
	 * Validate ALL output values from CAPS and Algo page
	 * @return int[] array where [0] = passed count, [1] = failed count
	 */
	private int[] validateAllOutputs() {
		// Navigate to CAPS and Algo tab and refresh values
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver, njChoicePage, command, capsAlgoPage);
		
		int validationCount = 0;
		int passedCount = 0;
		Map<String, Integer> outputValidationMap = new HashMap<>();
		int result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.cCARDIOValue, "Cardio-respiratory CAP", dataMap, ++validationCount);
		outputValidationMap.put("Cardio-respiratory CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.aMAPLEvalue, "aMAPLE Algorithm", dataMap, ++validationCount);
		outputValidationMap.put("aMAPLE Algorithm", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.sDRSValue, "sDRS Depression rating scale 0-14 range", dataMap, ++validationCount);
		outputValidationMap.put("sDRS Depression rating scale 0-14 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.smokingAndDrinkingValue, "Smoking and Drinking CAP", dataMap, ++validationCount);
		outputValidationMap.put("Smoking and Drinking CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.fallsValue, "Falls CAP", dataMap, ++validationCount);
		outputValidationMap.put("Falls CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.sCommValue, "Communication scale 0-8 range", dataMap, ++validationCount);
		outputValidationMap.put("Communication scale 0-8 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.dehydrationValue, "Dehydration CAP", dataMap, ++validationCount);
		outputValidationMap.put("Dehydration CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.painValue, "Pain CAP", dataMap, ++validationCount);
		outputValidationMap.put("Pain CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.deliriumValue, "Delirium CAP", dataMap, ++validationCount);
		outputValidationMap.put("Delirium CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.aggressiveBehaviorValue, "Aggressive Behaviour Scale 0-12 range", dataMap, ++validationCount);
		outputValidationMap.put("Aggressive Behaviour Scale 0-12 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.clinicianRatedMoodValue, "Clinician Rated Mood Scale", dataMap, ++validationCount);
		outputValidationMap.put("Clinician Rated Mood Scale", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.selfReportMoodScale, "Self Report Mood Scale", dataMap, ++validationCount);
		outputValidationMap.put("Self Report Mood Scale", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.iadlCapacityHierarchyScale, "IADL Capacity Hierarchy Scale 0-6 range", dataMap, ++validationCount);
		outputValidationMap.put("IADL Capacity Hierarchy Scale 0-6 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.fallsScale, "xFalls", dataMap, ++validationCount);
		outputValidationMap.put("xFalls", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.painScale, "sPAIN scale 0-4 range", dataMap, ++validationCount);
		outputValidationMap.put("sPAIN scale 0-4 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.cognitivePerformanceScale, "sCPS scale 0-6 range", dataMap, ++validationCount);
		outputValidationMap.put("sCPS scale 0-6 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.pressureUlcerCapValue, "Pressure Ulcer CAP", dataMap, ++validationCount);
		outputValidationMap.put("Pressure Ulcer CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.pressureUlcerRatingScaleValue, "sPUR Scale 0-8 range", dataMap, ++validationCount);
		outputValidationMap.put("sPUR Scale 0-8 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.feedingTubeCapsValue, "Feeding Tube CAP", dataMap, ++validationCount);
		outputValidationMap.put("Feeding Tube CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.behaviorCapsValue, "Behavior CAP", dataMap, ++validationCount);
		outputValidationMap.put("Behavior CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.deafblindSeverityIndexValue, "Deafblind Severity Index 0-5 range", dataMap, ++validationCount);
		outputValidationMap.put("Deafblind Severity Index 0-5 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.compositeMoodScaleValue, "Composite Mood Scale", dataMap, ++validationCount);
		outputValidationMap.put("Composite Mood Scale", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.physicalActivityPromotionValue, "Physical Activity Promotion CAP", dataMap, ++validationCount);
		outputValidationMap.put("Physical Activity Promotion CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.cognitivePerformanceScale2Value, "sCPS2 scale 0-8 range", dataMap, ++validationCount);
		outputValidationMap.put("sCPS2 scale 0-8 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.adlHierarchyScaleValue, "sADL hierarchy scale 0-6 range", dataMap, ++validationCount);
		outputValidationMap.put("sADL hierarchy scale 0-6 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.communicationCapsValue, "Communication CAP", dataMap, ++validationCount);
		outputValidationMap.put("Communication CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.adlsfScaleValue, "sADLSF ADL Short Form Scale 0-16 range", dataMap, ++validationCount);
		outputValidationMap.put("sADLSF ADL Short Form Scale 0-16 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.adllfScaleValue, "sADLLF ADL Long Form Scale 0-28 range", dataMap, ++validationCount);
		outputValidationMap.put("sADLLF ADL Long Form Scale 0-28 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.pain1ScaleValue, "SPAIN1 scale 0-3 range", dataMap, ++validationCount);
		outputValidationMap.put("SPAIN1 scale 0-3 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.bmiScaleValue, "Body Mass Index (kg/m^2)", dataMap, ++validationCount);
		outputValidationMap.put("Body Mass Index (kg/m^2)", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.functionalHierarchyScaleValue, "ADL-IADL Functional Hierarchy Scale 0-11 range", dataMap, ++validationCount);
		outputValidationMap.put("ADL-IADL Functional Hierarchy Scale 0-11 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.underNutritionCapValue, "Nutrition CAP", dataMap, ++validationCount);
		outputValidationMap.put("Nutrition CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.moodCapValue, "Mood CAP", dataMap, ++validationCount);
		outputValidationMap.put("Mood CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.informalSupportValue, "(Brittle) Informal Suppport CAP", dataMap, ++validationCount);
		outputValidationMap.put("(Brittle) Informal Suppport CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.urinaryIncontinenceValue, "Urinary Incontinence CAP", dataMap, ++validationCount);
		outputValidationMap.put("Urinary Incontinence CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.iadlImprovementCapValue, "IADL IMPROVEMENT CAP", dataMap, ++validationCount);
		outputValidationMap.put("IADL IMPROVEMENT CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.bowelCapValue, "Bowel CAP", dataMap, ++validationCount);
		outputValidationMap.put("Bowel CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.socialRelationshipsCapValue, "Social Function CAP", dataMap, ++validationCount);
		outputValidationMap.put("Social Function CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.cognitiveCapValue, "Cognitive CAP", dataMap, ++validationCount);
		outputValidationMap.put("Cognitive CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.institutionalRiskCapValue, "Institutional Risk CAP", dataMap, ++validationCount);
		outputValidationMap.put("Institutional Risk CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.careScaleValue, "Self-reliance Index", dataMap, ++validationCount);
		outputValidationMap.put("Self-reliance Index", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.environmentalCapsValue, "Environmental CAP", dataMap, ++validationCount);
		outputValidationMap.put("Environmental CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.preventionCapsValue, "Prevention CAP", dataMap, ++validationCount);
		outputValidationMap.put("Prevention CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.divertScaleValue, "sDIVERT - Divert Scale 1-6 range", dataMap, ++validationCount);
		outputValidationMap.put("sDIVERT - Divert Scale 1-6 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.sAgeValue, "sAGE Age in years", dataMap, ++validationCount);
		outputValidationMap.put("sAGE Age in years", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.chessScaleValue, "sCHESS Scale 0-5 range", dataMap, ++validationCount);
		outputValidationMap.put("sCHESS Scale 0-5 range", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.abusiveRelationshipCapsValue, "Abusive Relationship CAP", dataMap, ++validationCount);
		outputValidationMap.put("Abusive Relationship CAP", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.rug3aR3hValue, "aR3H RUG-III/HC GROUP CODE", dataMap, ++validationCount);
		outputValidationMap.put("aR3H RUG-III/HC GROUP CODE", result);
		passedCount += result;
		
		result = TestValidationHelper.validateOutput(capsAlgoPage.rug3aNR3hValue, "aNR3H RUG-III/HC GROUP NUMBER", dataMap, ++validationCount);
		outputValidationMap.put("aNR3H RUG-III/HC GROUP NUMBER", result);
		passedCount += result;
		// Note: ADL CAP and Age need to be added to CapsAndAlgoValuesPage if locators exist
		
		int failedCount = validationCount - passedCount;
		
		// Print validation summary
		TestValidationHelper.printValidationSummary(validationCount, passedCount, failedCount);
		
		// Store the output validation map in test result for the listener
		ITestResult testResult = Reporter.getCurrentTestResult();
		testResult.setAttribute("outputValidationMap", outputValidationMap);
		
		// Return results as array: [passed, failed]
		return new int[]{passedCount, failedCount};
	}
	
	/**
	 * Helper method to send keys only if data exists for that field
	 */
	private void sendKeysIfDataExists(WebElement element, String fieldKey) {
		TestValidationHelper.sendKeysIfDataExists(element, fieldKey, dataMap, command);
	}
	
	private void enterDateAsDDMMYYYY(WebElement element, String fieldKey) {
		
		String yyyyMMdd = dataMap.get(fieldKey);
		if (yyyyMMdd != null && !yyyyMMdd.trim().isEmpty() && !yyyyMMdd.equalsIgnoreCase("NA")) {
			try 
			{
		        if (yyyyMMdd == null || yyyyMMdd.length() != 8) {
		            throw new IllegalArgumentException("Date must be in yyyyMMdd format");
		        }
		        
		        try{Thread.sleep(2000);}catch(Exception e) {}
		
		        String year = yyyyMMdd.substring(0, 4);
		        String month = yyyyMMdd.substring(4, 6);
		        String day = yyyyMMdd.substring(6, 8);
		
		        String ddMMyyyy = day + month + year;
		
		        Actions actions = new Actions(driver);
		        
		        element.click();
		        actions
		        .moveToElement(element)
		        .click()
		        .sendKeys(ddMMyyyy)
		        .build()
		        .perform();
		        
		        try{Thread.sleep(3000);}catch(Exception e) {}
			}
			catch (Exception e) {
				System.out.println("⚠ Warning: Could not fill field " + fieldKey + " - " + e.getMessage());
			}
		}
    }
	
	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
