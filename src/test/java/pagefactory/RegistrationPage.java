package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage {
	
	private WebDriver driver;
	
	public RegistrationPage(WebDriver driver)
	{
		this.driver = driver;
		
	}
	

	@FindBy(xpath ="//a[text()='Remind Me Later']")
	public WebElement remindMeLaterLink;

}
