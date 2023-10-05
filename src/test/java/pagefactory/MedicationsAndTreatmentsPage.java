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
	
	@FindBy(xpath ="//label[contains(text(),'N2k')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2k;
	
	@FindBy(xpath ="//label[contains(text(),'N2m')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iN2m;
	
	
	
	
	@FindBy(xpath="//span[contains(text(),'Medication')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save']")
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
