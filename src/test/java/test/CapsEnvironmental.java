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
public class CapsEnvironmental extends Base{
	
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
	
	public CapsEnvironmental(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsEnvironmental(DataFile.CapsEnvironmental_DataFile);
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
	
	
	@Test(dataProvider="EnvironmentalCap", priority=41)
	void capEnvironmental(String iE1a,String iE1b,String iE1c,String iE1d,String iE1e,String iE1f,String iE1g,String iG1fb,String iG4a,String iJ2d,
			String iJ2g,String iJ2h,String iJ2i,String iJ6a,String iJ7a,String iQ1a,String iQ1b,String iQ1c,String iQ1e,String output)
	{
		Reusables.setIOMapping(IOMapping.getEnvironmentalCapsInputs(),IOMapping.getEnvironmentalCapsOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Enter Section E
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
		command.sendKeysWithBuffer(functionalStatus.iG1fb, iG1fb);
		command.sendKeysWithBuffer(functionalStatus.iG4a, iG4a);
		
		functionalStatus.saveOrUpdateAfterEnteringRequiredFields(functionalStatus);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		

		// Go to section J and enter required values
		njChoicePage.healthConditionsSectionJ.click();
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ2d, iJ2d);
		command.sendKeysWithBuffer(healthConditions.iJ2g, iJ2g);
		command.sendKeysWithBuffer(healthConditions.iJ2h, iJ2h);
		command.sendKeysWithBuffer(healthConditions.iJ2i, iJ2i);
		command.sendKeysWithBuffer(healthConditions.iJ6a, iJ6a);
		command.sendKeysWithBuffer(healthConditions.iJ7a, iJ7a);
		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		// Go to section Q and enter required values
		njChoicePage.socialSupportSectionPQandR.click();
		socialSupportPage = PageFactory.initElements(driver, SocialSupportPage.class);
		
		socialSupportPage.clickEditButtonIfDisplayed(socialSupportPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(socialSupportPage.iQ1a, iQ1a);
		command.sendKeysWithBuffer(socialSupportPage.iQ1b, iQ1b);
		command.sendKeysWithBuffer(socialSupportPage.iQ1c, iQ1c);
		command.sendKeysWithBuffer(socialSupportPage.iQ1e, iQ1e);
		
		socialSupportPage.saveOrUpdateAfterEnteringRequiredFields(socialSupportPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
				
				
		
		capsAlgoPage = commonActions.navigateToCapsAndAlgoTabAndRefreshValues(driver,njChoicePage,command,capsAlgoPage);
		
		Reusables.validateOutput(capsAlgoPage.environmentalCapsValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
	}
	
	
	@DataProvider(name="EnvironmentalCap")
	public static Object[][] getTestDataCapInstitutionalRisk() throws IOException
	{
		String[] requiredInputs = IOMapping.getEnvironmentalCapsInputs();
		String output = IOMapping.getEnvironmentalCapsOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsEnvironmental_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
