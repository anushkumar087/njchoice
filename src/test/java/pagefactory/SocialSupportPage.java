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
	
	@FindBy(xpath ="//label[contains(text(),'P1a2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1a2;
	
	@FindBy(xpath ="//label[contains(text(),'P1b1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1b1;
	
	@FindBy(xpath ="//label[contains(text(),'P1b2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1b2;
	
	@FindBy(xpath ="//label[contains(text(),'P1c1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1c1;
	
	@FindBy(xpath ="//label[contains(text(),'P1c2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1c2;
	
	@FindBy(xpath ="//label[contains(text(),'P1d1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1d1;
	
	@FindBy(xpath ="//label[contains(text(),'P1d2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP1d2;
	
	@FindBy(xpath ="//label[contains(text(),'P2a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP2a;
	
	@FindBy(xpath ="//label[contains(text(),'P2b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP2b;
	
	@FindBy(xpath ="//label[contains(text(),'P2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP2c;
	
	@FindBy(xpath ="//label[contains(text(),'P4')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iP4;
	
	@FindBy(xpath ="//label[contains(text(),'Q1a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1a;
	
	@FindBy(xpath ="//label[contains(text(),'Q1b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1b;
	
	@FindBy(xpath ="//label[contains(text(),'Q1c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1c;
	
	@FindBy(xpath ="//label[contains(text(),'Q1d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1d;
	
	@FindBy(xpath ="//label[contains(text(),'Q1e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ1e;
	
	@FindBy(xpath ="//label[contains(text(),'Q2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ2;
	
	@FindBy(xpath ="//label[contains(text(),'Q3a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ3a;
	
	@FindBy(xpath ="//label[contains(text(),'Q3b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ3b;
	
	@FindBy(xpath ="//label[contains(text(),'Q3c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ3c;
	
	@FindBy(xpath ="//label[contains(text(),'Q4')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iQ4;
	
	@FindBy(xpath ="//label[contains(text(),'R1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iR1;
	
	@FindBy(xpath ="//label[contains(text(),'R2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iR2;
	
	@FindBy(xpath ="//label[contains(text(),'R3a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iR3a;
	
	@FindBy(xpath ="//label[contains(text(),'R3b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iR3b;
	
	@FindBy(xpath ="//label[contains(text(),'P3')]/following-sibling::*//input")
	public WebElement iP3;
	
	
	public void enterUnnecessaryButMandatoryFields() {
		OptimizedCommands o = new OptimizedCommands();
		o.sendKeysWithBuffer(this.iP2c, "0");
		o.sendKeysWithBuffer(this.iP4, "0");
		o.sendKeysWithBuffer(this.iR1, "1");
		o.sendKeysWithBuffer(this.iR2, "1");
		o.sendKeysWithBuffer(this.iR3a, "1");
		o.sendKeysWithBuffer(this.iR3b, "1");
	}
	
	
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
