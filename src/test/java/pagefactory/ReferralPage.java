package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import util.OptimizedCommands;
import util.DataFile;

public class ReferralPage {
	
	public static int startingReferencePositionOfNJChoiceSections=0;
	private OptimizedCommands command;
	private WebDriver driver;
	
	public ReferralPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	
	@FindBy(xpath ="//label[contains(text(),'REFERRAL DATE')]/following-sibling::input")
	public WebElement referralDate;
	
	@FindBy(xpath ="//label[contains(text(),'REFERRAL ENTRY DATE')]/following-sibling::input")
	public WebElement referralEntryDate;
	
	@FindBy(xpath ="//label[contains(text(),'REFERRAL/ASSESSMENT SOURCE')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement referralAssessmentSource;
	
	@FindBy(xpath ="//label[contains(text(),'Assessment Organization Name')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement assessmentOrganizationName;
	
	@FindBy(xpath ="//label[contains(text(),'REASON FOR ASSESSMENT')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement reasonForAssessment;
	
	@FindBy(xpath ="//label[contains(text(),'First Name')]/following-sibling::*//input")
	public WebElement firstName;
	
	@FindBy(xpath ="//label[contains(text(),'Last Name')]/following-sibling::*//input")
	public WebElement lastName;
	
	@FindBy(xpath="//label[not(contains(text(),'IDENTITY')) and contains(text(),' GENDER')]/parent::div/..//input")
	public WebElement gender;
	
	@FindBy(xpath="//label[contains(text(),'GENDER IDENTITY')]/parent::div/..//input")
	public WebElement genderIdentity;
	
	@FindBy(xpath="//label[contains(text(),'BIRTHDATE')]/following-sibling::input")
	public WebElement birthdate;
	
	@FindBy(xpath="//label[contains(text(),'Social Security Number')]/following-sibling::*//input[@pattern]")
	public WebElement socialSecurityNumber;
	
	@FindBy(xpath="//span[contains(text(),'ASSESSMENT LOCATION')]/ancestor::lightning-accordion//label[contains(text(),'Street Address 1')]/following-sibling::*//input[@required]")
	public WebElement streetAddress1_1;
	
	@FindBy(xpath="//span[contains(text(),'ASSESSMENT LOCATION')]/ancestor::lightning-accordion//label[contains(text(),'City')]/following-sibling::*//input[@required]")
	public WebElement city_1;
	
	@FindBy(xpath="//span[contains(text(),'ASSESSMENT LOCATION')]/ancestor::lightning-accordion//label[contains(text(),'State')]/following-sibling::*//input[@required]")
	public WebElement state_1;
	
	@FindBy(xpath="//span[contains(text(),'ASSESSMENT LOCATION')]/ancestor::lightning-accordion//label[contains(text(),'Zip Code')]/following-sibling::*//input")
	public WebElement zipCode_1;
	
	@FindBy(xpath="//label[contains(text(),'County')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement county;
	
	@FindBy(xpath="//span[contains(text(),'RESIDENTIAL ADDRESS')]/ancestor::lightning-accordion//label[contains(text(),'Street Address 1')]/following-sibling::*//input[@required]")
	public WebElement streetAddress1_2;
	
	@FindBy(xpath="//span[contains(text(),'RESIDENTIAL ADDRESS')]/ancestor::lightning-accordion//label[contains(text(),'City')]/following-sibling::*//input[@required]")
	public WebElement city_2;
	
	@FindBy(xpath="//span[contains(text(),'RESIDENTIAL ADDRESS')]/ancestor::lightning-accordion//label[contains(text(),'State')]/following-sibling::*//input[@required]")
	public WebElement state_2;
	
	@FindBy(xpath="//span[contains(text(),'RESIDENTIAL ADDRESS')]/ancestor::lightning-accordion//label[contains(text(),'Zip Code')]/following-sibling::*//input[@required]")
	public WebElement zipCode_2;
	
	@FindBy(xpath="//span[contains(text(),'REFERRAL')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'REFERRAL')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	
	
	public void saveOrUpdateAfterEnteringRequiredFields(ReferralPage referralPage)
	{
		try 
		{
			if(referralPage.SaveAndNextButton.isDisplayed())
				referralPage.SaveAndNextButton.click();
			else
				referralPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			referralPage.UpdateButton.click();
		}
	}
	
	
	public void enterAllMandatoryFields()
	{
		NJChoicePage njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);
		
		startingReferencePositionOfNJChoiceSections = njChoicePage.njChoiceAssessmentSections.getLocation().getY();
		
		// command.sendKeysWithBuffer(referralDate, DataFile.ReferralDateValue);
		command.selectPresentDate(referralDate, DataFile.ReferralDateValue, driver);
		
		// command.sendKeysWithBuffer(referralEntryDate, DataFile.ReferralEntryDateValue);
		// command.selectPresentDate(referralEntryDate, DataFile.ReferralEntryDateValue, driver);
		
		command.sendKeysWithBuffer(referralAssessmentSource, DataFile.ReferralAssessmentSourceValue);
		
		command.sendKeysWithBuffer(assessmentOrganizationName, DataFile.AssessmentOrganizationNameValue);
		
		command.sendKeysWithBuffer(reasonForAssessment, DataFile.ReasonForAssessmentValue);
		
		command.sendKeysWithBuffer(firstName, DataFile.FirstNameValue);
		
		command.sendKeysWithBuffer(lastName, DataFile.LastNameValue);
		
		command.sendKeysWithBuffer(gender, DataFile.GenderValue);
		
		command.sendKeysWithBuffer(genderIdentity, DataFile.GenderIdentityValue);
		
		// command.sendKeysWithBuffer(birthdate, DataFile.BirthdateValue);
		command.selectPresentDate(birthdate, DataFile.BirthdateValue, driver);
		
		command.sendKeysWithBuffer(socialSecurityNumber, DataFile.SocialSecurityNumberValue);
		
		command.sendKeysWithBuffer(streetAddress1_1, DataFile.StreetAddress1Value);
		
		command.sendKeysWithBuffer(city_1, DataFile.City1Value);
		command.sendKeysWithBuffer(zipCode_1, DataFile.ZipCode1Value);
		command.sendKeysWithBuffer(county, DataFile.CountyValue);
		
		command.sendKeysWithBuffer(streetAddress1_2, DataFile.StreetAddress1Value);
		command.sendKeysWithBuffer(city_2, DataFile.City1Value);
		command.sendKeysWithBuffer(zipCode_2, DataFile.ZipCode1Value);
	}
	
	
	
}
