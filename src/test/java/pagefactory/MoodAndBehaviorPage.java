package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class MoodAndBehaviorPage {
	private OptimizedCommands command;
	private WebDriver driver;
	
	public MoodAndBehaviorPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'E1a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1a;
	
	@FindBy(xpath ="//label[contains(text(),'E1b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1b;
	
	@FindBy(xpath ="//label[contains(text(),'E1c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1c;
	
	@FindBy(xpath ="//label[contains(text(),'E1d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1d;
	
	@FindBy(xpath ="//label[contains(text(),'E1e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1e;
	
	@FindBy(xpath="//label[contains(text(),'E1f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1f;
	
	@FindBy(xpath="//label[contains(text(),'E1g')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1g;
	
	@FindBy(xpath="//label[contains(text(),'E1h')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1h;
	
	@FindBy(xpath="//label[contains(text(),'E1i')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1i;
	
	@FindBy(xpath="//label[contains(text(),'E1j')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1j;
	
	@FindBy(xpath="//label[contains(text(),'E1k')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE1k;
	
	@FindBy(xpath="//label[contains(text(),'E2a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE2a;
	
	@FindBy(xpath="//label[contains(text(),'E2b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE2b;
	
	@FindBy(xpath="//label[contains(text(),'E2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE2c;
	
	@FindBy(xpath="//label[contains(text(),'E3a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE3a;
	
	@FindBy(xpath="//label[contains(text(),'E3b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE3b;
	
	@FindBy(xpath="//label[contains(text(),'E3c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE3c;
	
	@FindBy(xpath="//label[contains(text(),'E3d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE3d;
	
	@FindBy(xpath="//label[contains(text(),'E3e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE3e;
	
	@FindBy(xpath="//label[contains(text(),'E3f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iE3f;
	
	@FindBy(xpath="//label[contains(text(),'F1d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iF1d;
	
	@FindBy(xpath="//label[contains(text(),'F1e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iF1e;
	
	@FindBy(xpath="//label[contains(text(),'F1f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iF1f;
	
	@FindBy(xpath="//label[contains(text(),'F2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iF2;
	
	@FindBy(xpath="//label[contains(text(),'F3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iF3;
	
	@FindBy(xpath="//label[contains(text(),'F4')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iF4;
	
	@FindBy(xpath="//span[contains(text(),'MOOD AND BEHAVIOR')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'MOOD AND BEHAVIOR')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'MOOD AND BEHAVIOR')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	

	public void clickEditButtonIfDisplayed(MoodAndBehaviorPage moodAndBehavior) 
	{
		try 
		{
			if(moodAndBehavior.EditButton.isDisplayed())
				moodAndBehavior.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}

	public void saveOrUpdateAfterEnteringRequiredFields(MoodAndBehaviorPage moodAndBehavior)
	{
		try 
		{
			if(moodAndBehavior.SaveAndNextButton.isDisplayed())
				moodAndBehavior.SaveAndNextButton.click();
			else
				moodAndBehavior.UpdateButton.click();
		}
		catch(Exception e)
		{
			moodAndBehavior.UpdateButton.click();
		}
	}

}
