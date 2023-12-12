package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class SocialSupportPage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public SocialSupportPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'P1a1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1a1;
	
	@FindBy(xpath ="//label[contains(text(),'P1b1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1b1;
	
	@FindBy(xpath ="//label[contains(text(),'P2b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP2b;
	
	@FindBy(xpath ="//label[contains(text(),'Q1a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1a;
	
	@FindBy(xpath ="//label[contains(text(),'Q1b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1b;
	
	@FindBy(xpath ="//label[contains(text(),'Q1c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1c;
	
	@FindBy(xpath ="//label[contains(text(),'Q1e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1e;
	
	
	@FindBy(xpath ="//label[contains(text(),'R2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iR2;
	
	@FindBy(xpath ="//label[contains(text(),'P3')]/following-sibling::*//input")
	public WebElement iP3;
	
	
	
	@FindBy(xpath="//span[contains(text(),'SOCIAL SUPPORT')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'SOCIAL SUPPORT')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'SOCIAL SUPPORT')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;

	public void clickEditButtonIfDisplayed(SocialSupportPage socialSupportPage) 
	{
		try 
		{
			if(socialSupportPage.EditButton.isDisplayed())
				socialSupportPage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}

	public void saveOrUpdateAfterEnteringRequiredFields(SocialSupportPage socialSupportPage)
	{
		try 
		{
			if(socialSupportPage.SaveAndNextButton.isDisplayed())
				socialSupportPage.SaveAndNextButton.click();
			else 
				socialSupportPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			socialSupportPage.UpdateButton.click();
		}
	}

}
