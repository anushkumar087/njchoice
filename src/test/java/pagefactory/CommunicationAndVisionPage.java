package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class CommunicationAndVisionPage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public CommunicationAndVisionPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	
	@FindBy(xpath ="//label[contains(text(),'C1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iC1;
	
	@FindBy(xpath ="//label[contains(text(),'C2a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iC2a;
	
	@FindBy(xpath ="//label[contains(text(),'C3a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iC3a;
	
	@FindBy(xpath ="//label[contains(text(),'C3b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iC3b;
	
	@FindBy(xpath ="//label[contains(text(),'C3c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iC3c;
	
	@FindBy(xpath ="//label[contains(text(),'C4')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iC4;
	
	@FindBy(xpath ="//label[contains(text(),'D1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iD1;
	
	@FindBy(xpath ="//label[contains(text(),'D2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iD2;
	
	@FindBy(xpath ="//label[contains(text(),'D3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iD3;
	
	@FindBy(xpath ="//label[contains(text(),'D4')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iD4;
	
	@FindBy(xpath="//span[contains(text(),'COGNITION')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'COGNITION')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'COGNITION')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	
	public void clickEditButtonIfDisplayed(CommunicationAndVisionPage communicationAndVisionPage) 
	{
		try 
		{
			if(communicationAndVisionPage.EditButton.isDisplayed())
				communicationAndVisionPage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveOrUpdateAfterEnteringRequiredFields(CommunicationAndVisionPage communicationAndVisionPage)
	{
		try 
		{
			if(communicationAndVisionPage.SaveAndNextButton.isDisplayed())
				communicationAndVisionPage.SaveAndNextButton.click();
			else
				communicationAndVisionPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			communicationAndVisionPage.UpdateButton.click();
		}
	}

}
