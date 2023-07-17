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
//			else 
//				healthConditions.UpdateButton.click();
		}
		catch(Exception e)
		{
			moodAndBehavior.UpdateButton.click();
		}
	}

}
