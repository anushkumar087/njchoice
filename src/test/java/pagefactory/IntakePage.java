package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class IntakePage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public IntakePage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'DATE CASE OPENED')]/following-sibling::input")
	public WebElement iB1;
	
	@FindBy(xpath ="//label[contains(text(),'B5a')]/parent::*/..//input")
	public WebElement iB5a;
	
	@FindBy(xpath ="//label[contains(text(),'B7a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB7a;
	
	@FindBy(xpath ="//label[contains(text(),'B10')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB10;
	
	@FindBy(xpath="//span[contains(text(),'INTAKE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'INTAKE')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'INTAKE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;

	public void clickEditButtonIfDisplayed(IntakePage intakePage) 
	{
		try 
		{
			if(intakePage.EditButton.isDisplayed())
				intakePage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}

	public void saveOrUpdateAfterEnteringRequiredFields(IntakePage intakePage)
	{
		try 
		{
			if(intakePage.SaveAndNextButton.isDisplayed())
				intakePage.SaveAndNextButton.click();
			else 
				intakePage.UpdateButton.click();
		}
		catch(Exception e)
		{
			intakePage.UpdateButton.click();
		}
	}

}
