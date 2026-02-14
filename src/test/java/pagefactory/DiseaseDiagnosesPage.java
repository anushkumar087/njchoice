package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class DiseaseDiagnosesPage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public DiseaseDiagnosesPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'I1a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1a;
	
	@FindBy(xpath ="//label[contains(text(),'I1b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1b;
	
	@FindBy(xpath ="//label[contains(text(),'I1c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1c;
	
	@FindBy(xpath ="//label[contains(text(),'I1d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1d;
	
	@FindBy(xpath ="//label[contains(text(),'I1e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1e;
	
	@FindBy(xpath ="//label[contains(text(),'I1f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1f;
	
	@FindBy(xpath ="//label[contains(text(),'I1g')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1g;
	
	@FindBy(xpath ="//label[contains(text(),'I1h')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1h;
	
	@FindBy(xpath ="//label[contains(text(),'I1i')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1i;
	
	@FindBy(xpath ="//label[contains(text(),'I1j')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1j;
	
	@FindBy(xpath ="//label[contains(text(),'I1k')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1k;
	
	@FindBy(xpath ="//label[contains(text(),'I1l')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1l;
	
	@FindBy(xpath ="//label[contains(text(),'I1m')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1m;
	
	@FindBy(xpath ="//label[contains(text(),'I1n')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1n;
	
	@FindBy(xpath ="//label[contains(text(),'I1o')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1o;
	
	@FindBy(xpath ="//label[contains(text(),'I1p')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1p;
	
	@FindBy(xpath ="//label[contains(text(),'I1q')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1q;
	
	@FindBy(xpath ="//label[contains(text(),'I1r')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1r;
	
	@FindBy(xpath ="//label[contains(text(),'I1s')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1s;
	
	@FindBy(xpath ="//label[contains(text(),'I1s1')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1s1;
	
	@FindBy(xpath ="//label[contains(text(),'I1t')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1t;
	
	@FindBy(xpath ="//label[contains(text(),'I1u')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1u;
	
	@FindBy(xpath ="//label[contains(text(),'I1v')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1v;
	
	@FindBy(xpath ="//label[contains(text(),'I1w')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iI1w;
	
	@FindBy(xpath ="//label[contains(text(),'I2aa')]/parent::*/..//input")
	public WebElement iI2aa;
	
	@FindBy(xpath ="//label[contains(text(),'I2ab')]/parent::*/..//input")
	public WebElement iI2ab;
	
	@FindBy(xpath="//span[contains(text(),'DISEASE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'DISEASE')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'DISEASE')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	public void enterUnnecessaryButMandatoryFields() {
		OptimizedCommands o = new OptimizedCommands();
		o.sendKeysWithBuffer(this.iI1v, "0");
		o.sendKeysWithBuffer(this.iI1w, "0");
		o.sendKeysWithBuffer(this.iI2aa, "0");
		o.sendKeysWithBuffer(this.iI2ab, "0");
	}
	
	public void clickEditButtonIfDisplayed(DiseaseDiagnosesPage diseaseDiagnosesPage) 
	{
		try 
		{
			if(diseaseDiagnosesPage.EditButton.isDisplayed())
				diseaseDiagnosesPage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveOrUpdateAfterEnteringRequiredFields(DiseaseDiagnosesPage diseaseDiagnosesPage)
	{
		try 
		{
			if(diseaseDiagnosesPage.SaveAndNextButton.isDisplayed())
				diseaseDiagnosesPage.SaveAndNextButton.click();
			else
				diseaseDiagnosesPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			diseaseDiagnosesPage.UpdateButton.click();
		}
	}

}
