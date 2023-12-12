package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class MedicationsAndTreatmentsPage {
	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public MedicationsAndTreatmentsPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//label[contains(text(),'M2')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iM2;
	
	@FindBy(xpath ="//label[contains(text(),'N1a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1a;
	
	@FindBy(xpath ="//label[contains(text(),'N1b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1b;
	
	@FindBy(xpath ="//label[contains(text(),'N1c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1c;
	
	@FindBy(xpath ="//label[contains(text(),'N1d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1d;
	
	@FindBy(xpath ="//label[contains(text(),'N1e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1e;
	
	@FindBy(xpath ="//label[contains(text(),'N1f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1f;
	
	@FindBy(xpath ="//label[contains(text(),'N1g')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1g;
	
	@FindBy(xpath ="//label[contains(text(),'N1h')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1h;
	
	@FindBy(xpath ="//label[contains(text(),'N3ea')]/parent::div/..//input")
	public WebElement iN3ea;
	
	@FindBy(xpath ="//label[contains(text(),'N3eb')]/parent::div/..//input")
	public WebElement iN3eb;
	
	@FindBy(xpath ="//label[contains(text(),'N3gb')]/parent::div/..//input")
	public WebElement iN3gb;
	
	@FindBy(xpath ="//label[contains(text(),'N3fb')]/parent::div/..//input")
	public WebElement iN3fb;
	
	@FindBy(xpath ="//label[contains(text(),'N4a')]/parent::div/..//input")
	public WebElement iN4a;
	
	@FindBy(xpath ="//label[contains(text(),'N4b')]/parent::div/..//input")
	public WebElement iN4b;
	
	@FindBy(xpath ="//label[contains(text(),'N4c')]/parent::div/..//input")
	public WebElement iN4c;
	
	@FindBy(xpath ="//label[contains(text(),'N2a')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2a;
	
	@FindBy(xpath ="//label[contains(text(),'N2b')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2b;
	
	@FindBy(xpath ="//label[contains(text(),'N2c')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2c;
	
	@FindBy(xpath ="//label[contains(text(),'N2d')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2d;
	
	@FindBy(xpath ="//label[contains(text(),'N2e')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2e;
	
	@FindBy(xpath ="//label[contains(text(),'N2f')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2f;
	
	@FindBy(xpath ="//label[contains(text(),'N2g')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2g;
	
	@FindBy(xpath ="//label[contains(text(),'N2h')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2h;
	
	@FindBy(xpath ="//label[contains(text(),'N2i')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2i;
	
	@FindBy(xpath ="//label[contains(text(),'N2j')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2j;
	
	@FindBy(xpath ="//label[contains(text(),'N2k')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2k;
	
	@FindBy(xpath ="//label[contains(text(),'N2l')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2l;
	
	@FindBy(xpath ="//label[contains(text(),'N2m')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2m;
	
	@FindBy(xpath ="//label[contains(text(),'N2n')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2n;
	
	
	
	
	@FindBy(xpath="//span[contains(text(),'Medication')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save&Next']")
	public WebElement SaveAndNextButton;
	
	@FindBy(xpath="//span[contains(text(),'Medication')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;
	
	@FindBy(xpath="//span[contains(text(),'Medication')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;
	
	
	public void clickEditButtonIfDisplayed(MedicationsAndTreatmentsPage medicationsPage) 
	{
		try 
		{
			if(medicationsPage.EditButton.isDisplayed())
				medicationsPage.EditButton.click();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void saveOrUpdateAfterEnteringRequiredFields(MedicationsAndTreatmentsPage medicationsPage)
	{
		try 
		{
			if(medicationsPage.SaveAndNextButton.isDisplayed())
				medicationsPage.SaveAndNextButton.click();
			else
				medicationsPage.UpdateButton.click();
		}
		catch(Exception e)
		{
			medicationsPage.UpdateButton.click();
		}
	}

}
