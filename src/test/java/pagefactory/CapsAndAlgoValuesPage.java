package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.OptimizedCommands;

public class CapsAndAlgoValuesPage {

	
	private OptimizedCommands command;
	private WebDriver driver;
	
	public CapsAndAlgoValuesPage(WebDriver driver)
	{
		this.driver = driver;
		command = new OptimizedCommands();
	}
	
	@FindBy(xpath ="//h1[contains(text(),'cCARDIO Value')]/b")
	public WebElement cCARDIOValue;
	
	@FindBy(xpath="//h1[contains(text(),'sDRS Value')]/b")
	public WebElement sDRSValue;
	
	@FindBy(xpath="//h1[contains(text(),'CAdd Value')]/b")
	public WebElement CAddValue;
}
