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
public class CapsPrevention extends Base{
	
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
	
	public CapsPrevention(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsPrevention(DataFile.CapsPrevention_DataFile);
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
	
	
	@Test(dataProvider="PreventionCap", priority=42)
	void capPrevention(String iA2,String iN1a,String iN1b,String iN1c,String iN1d,String iN1e,String iN1f,String iN1g,String iN1h,String iN4c,String output)
	{
		Reusables.setIOMapping(IOMapping.getPreventionCapsInputs(),IOMapping.getPreventionCapsOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Go to section A and enter required values
		njChoicePage.referralSectionA.click();
		
		referralPage = PageFactory.initElements(driver, ReferralPage.class);
		
		
		// Enter all required values
		command.sendKeysWithBuffer(referralPage.gender, iA2);
		command.sendKeysWithBuffer(referralPage.genderIdentity, iA2);
		
		referralPage.saveOrUpdateAfterEnteringRequiredFields(referralPage);
		commonActions.handleAlertsAfterSaveReferralSection(driver, command);
		
		// Enter Section N
		njChoicePage.medicationsAndTreatmentsSectionMandN.click();
				
		medicationsAndTreatmentsPage = PageFactory.initElements(driver, MedicationsAndTreatmentsPage.class);
		medicationsAndTreatmentsPage.clickEditButtonIfDisplayed(medicationsAndTreatmentsPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1a, iN1a);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1b, iN1b);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1c, iN1c);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1d, iN1d);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1e, iN1e);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1f, iN1f);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1g, iN1g);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN1h, iN1h);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN4c, iN4c);
		
		medicationsAndTreatmentsPage.saveOrUpdateAfterEnteringRequiredFields(medicationsAndTreatmentsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.preventionCapsValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="PreventionCap")
	public static Object[][] getTestDataCapPrevention() throws IOException
	{
		String[] requiredInputs = IOMapping.getPreventionCapsInputs();
		String output = IOMapping.getPreventionCapsOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsPrevention_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
