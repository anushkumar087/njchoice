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
	
	@FindBy(xpath ="//label[contains(text(),'J1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ1;
	
	@FindBy(xpath ="//label[contains(text(),'J2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2c;
	
	@FindBy(xpath ="//label[contains(text(),'J2d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2d;
	
	@FindBy(xpath ="//label[contains(text(),'J2e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2e;
	
	@FindBy(xpath ="//label[contains(text(),'J2g')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2g;
	
	@FindBy(xpath ="//label[contains(text(),'J2h')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2h;
	
	@FindBy(xpath ="//label[contains(text(),'J2i')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2i;
	
	@FindBy(xpath ="//label[contains(text(),'J2j')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2j;
	
	@FindBy(xpath ="//label[contains(text(),'J2k')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2k;
	
	@FindBy(xpath ="//label[contains(text(),'J2l')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2l;
	
	@FindBy(xpath ="//label[contains(text(),'J2n')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2n;
	
	@FindBy(xpath ="//label[contains(text(),'J2q')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2q;
	
	@FindBy(xpath ="//label[contains(text(),'J2r')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2r;
	
	@FindBy(xpath ="//label[contains(text(),'J2s')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2s;
	
	@FindBy(xpath ="//label[contains(text(),'J2t')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2t;
	
	@FindBy(xpath ="//label[contains(text(),'J2u')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ2u;
	
	@FindBy(xpath ="//label[contains(text(),'J3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ3;
	
	@FindBy(xpath ="//label[contains(text(),'J5a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ5a;
	
	@FindBy(xpath ="//label[contains(text(),'J5b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ5b;
	
	@FindBy(xpath ="//label[contains(text(),'J6a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ6a;
	
	@FindBy(xpath ="//label[contains(text(),'J6b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ6b;
	
	@FindBy(xpath ="//label[contains(text(),'J6c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ6c;
	
	@FindBy(xpath ="//label[contains(text(),'J7a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iJ7a;
	
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
			else 
				healthConditions.UpdateButton.click();
		}
		catch(Exception e)
		{
			healthConditions.UpdateButton.click();
		}
	}
	
	
	
}
