package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(xpath ="//label[contains(text(),'M1')]/parent::div/..//input")
	public WebElement iM1;
	
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
	
	@FindBy(xpath ="//label[contains(text(),'N1i')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN1i;
	
	@FindBy(xpath ="//label[contains(text(),'N3aa')]/parent::div/..//input")
	public WebElement iN3aa;
	
	@FindBy(xpath ="//label[contains(text(),'N3ab')]/parent::div/..//input")
	public WebElement iN3ab;
	
	@FindBy(xpath ="//label[contains(text(),'N3ba')]/parent::div/..//input")
	public WebElement iN3ba;
	
	@FindBy(xpath ="//label[contains(text(),'N3bb')]/parent::div/..//input")
	public WebElement iN3bb;
	
	@FindBy(xpath ="//label[contains(text(),'N3ca')]/parent::div/..//input")
	public WebElement iN3ca;
	
	@FindBy(xpath ="//label[contains(text(),'N3cb')]/parent::div/..//input")
	public WebElement iN3cb;
	
	@FindBy(xpath ="//label[contains(text(),'N3da')]/parent::div/..//input")
	public WebElement iN3da;
	
	@FindBy(xpath ="//label[contains(text(),'N3ea')]/parent::div/..//input")
	public WebElement iN3ea;
	
	@FindBy(xpath ="//label[contains(text(),'N3eb')]/parent::div/..//input")
	public WebElement iN3eb;
	
	@FindBy(xpath ="//label[contains(text(),'N3ga')]/parent::div/..//input")
	public WebElement iN3ga;
	
	@FindBy(xpath ="//label[contains(text(),'N3gb')]/parent::div/..//input")
	public WebElement iN3gb;
	
	@FindBy(xpath ="//label[contains(text(),'N3fa')]/parent::div/..//input")
	public WebElement iN3fa;
	
	@FindBy(xpath ="//label[contains(text(),'N3fb')]/parent::div/..//input")
	public WebElement iN3fb;
	
	@FindBy(xpath ="//label[contains(text(),'N3ha')]/parent::div/..//input")
	public WebElement iN3ha;
	
	@FindBy(xpath ="//label[contains(text(),'N3hb')]/parent::div/..//input")
	public WebElement iN3hb;
	
	@FindBy(xpath ="//label[contains(text(),'N3ia')]/parent::div/..//input")
	public WebElement iN3ia;
	
	@FindBy(xpath ="//label[contains(text(),'N3ib')]/parent::div/..//input")
	public WebElement iN3ib;
	
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
	
	@FindBy(xpath ="//label[contains(text(),'N2o')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2o;
	
	@FindBy(xpath ="//label[contains(text(),'N2p')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2p;
	
	@FindBy(xpath ="//label[contains(text(),'N5')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN5;
	
	
	public void enterUnnecessaryButMandatoryFields() {
		OptimizedCommands o = new OptimizedCommands();
		o.sendKeysWithBuffer(this.iM1, "01");
		o.sendKeysWithBuffer(this.iN1i, "0");
		o.sendKeysWithBuffer(this.iN2o, "0");
		o.sendKeysWithBuffer(this.iN2p, "0");
		o.sendKeysWithBuffer(this.iN3ia, "0");
		o.sendKeysWithBuffer(this.iN3ib, "0");
	}
	
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
			{
				new Actions(driver).scrollToElement(SaveAndNextButton).build().perform();
				Thread.sleep(3000);
				medicationsPage.SaveAndNextButton.click();
			}
			else
			{
				new Actions(driver).scrollToElement(UpdateButton).build().perform();
				Thread.sleep(3000);
				medicationsPage.UpdateButton.click();
			}
		}
		catch(Exception e)
		{
			medicationsPage.UpdateButton.click();
		}
	}

}
