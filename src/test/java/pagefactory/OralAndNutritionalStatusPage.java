package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class OralAndNutritionalStatusPage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public OralAndNutritionalStatusPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'K1a')]/../..//input")
	public WebElement iK1a;
	
	@FindBy(xpath ="//label[contains(text(),'K1b')]/../..//input")
	public WebElement iK1b;
	
	@FindBy(xpath ="//label[contains(text(),'K2a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iK2a;
	
	@FindBy(xpath ="//label[contains(text(),'K2b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iK2b;
	
	@FindBy(xpath ="//label[contains(text(),'K2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iK2c;
	
	@FindBy(xpath ="//label[contains(text(),'K3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iK3;
	
	@FindBy(xpath ="//label[contains(text(),'L1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iL1;
	
	@FindBy(xpath ="//label[contains(text(),'L2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iL2;
	
	@FindBy(xpath ="//label[contains(text(),'L3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iL3;
	
	@FindBy(xpath ="//label[contains(text(),'L4')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iL4;
	
	@FindBy(xpath ="//label[contains(text(),'L5')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iL5;
	
	@FindBy(xpath ="//label[contains(text(),'L7')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iL7;
	
	
	@FindBy(xpath="//span[contains(text(),'ORAL AND NUTRITIONAL STATUS')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'ORAL AND NUTRITIONAL STATUS')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'ORAL AND NUTRITIONAL STATUS')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	
	public void clickEditButtonIfDisplayed(OralAndNutritionalStatusPage oralAndNutritionalStatusPage) 
	{
		try 
		{
			if(oralAndNutritionalStatusPage.EditButton.isDisplayed())
				oralAndNutritionalStatusPage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveOrUpdateAfterEnteringRequiredFields(OralAndNutritionalStatusPage oralAndNutritionalStatusPage)
	{
		try 
		{
			if(oralAndNutritionalStatusPage.SaveAndNextButton.isDisplayed())
				oralAndNutritionalStatusPage.SaveAndNextButton.click();
			else
				oralAndNutritionalStatusPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			oralAndNutritionalStatusPage.UpdateButton.click();
		}
	}

}
