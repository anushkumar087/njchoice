package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class HealthConditionsPage {

	private OptimizedCommands command;
	private WebDriver driver;
	
	public HealthConditionsPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'J2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2c;
	
	@FindBy(xpath ="//label[contains(text(),'J2e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2e;
	
	@FindBy(xpath ="//label[contains(text(),'J3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ3;
	
	@FindBy(xpath ="//label[contains(text(),'J8a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ8a;
	
	@FindBy(xpath ="//label[contains(text(),'J8b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ8b;
	
	@FindBy(xpath="//span[contains(text(),'HEALTH CONDITIONS')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'HEALTH CONDITIONS')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'HEALTH CONDITIONS')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;

	public void clickEditButtonIfDisplayed(HealthConditionsPage healthConditions) 
	{
		try 
		{
			if(healthConditions.EditButton.isDisplayed())
				healthConditions.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}

	public void saveOrUpdateAfterEnteringRequiredFields(HealthConditionsPage healthConditions)
	{
		try 
		{
			if(healthConditions.SaveAndNextButton.isDisplayed())
				healthConditions.SaveAndNextButton.click();
//			else 
//				healthConditions.UpdateButton.click();
		}
		catch(Exception e)
		{
			healthConditions.UpdateButton.click();
		}
	}
	
	
	
}
