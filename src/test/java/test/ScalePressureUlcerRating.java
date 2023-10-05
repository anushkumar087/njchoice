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
public class ScalePressureUlcerRating extends Base{
	
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
	
	public ScalePressureUlcerRating(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new ScaleCommunication(DataFile.ScalePressureUlcerRating_DataFile);
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
	
	
	@Test(dataProvider="PressureUlcerRatingScale", priority=17)
	void scalePressureUlcerRating(String iG2e, String iG2i, String iH3, String iJ3, 
			String iJ5a, String iK2a, String iL2, String output)
	{
		Reusables.setIOMapping(IOMapping.getPressureUlcerRatingScaleInputs(),IOMapping.getPressureUlcerRatingScaleOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section G and H
		njChoicePage.FunctionalStatusSectionGandH.click();
		
		functionalStatusPage = PageFactory.initElements(driver, FunctionalStatusPage.class);
		functionalStatusPage.clickEditButtonIfDisplayed(functionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatusPage.iG2e, iG2e);
		command.sendKeysWithBuffer(functionalStatusPage.iG2i, iG2i);
		command.sendKeysWithBuffer(functionalStatusPage.iH3, iH3);
		

		functionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(functionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section J
		njChoicePage.healthConditionsSectionJ.click();
				
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ3, iJ3);
		command.sendKeysWithBuffer(healthConditions.iJ5a, iJ5a);
		
		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Enter Section L
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
				
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2a, iK2a);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iL2, iL2);
		
		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.pressureUlcerRatingScaleValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="PressureUlcerRatingScale")
	public static Object[][] getTestDataPressureUlcerRatingScale() throws IOException
	{
		String[] requiredInputs = IOMapping.getPressureUlcerRatingScaleInputs();
		String output = IOMapping.getPressureUlcerRatingScaleOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.ScalePressureUlcerRating_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
