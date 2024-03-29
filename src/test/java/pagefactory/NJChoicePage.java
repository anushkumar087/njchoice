package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class NJChoicePage {
	
private WebDriver driver;
	
	public NJChoicePage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	@FindBy(xpath ="//h1[text()='Create NJ Choice Assessment']")
	public WebElement CreateNJChoiceAssessmentHeader;
	
	@FindBy(xpath="//ul[@class='slds-tabs_default__nav']//a[contains(text(),'NJ CHOICE ASSESSMENT')]")
	public WebElement njChoiceAssessmentTab;
	
	
	@FindBy(xpath="//h2[contains(text(),'NJ Choice Assessment - Sections')]")
	public WebElement njChoiceAssessmentSections;
	
	@FindBy(xpath="//ul[@class='slds-tabs_default__nav']//a[contains(text(),'ALGORITHMS & CAPS')]")
	public WebElement capsAndAlgoTab;
	
	@CacheLookup
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'REFERRAL')]")
	public WebElement referralSectionA;
	
	@CacheLookup
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'INTAKE')]")
	public WebElement intakeSectionB;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'COGNITION')]")
	public WebElement cognitionSectionCandD;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'MOOD AND BEHAVIOR')]")
	public WebElement moodAndBehaviorSectionE;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'DISEASE')]")
	public WebElement diseaseDiagnosesSectionI;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'HEALTH CONDITIONS')]")
	public WebElement healthConditionsSectionJ;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'ORAL AND NUTRITIONAL STATUS')]")
	public WebElement oralAndNutritionalStatusSectionKAndL;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'FUNCTIONAL STATUS')]")
	public WebElement FunctionalStatusSectionGandH;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'MEDICATIONS')]")
	public WebElement medicationsAndTreatmentsSectionMandN;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'SOCIAL SUPPORT')]")
	public WebElement socialSupportSectionPQandR;
	

}
