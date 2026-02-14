package pagefactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.Base;
import util.OptimizedCommands;

public class CompletionPage {

	private OptimizedCommands command;
	private WebDriver driver;

	public CompletionPage(WebDriver driver) {
		this.driver = driver;
		command = new OptimizedCommands();
	}

	@FindBy(xpath = "//label[contains(text(),'S1')]/following-sibling::*//input")
	public WebElement iS1;

	@FindBy(xpath = "//label[contains(text(),'COMPLETION DATE')]/following-sibling::input")
	public WebElement completionDate;

	@FindBy(xpath = "//label[contains(text(),'S3')]/ancestor::div[1]/following-sibling::*//input")
	public WebElement iS3;

	@FindBy(xpath = "//span[contains(text(),'ASSESSMENT INFORMATION')]//ancestor::div[@part='body']//button[@type='submit' and text()='Save & Complete']")
	public WebElement SaveAndCompleteButton;

	@FindBy(xpath = "//span[contains(text(),'ASSESSMENT INFORMATION')]//ancestor::div[@part='body']//button[@type='button' and text()='Edit']")
	public WebElement EditButton;

	@FindBy(xpath = "//span[contains(text(),'ASSESSMENT INFORMATION')]//ancestor::div[@part='body']//button[@type='submit' and text()='Update']")
	public WebElement UpdateButton;

	public void clickEditButtonIfDisplayed(CompletionPage completionPage) {
		try {
			if (completionPage.EditButton.isDisplayed())
				completionPage.EditButton.click();
		} catch (Exception e) {

		}
	}

	public void saveOrUpdateAfterEnteringRequiredFields(CompletionPage completionPage) {
		try {
			if (completionPage.SaveAndCompleteButton.isDisplayed())
				completionPage.SaveAndCompleteButton.click();
			else
				completionPage.UpdateButton.click();
		} catch (Exception e) {
			completionPage.UpdateButton.click();
		}
	}

	/**
	 * Fill Section S with default values: - S1: Name from config.properties -
	 * Completion Date: Current date - S3: "1"
	 */
	public void fillCompletionSectionWithDefaults() {
		// Get name from config.properties
		String name = Base.prop.getProperty("name", "Automation Test User");
		command.sendKeysWithBuffer(iS1, name);

		// Set current date
		String currentDate = getCurrentDate();
		command.selectPresentDate(completionDate, currentDate, driver);

		// Set S3 to "1"
		command.sendKeysWithBuffer(iS3, "1");
	}

	/**
	 * Get current date in MM/dd/yyyy format
	 */
	private String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		return formatter.format(date);
	}

}
