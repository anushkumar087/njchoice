package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class CapsAndAlgoValuesPage {

	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public CapsAndAlgoValuesPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//h1/b[contains(text(),'Cardiorespiratory Conditions')]/..")
	public WebElement cCARDIOValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'SComm Value')]/..")
	public WebElement sCommValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Depression Rating Scale')]/..")
	public WebElement sDRSValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Tobacco and Alcohol Use (Smoking and Drinking)')]/..")
	public WebElement smokingAndDrinkingValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Falls:')]/..")
	public WebElement fallsValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Dehydration:')]/..")
	public WebElement dehydrationValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Pain:')]/..")
	public WebElement painValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Delirium:')]/..")
	public WebElement deliriumValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Aggressive Behavior:')]/..")
	public WebElement aggressiveBehaviorValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Clinician-Rated Mood Scale:')]/..")
	public WebElement clinicianRatedMoodValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Self-Report Mood Scale:')]/..")
	public WebElement selfReportMoodScale;
	
	@FindBy(xpath="//h1/b[contains(text(),'Instrumental Hierarchy - Capacity:')]/..")
	public WebElement iadlCapacityHierarchyScale;
	
	@FindBy(xpath="//h1/b[contains(text(),'Falls Algorithm:')]/..")
	public WebElement fallsScale;
	
	@FindBy(xpath="//h1/b[contains(text(),'Pain Scale')]/..")
	public WebElement painScale;
	
	@FindBy(xpath="//h1/b[contains(text(),'Cognitive Performance Scale:')]/..")
	public WebElement cognitivePerformanceScale;
	
	@FindBy(xpath="//h1/b[contains(text(),'Pressure Ulcer:')]/..")
	public WebElement pressureUlcerCapValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Pressure Ulcer Rating Scale:')]/..")
	public WebElement pressureUlcerRatingScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Feeding Tube:')]/..")
	public WebElement feedingTubeCapsValue;
	
	@FindBy(xpath="//h1/b[starts-with(text(),'Behavior:')]/..")
	public WebElement behaviorCapsValue;
	
	@FindBy(xpath="//h1/b[starts-with(text(),'Deafblind Severity Index:')]/..")
	public WebElement deafblindSeverityIndexValue;
	
	@FindBy(xpath="//h1/b[starts-with(text(),'Composite Mood Scale:')]/..")
	public WebElement compositeMoodScaleValue;
	
	@FindBy(xpath="//h1/b[starts-with(text(),'Physical Activity Promotion:')]/..")
	public WebElement physicalActivityPromotionValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Cognitive Performance Scale 2:')]/..")
	public WebElement cognitivePerformanceScale2Value;
	
	@FindBy(xpath="//h1/b[contains(text(),'ADL Hierarchy:')]/..")
	public WebElement adlHierarchyScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Communication:')]/..")
	public WebElement communicationCapsValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'ADL Scale - Short form:')]/..")
	public WebElement adlsfScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'ADL Scale - Long form:')]/..")
	public WebElement adllfScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Pain Scale(sPAIN1):')]/..")
	public WebElement pain1ScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Body Mass Index:')]/..")
	public WebElement bmiScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Functional Hierarchy:')]/..")
	public WebElement functionalHierarchyScaleValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Undernutrition:')]/..")
	public WebElement underNutritionCapValue;
	
	@FindBy(xpath="//h1/b[contains(text(),'Mood:')]/..")
	public WebElement moodCapValue;
	
	
	
	@FindBy(xpath="//lightning-tab[contains(@class,'slds-show')]//button[text()='Refresh']")
	public WebElement refreshButton;
	
	//h1/b[contains(text(),'Communication:')]/..
}
