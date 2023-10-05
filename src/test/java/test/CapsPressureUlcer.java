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
import pagefactory.MedicationsAndTreatmentsPage;
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
public class CapsPressureUlcer extends Base{
	
	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	OralAndNutritionalStatusPage oralAndNutritionalStatusPage;
	CapsAndAlgoValuesPage capsAlgoPage;
	CommunicationAndVisionPage communicationAndVisionPage;
	FunctionalStatusPage functionalStatusPage;
	MedicationsAndTreatmentsPage medicationsAndTreatmentsPage;
	
	public CapsPressureUlcer(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCommunication(DataFile.CapsPressureUlcer_DataFile);
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
	
	
	@Test(dataProvider="PressureUlcerCaps", priority=16)
	void capsPressureUlcer(String iG2g, String iG2i, String iH1, String iH2, 
			String iL1, String iL2, String iL3, String iN2k, String iN2m, String output)
	{
		Reusables.setIOMapping(IOMapping.getPressureUlcerInputs(),IOMapping.getPressureUlcerOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section G and H
		njChoicePage.FunctionalStatusSectionGandH.click();
		
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG2g, iG2g);
		command.sendKeysWithBuffer(functionalStatusPage.iG2i, iG2i);
		command.sendKeysWithBuffer(functionalStatusPage.iH1, iH1);
		command.sendKeysWithBuffer(functionalStatusPage.iH2, iH2);
		

		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section L
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
				
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL1, iL1);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL2, iL2);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL3, iL3);
		
		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section N
		njChoicePage.medicationsAndTreatmentsSectionMandN.click();
				
		medicationsAndTreatmentsPage = PageFactory.initElements(driver, MedicationsAndTreatmentsPage.class);
		medicationsAndTreatmentsPage.clickEditButtonIfDisplayed(medicationsAndTreatmentsPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2k, iN2k);
		command.sendKeysWithBuffer(medicationsAndTreatmentsPage.iN2m, iN2m);
		
		medicationsAndTreatmentsPage.saveOrUpdateAfterEnteringRequiredFields(medicationsAndTreatmentsPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.pressureUlcerCapValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="PressureUlcerCaps")
	public static Object[][] getTestDataPressureUlcerCaps() throws IOException
	{
		String[] requiredInputs = IOMapping.getPressureUlcerInputs();
		String output = IOMapping.getPressureUlcerOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsPressureUlcer_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
