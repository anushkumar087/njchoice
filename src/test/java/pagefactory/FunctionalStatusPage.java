package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class FunctionalStatusPage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public FunctionalStatusPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'G1aa')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1aa;
	
	@FindBy(xpath ="//label[contains(text(),'G1ab')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1ab;
	
	@FindBy(xpath ="//label[contains(text(),'G1bb')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1bb;
	
	@FindBy(xpath ="//label[contains(text(),'G1cb')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1cb;
	
	@FindBy(xpath ="//label[contains(text(),'G1da')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1da;
	
	@FindBy(xpath ="//label[contains(text(),'G1db')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1db;
	
	@FindBy(xpath ="//label[contains(text(),'G1ea')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1ea;
	
	@FindBy(xpath ="//label[contains(text(),'G1gb')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1gb;
	
	@FindBy(xpath ="//label[contains(text(),'G1hb')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1hb;
	
	@FindBy(xpath ="//label[contains(text(),'G1fa')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1fa;
	
	@FindBy(xpath ="//label[contains(text(),'G1fb')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG1fb;
	
	@FindBy(xpath ="//label[contains(text(),'G2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2a;
	
	@FindBy(xpath ="//label[contains(text(),'G2b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2b;
	
	@FindBy(xpath ="//label[contains(text(),'G2d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2c;
	
	@FindBy(xpath ="//label[contains(text(),'G2e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2d;
	
	@FindBy(xpath ="//label[contains(text(),'G2j')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2e;
	
	@FindBy(xpath ="//label[contains(text(),'G2k')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2f;
	
	@FindBy(xpath ="//label[contains(text(),'G2f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2g;
	
	@FindBy(xpath ="//label[contains(text(),'G2g')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2h;
	
	@FindBy(xpath ="//label[contains(text(),'G2h')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2i;
	
	@FindBy(xpath ="//label[contains(text(),'G2a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG2j;
	
	@FindBy(xpath ="//label[contains(text(),'G3a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG3a;
	
	@FindBy(xpath ="//label[contains(text(),'G4a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG4a;
	
	@FindBy(xpath ="//label[contains(text(),'G4b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG4b;
	
	@FindBy(xpath ="//label[contains(text(),'G5a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG5a;
	
	@FindBy(xpath ="//label[contains(text(),'G5b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG5b;
	
	@FindBy(xpath ="//label[contains(text(),'G6')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG6;
	
	@FindBy(xpath ="//label[contains(text(),'G7')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iG7;
	
	@FindBy(xpath ="//label[contains(text(),'H1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iH1;
	
	@FindBy(xpath ="//label[contains(text(),'H2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iH2;
	
	@FindBy(xpath ="//label[contains(text(),'H3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iH3;
	
	@FindBy(xpath="//span[contains(text(),'CONTINENCE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'CONTINENCE')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'CONTINENCE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	
	public void clickEditButtonIfDisplayed(FunctionalStatusPage functionalStatusPage) 
	{
		try 
		{
			if(functionalStatusPage.EditButton.isDisplayed())
				functionalStatusPage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveOrUpdateAfterEnteringRequiredFields(FunctionalStatusPage functionalStatusPage)
	{
		try 
		{
			if(functionalStatusPage.SaveAndNextButton.isDisplayed())
				functionalStatusPage.SaveAndNextButton.click();
			else
				functionalStatusPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			functionalStatusPage.UpdateButton.click();
		}
	}

}
