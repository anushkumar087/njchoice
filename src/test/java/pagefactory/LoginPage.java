package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import base.Base;

public class LoginPage {

	private WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	// input textbox username
	@FindAll({
		@FindBy(name="username"),
		@FindBy(id="username")
	})
	WebElement usernameInput;
	
	
	// input textbox password
	@FindAll({
		@FindBy(name="pw"),
		@FindBy(id="password")
	})
	WebElement passwordInput;
	
	// button Log In to Sandbox
	@FindAll({
		@FindBy(name="Login"),
		@FindBy(id="Login")
	})
	WebElement loginButton;
	
	// Functionality for login to Salesfore UI
	public void loginToSandbox()
	{
		usernameInput.sendKeys(Base.prop.get("username").toString());
		passwordInput.sendKeys(Base.prop.get("password").toString());
		loginButton.click();
		
	}
	
	
	
}
