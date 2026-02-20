package testAll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.openqa.selenium.JavascriptExecutor;
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
import report.ComprehensiveTestReportManager;
import util.AppCommonActions;
import util.DataFile;
import util.OptimizedCommands;
import util.TestValidationHelper;
import util.TestLogger;

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
	private static List<String> passedScalesOrCaps = new ArrayList<>();
	private static List<String> failedScalesOrCaps = new ArrayList<>();
	private static int loggedInAndNavigatedToNJC = 0;
	
	public ComprehensiveAllSectionsTest(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp() {
		// Start logging to temp_logs.txt
		TestLogger.startLogging();
		
		base = new ComprehensiveAllSectionsTest(DataFile.master_DataFile);
		commonActions = new AppCommonActions();
		command = new OptimizedCommands();
		base.initialize();
		commonActions.loginToSalesForce(driver);
		commonActions.navigateToNJChoice(driver);
		loggedInAndNavigatedToNJC = 1;
	}
	
	@Test(dataProvider="DynamicDataProvider", dataProviderClass=dataproviders.MasterDataProvider.class, priority=1)
	public void testAllSectionsComprehensive(Object[] rowData) {
		try {
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
		
		// Print passed and failed scales/caps arrays
		if (passedScalesOrCaps.isEmpty()) {
			System.out.println("\n⚠️  No Scales/CAPs passed validation (passedScalesOrCaps array is empty)");
		} else {
			System.out.println("\n✅ Passed Scales/CAPs (" + passedScalesOrCaps.size() + "):");
			for (String scale : passedScalesOrCaps) {
				System.out.println("   - " + scale);
			}
		}
		
		if (failedScalesOrCaps.isEmpty()) {
			System.out.println("\n✅ No Scales/CAPs failed validation (failedScalesOrCaps array is empty)");
		} else {
			System.out.println("\n❌ Failed Scales/CAPs (" + failedScalesOrCaps.size() + "):");
			for (String scale : failedScalesOrCaps) {
				System.out.println("   - " + scale);
			}
		}
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		} catch (Exception e) {
			System.err.println("\n❌ TEST FAILED WITH EXCEPTION: " + e.getMessage());
			e.printStackTrace();
			
			// Print passed and failed scales/caps arrays even on exception
			if (passedScalesOrCaps.isEmpty()) {
				System.out.println("\n⚠️  No Scales/CAPs validated due to issues in execution (passedScalesOrCaps array is empty)");
			} else {
				System.out.println("\n✅ Passed Scales/CAPs (" + passedScalesOrCaps.size() + "):");
				for (String scale : passedScalesOrCaps) {
					System.out.println("   - " + scale);
				}
			}
			
			if (failedScalesOrCaps.isEmpty()) {
				System.out.println("\n✅ No Scales/CAPs validated due to issues in execution (failedScalesOrCaps array is empty)");
			} else {
				System.out.println("\n❌ Failed Scales/CAPs (" + failedScalesOrCaps.size() + "):");
				for (String scale : failedScalesOrCaps) {
					System.out.println("   - " + scale);
				}
			}
			
			throw new RuntimeException("Test execution failed", e);
		}
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
		
		try {Thread.sleep(15000);} catch (InterruptedException e) {e.printStackTrace();}
        
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
		// Clear the arrays at the start of validation
		passedScalesOrCaps.clear();
		failedScalesOrCaps.clear();
		
		// Navigate to CAPS and Algo tab and refresh values
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver, njChoicePage, command, capsAlgoPage);
		
		int validationCount = 0;
		int passedCount = 0;
		Map<String, Integer> outputValidationMap = new HashMap<>();
		int result;
		String capName;
		
		capName = "Cardio-respiratory CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.cCARDIOValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "aMAPLE Algorithm";
		result = TestValidationHelper.validateOutput(capsAlgoPage.aMAPLEvalue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sDRS Depression rating scale 0-14 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.sDRSValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Smoking and Drinking CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.smokingAndDrinkingValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Falls CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.fallsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Communication scale 0-8 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.sCommValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Dehydration CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.dehydrationValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Pain CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.painValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Delirium CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.deliriumValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Aggressive Behaviour Scale 0-12 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.aggressiveBehaviorValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Clinician Rated Mood Scale";
		result = TestValidationHelper.validateOutput(capsAlgoPage.clinicianRatedMoodValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Self Report Mood Scale";
		result = TestValidationHelper.validateOutput(capsAlgoPage.selfReportMoodScale, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "IADL Capacity Hierarchy Scale 0-6 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.iadlCapacityHierarchyScale, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "xFalls";
		result = TestValidationHelper.validateOutput(capsAlgoPage.fallsScale, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sPAIN scale 0-4 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.painScale, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sCPS scale 0-6 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.cognitivePerformanceScale, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Pressure Ulcer CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.pressureUlcerCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sPUR Scale 0-8 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.pressureUlcerRatingScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Feeding Tube CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.feedingTubeCapsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Behavior CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.behaviorCapsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Deafblind Severity Index 0-5 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.deafblindSeverityIndexValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Composite Mood Scale";
		result = TestValidationHelper.validateOutput(capsAlgoPage.compositeMoodScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Physical Activity Promotion CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.physicalActivityPromotionValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sCPS2 scale 0-8 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.cognitivePerformanceScale2Value, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sADL hierarchy scale 0-6 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.adlHierarchyScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Communication CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.communicationCapsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sADLSF ADL Short Form Scale 0-16 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.adlsfScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sADLLF ADL Long Form Scale 0-28 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.adllfScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "SPAIN1 scale 0-3 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.pain1ScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Body Mass Index (kg/m^2)";
		result = TestValidationHelper.validateOutput(capsAlgoPage.bmiScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "ADL-IADL Functional Hierarchy Scale 0-11 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.functionalHierarchyScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Nutrition CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.underNutritionCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Mood CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.moodCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "(Brittle) Informal Suppport CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.informalSupportValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Urinary Incontinence CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.urinaryIncontinenceValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "IADL IMPROVEMENT CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.iadlImprovementCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Bowel CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.bowelCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Social Function CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.socialRelationshipsCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Cognitive CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.cognitiveCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Institutional Risk CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.institutionalRiskCapValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Self-reliance Index";
		result = TestValidationHelper.validateOutput(capsAlgoPage.careScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Environmental CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.environmentalCapsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Prevention CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.preventionCapsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sDIVERT - Divert Scale 1-6 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.divertScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sAGE Age in years";
		result = TestValidationHelper.validateOutput(capsAlgoPage.sAgeValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "sCHESS Scale 0-5 range";
		result = TestValidationHelper.validateOutput(capsAlgoPage.chessScaleValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "Abusive Relationship CAP";
		result = TestValidationHelper.validateOutput(capsAlgoPage.abusiveRelationshipCapsValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "aR3H RUG-III/HC GROUP CODE";
		result = TestValidationHelper.validateOutput(capsAlgoPage.rug3aR3hValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
		
		capName = "aNR3H RUG-III/HC GROUP NUMBER";
		result = TestValidationHelper.validateOutput(capsAlgoPage.rug3aNR3hValue, capName, dataMap, ++validationCount);
		outputValidationMap.put(capName, result);
		passedCount += result;
		if (result == 1) passedScalesOrCaps.add(capName); else failedScalesOrCaps.add(capName);
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
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		try {
			if(loggedInAndNavigatedToNJC==0)
			{
				System.out.println("=========================================================================");
				System.out.println("FAILED EXECUTION: Could not login and navigate to NJ Choice Assessment page and all Caps and Scales failed");
			}
			String separator = "================================================================================";
			System.out.println("\n" + separator);
			System.out.println("TEST EXECUTION COMPLETED");
			System.out.println(separator + "\n");
			
			// Stop logging
			TestLogger.stopLogging();

			try {Thread.sleep(3000);} catch (InterruptedException e) {}
			
			// Run embeddings.py to create embeddings from logs
			System.out.println("\n🔄 Creating AI embeddings from test logs...");
			runPythonScript("embeddings.py");

			try {Thread.sleep(3000);} catch (InterruptedException e) {}
			
			// Run analysis.py to get AI analysis
			System.out.println("\n🤖 Generating AI Analysis of Test Execution...");
			//runPythonScript("analysis.py");
			String reportTimestamp = ComprehensiveTestReportManager.getReportFilePath();
			if (reportTimestamp != null) {
				// Extract timestamp from report filename (e.g., "ComprehensiveTestReport_16_02_2026__20_14_53.xlsx")
				int lastSlash = reportTimestamp.lastIndexOf("/");
				int lastDot = reportTimestamp.lastIndexOf(".xlsx");
				if (lastSlash >= 0 && lastDot > lastSlash) {
					String filename = reportTimestamp.substring(lastSlash + 1, lastDot);
					String timestamp = filename.replace("ComprehensiveTestReport_", "");
					runPythonScriptWithArgs("analysis.py", timestamp);
				} else {
					runPythonScript("analysis.py");
				}
			} else {
				runPythonScript("analysis.py");
			}
			
		} catch (Exception e) {
			System.err.println("⚠️  Error during teardown: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Always close browser, even if other teardown steps fail
			if (driver != null) {
				try {
					driver.quit();
				} catch (Exception e) {
					System.err.println("⚠️  Error closing browser: " + e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Helper method to run Python scripts from ai-analysis folder
	 */
	private void runPythonScript(String scriptName) {
		try {
			String projectDir = System.getProperty("user.dir");
			String aiAnalysisDir = projectDir + "/ai-analysis";
			String pythonPath = aiAnalysisDir + "/venv/bin/python3";
			String scriptPath = aiAnalysisDir + "/" + scriptName;
			
			ProcessBuilder pb = new ProcessBuilder(pythonPath, scriptPath);
			pb.directory(new java.io.File(aiAnalysisDir));
			pb.redirectErrorStream(true);
			
			Process process = pb.start();
			
			// Read and print output
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream())
			);
			
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("⚠️  Warning: " + scriptName + " exited with code " + exitCode);
			}
			
		} catch (Exception e) {
			System.err.println("❌ Error running " + scriptName + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper method to run Python scripts with arguments from ai-analysis folder
	 */
	private void runPythonScriptWithArgs(String scriptName, String... args) {
		try {
			String projectDir = System.getProperty("user.dir");
			String aiAnalysisDir = projectDir + "/ai-analysis";
			String pythonPath = aiAnalysisDir + "/venv/bin/python3";
			String scriptPath = aiAnalysisDir + "/" + scriptName;
			
			// Build command with arguments
			List<String> command = new ArrayList<>();
			command.add(pythonPath);
			command.add(scriptPath);
			for (String arg : args) {
				command.add(arg);
			}
			
			ProcessBuilder pb = new ProcessBuilder(command);
			pb.directory(new java.io.File(aiAnalysisDir));
			pb.redirectErrorStream(true);
			
			Process process = pb.start();
			
			// Read and print output
			BufferedReader reader = new BufferedReader(
				new InputStreamReader(process.getInputStream())
			);
			
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			int exitCode = process.waitFor();
			if (exitCode != 0) {
				System.err.println("⚠️  Warning: " + scriptName + " exited with code " + exitCode);
			}
			
		} catch (Exception e) {
			System.err.println("❌ Error running " + scriptName + ": " + e.getMessage());
			e.printStackTrace();
		}
	}
}
