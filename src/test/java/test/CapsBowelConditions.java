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
public class CapsBowelConditions extends Base{
	
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
	
	public CapsBowelConditions(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsBowelConditions(DataFile.CapsBowel_DataFile);
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
	
	
	@Test(dataProvider="BowelCap", priority=36)
	void capBowel(String iB10,String iC1,String iC3a,String iC3b,String iC3c,String iC4,String iG2h,String iG2i,String iG2j,String iG5b,
			String iH1,String iH3,String iI1a,String iI1q,String output)
	{
		Reusables.setIOMapping(IOMapping.getBowelCapInputs(),IOMapping.getBowelCapOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Go to section C and enter required values
		njChoicePage.cognitionSectionCandD.click();
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iC1, iC1);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3a, iC3a);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3b, iC3b);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3c, iC3c);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC4, iC4);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section G and enter required values
		njChoicePage.FunctionalStatusSectionGandH.click();
		functionalStatus = PageFactory.initElements(driver, FunctionalStatusPage.class);
		
		functionalStatus.clickEditButtonIfDisplayed(functionalStatus);
		
		// Enter all required values
		command.sendKeysWithBuffer(functionalStatus.iG2h, iG2h);
		command.sendKeysWithBuffer(functionalStatus.iG2i, iG2i);
		command.sendKeysWithBuffer(functionalStatus.iG2j, iG2j);
		command.sendKeysWithBuffer(functionalStatus.iG5b, iG5b);
		command.sendKeysWithBuffer(functionalStatus.iH1, iH1);
		command.sendKeysWithBuffer(functionalStatus.iH3, iH3);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section I and enter required values
		njChoicePage.diseaseDiagnosesSectionI.click();
		diseaseDiagnosesPage = PageFactory.initElements(driver, DiseaseDiagnosesPage.class);
		
		diseaseDiagnosesPage.clickEditButtonIfDisplayed(diseaseDiagnosesPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1a, iI1a);
		command.sendKeysWithBuffer(diseaseDiagnosesPage.iI1q, iI1q);
		
		diseaseDiagnosesPage.saveOrUpdateAfterEnteringRequiredFields(diseaseDiagnosesPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);

		// Enter Section B
		njChoicePage.intakeSectionB.click();
				
		intakePage = PageFactory.initElements(driver, IntakePage.class);
		
		intakePage.clickEditButtonIfDisplayed(intakePage);
		
		// Enter all required values
		command.sendKeysWithBuffer(intakePage.iB10, iB10);
		
		intakePage.saveOrUpdateAfterEnteringRequiredFields(intakePage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.bowelCapValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="BowelCap")
	public static Object[][] getTestDataCapBowel() throws IOException
	{
		String[] requiredInputs = IOMapping.getBowelCapInputs();
		String output = IOMapping.getBowelCapOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsBowel_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}


}
