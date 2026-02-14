package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Base;
import util.OptimizedCommands;

public class LevelOfCarePage {


	private OptimizedCommands command;
	private WebDriver driver;
	
	public LevelOfCarePage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'O1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iO1;
	
	@FindBy(xpath ="//label[contains(text(),'O2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iO2;
	
	@FindBy(xpath="//span[contains(text(),'LEVEL OF CARE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'LEVEL OF CARE')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'LEVEL OF CARE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	public void fillCompletionSectionWithDefaults() {
		command.sendKeysWithBuffer(iO1, "0");
		command.sendKeysWithBuffer(iO2, "0");
	}
	
	public void clickEditButtonIfDisplayed(LevelOfCarePage levelOfCarePage) 
	{
		try 
		{
			if(levelOfCarePage.EditButton.isDisplayed())
				levelOfCarePage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveOrUpdateAfterEnteringRequiredFields(LevelOfCarePage levelOfCarePage)
	{
		try 
		{
			if(levelOfCarePage.SaveAndNextButton.isDisplayed())
				levelOfCarePage.SaveAndNextButton.click();
			else
				levelOfCarePage.UpdateButton.click();
		}
		catch(Exception e)
		{
			levelOfCarePage.UpdateButton.click();
		}
	}
}
