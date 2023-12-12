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
public class CapsIADLImprovement extends Base{
	
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
	
	public CapsIADLImprovement(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsIADLImprovement(DataFile.CapsIADLImprovement_DataFile);
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
	
	
	@Test(dataProvider="IADLImprovementCap", priority=35)
	void capIADLImprovement(String iC1,String iC2a,String iD1,String iG1ab,String iG1bb,String iG1gb,String iG1hb,String iG2a,String iG2b,
			String iG2f,String iG2h,String iG2j,String iG5a,String iG5b,String iG6,String output)
	{
		Reusables.setIOMapping(IOMapping.getIADLImprovementCapInputs(),IOMapping.getIADLImprovementCapOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
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
		command.sendKeysWithBuffer(functionalStatus.iG1ab, iG1ab);
		command.sendKeysWithBuffer(functionalStatus.iG1bb, iG1bb);
		command.sendKeysWithBuffer(functionalStatus.iG1gb, iG1gb);
		command.sendKeysWithBuffer(functionalStatus.iG1hb, iG1hb);
		
		command.sendKeysWithBuffer(functionalStatus.iG2a, iG2a);
		command.sendKeysWithBuffer(functionalStatus.iG2b, iG2b);
		command.sendKeysWithBuffer(functionalStatus.iG2f, iG2f);
		command.sendKeysWithBuffer(functionalStatus.iG2h, iG2h);
		command.sendKeysWithBuffer(functionalStatus.iG2j, iG2j);
		
		command.sendKeysWithBuffer(functionalStatus.iG5a, iG5a);
		command.sendKeysWithBuffer(functionalStatus.iG5b, iG5b);
		command.sendKeysWithBuffer(functionalStatus.iG6, iG6);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.urinaryIncontinenceValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="IADLImprovementCap")
	public static Object[][] getTestDataCapIADLImprovement() throws IOException
	{
		String[] requiredInputs = IOMapping.getIADLImprovementCapInputs();
		String output = IOMapping.getIADLImprovementCapOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsIADLImprovement_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
