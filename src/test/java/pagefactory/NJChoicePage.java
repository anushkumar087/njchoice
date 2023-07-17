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
	
	@FindBy(xpath="//ul[@class='slds-tabs_default__nav']//a[contains(text(),'Caps And Algo Values')]")
	public WebElement capsAndAlgoTab;
	
	@CacheLookup
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'REFERRAL')]")
	public WebElement referralSectionA;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'MOOD AND BEHAVIOR')]")
	public WebElement moodAndBehaviorSectionE;
	
	@FindBy(xpath="//div[@role='list']//label[contains(text(),'HEALTH CONDITIONS')]")
	public WebElement healthConditionsSectionJ;
	

}
