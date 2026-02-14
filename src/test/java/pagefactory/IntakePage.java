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
	
	@FindBy(xpath ="//label[contains(text(),'B2')]/parent::*/..//input")
	public WebElement iB2;

	@FindBy(xpath ="//label[contains(text(),'B3a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB3a;

	@FindBy(xpath ="//label[contains(text(),'B3b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB3b;

	@FindBy(xpath ="//label[contains(text(),'B3c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB3c;

	@FindBy(xpath ="//label[contains(text(),'B3d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB3d;

	@FindBy(xpath ="//label[contains(text(),'B3e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB3e;

	@FindBy(xpath ="//label[contains(text(),'B3f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB3f;

	@FindBy(xpath ="//label[contains(text(),'B4a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB4a;

	@FindBy(xpath ="//label[contains(text(),'B4b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB4b;

	@FindBy(xpath ="//label[contains(text(),'B5')]/parent::*/..//textarea")
	public WebElement iB5;

	@FindBy(xpath ="//label[contains(text(),'B5a')]/parent::*/..//input")
	public WebElement iB5a;

	@FindBy(xpath ="//label[contains(text(),'B6')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB6;

	@FindBy(xpath ="//label[contains(text(),'B7a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB7a;

	@FindBy(xpath ="//label[contains(text(),'B7b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB7b;

	@FindBy(xpath ="//label[contains(text(),'B7c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB7c;

	@FindBy(xpath ="//label[contains(text(),'B8')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB8;

	@FindBy(xpath ="//label[contains(text(),'B9a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB9a;

	@FindBy(xpath ="//label[contains(text(),'B9b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB9b;

	@FindBy(xpath ="//label[contains(text(),'B9c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB9c;

	@FindBy(xpath ="//label[contains(text(),'B9d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB9d;

	@FindBy(xpath ="//label[contains(text(),'B9e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB9e;

	@FindBy(xpath ="//label[contains(text(),'B10')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB10;

	@FindBy(xpath ="//label[contains(text(),'B11')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iB11;
	
	@FindBy(xpath="//span[contains(text(),'INTAKE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'INTAKE')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'INTAKE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	public void enterUnnecessaryButMandatoryFields() {
		OptimizedCommands o = new OptimizedCommands();
		o.sendKeysWithBuffer(this.iB3a, "0");
		o.sendKeysWithBuffer(this.iB3b, "0");
		o.sendKeysWithBuffer(this.iB3c, "0");
		o.sendKeysWithBuffer(this.iB3d, "0");
		o.sendKeysWithBuffer(this.iB3e, "0");
		o.sendKeysWithBuffer(this.iB3f, "0");
		o.sendKeysWithBuffer(this.iB4b, "0");
		o.sendKeysWithBuffer(this.iB5, "0");
		o.sendKeysWithBuffer(this.iB5a, "0");
		o.sendKeysWithBuffer(this.iB11, "0");
	}

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
