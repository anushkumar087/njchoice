package test;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.Base;
import pagefactory.CapsAndAlgoValuesPage;
import pagefactory.CommunicationAndVisionPage;
import pagefactory.HealthConditionsPage;
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
public class CapsDehydration extends Base{

	Base base;
	AppCommonActions commonActions;
	NJChoicePage njChoicePage;
	OptimizedCommands command;
	ReferralPage referralPage;
	HealthConditionsPage healthConditions;
	MoodAndBehaviorPage moodAndBehavior;
	CapsAndAlgoValuesPage capsAlgoPage;
	OralAndNutritionalStatusPage oralAndNutritionalStatusPage;
	CommunicationAndVisionPage communicationAndVisionPage;
	
	public CapsDehydration(String dataFile) {
		super(dataFile);
	}
	
	@BeforeTest
	public void setUp()
	{
		base = new CapsDehydration(DataFile.CapsDehydration_DataFile);
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
	
	
	@Test(dataProvider="CapsDehydration", priority=2)
	void capsDehydration(String ic3a, String ic3b, String ic3c, String ic4,
			String ij2c, String ij2k, String ij2l, String ij2n, String ij2q,
			String ik2a, String ik2b, String ik2c, String output)
	{	

		Reusables.setIOMapping(IOMapping.getCapsDehydrationInputs(),IOMapping.getCapsDehydrationOutput(),new Object(){}.getClass().getEnclosingMethod().getName());
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		// Go to section C and enter required values
		njChoicePage.cognitionSectionCandD.click();
		communicationAndVisionPage = PageFactory.initElements(driver, CommunicationAndVisionPage.class);
		
		communicationAndVisionPage.clickEditButtonIfDisplayed(communicationAndVisionPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3a, ic3a);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3b, ic3b);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC3c, ic3c);
		command.sendKeysWithBuffer(communicationAndVisionPage.iC4, ic4);
		
		communicationAndVisionPage.saveOrUpdateAfterEnteringRequiredFields(communicationAndVisionPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
				
				
		// Go to section J and enter required values
		njChoicePage.healthConditionsSectionJ.click();
		healthConditions = PageFactory.initElements(driver, HealthConditionsPage.class);
		
		healthConditions.clickEditButtonIfDisplayed(healthConditions);
		
		// Enter all required values
		command.sendKeysWithBuffer(healthConditions.iJ2c, ij2c);
		command.sendKeysWithBuffer(healthConditions.iJ2k, ij2k);
		command.sendKeysWithBuffer(healthConditions.iJ2l, ij2l);
		command.sendKeysWithBuffer(healthConditions.iJ2n, ij2n);
		command.sendKeysWithBuffer(healthConditions.iJ2q, ij2q);
		
		healthConditions.saveOrUpdateAfterEnteringRequiredFields(healthConditions);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		// Go to section K and enter required values
		njChoicePage.oralAndNutritionalStatusSectionKAndL.click();
		oralAndNutritionalStatusPage = PageFactory.initElements(driver, OralAndNutritionalStatusPage.class);
		
		oralAndNutritionalStatusPage.clickEditButtonIfDisplayed(oralAndNutritionalStatusPage);
		
		// Enter all required values
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2a, ik2a);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2b, ik2b);
		command.sendKeysWithBuffer(oralAndNutritionalStatusPage.iK2c, ik2c);
		
		oralAndNutritionalStatusPage.saveOrUpdateAfterEnteringRequiredFields(oralAndNutritionalStatusPage);
		commonActions.handleAlertsAfterSaveOrUpdateInputsSection(driver, command);
		
		
		// Wait for 5 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
				
		if(njChoicePage.capsAndAlgoTab.getAttribute("aria-selected").equals("false"))
			njChoicePage.capsAndAlgoTab.click();
		
		command.waitAfterNavigatingToCapsAndAlgoTab();
		capsAlgoPage = PageFactory.initElements(driver, CapsAndAlgoValuesPage.class);
		capsAlgoPage.refreshButton.click();
		command.waitAfterClickingRefreshOnCapsAndAlgoTab();
		
		Reusables.validateOutput(capsAlgoPage.dehydrationValue,output);
		
		njChoicePage.njChoiceAssessmentTab.click();
		
		
	}
	
	
	@DataProvider(name="CapsDehydration")
	public static Object[][] getTestDataCapsFalls() throws IOException
	{
		String[] requiredInputs = IOMapping.getCapsDehydrationInputs();
		String output = IOMapping.getCapsDehydrationOutput();
		Object[][] testData = TestData.getRequiredDataFromMasterSheet(requiredInputs, output, DataFile.CapsDehydration_DataFile);
		return testData;
		
	}
	
	
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
