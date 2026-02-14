package pagefactory;

import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import base.Base;
import util.OptimizedCommands;

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

	@FindBy(css = "#emc")
	WebElement verificationCodeInput;

	@FindBy(css = "#save")
	WebElement verifyButton;
	
	// Functionality for login to Salesfore UI
	public void loginToSandbox()
	{
		
		usernameInput.sendKeys(Base.prop.get("username").toString());
		passwordInput.sendKeys(Base.prop.get("password").toString());
		loginButton.click();

		// Check if running in Docker environment
		boolean IS_DOCKER_RUNTIME = Boolean.parseBoolean(
			System.getProperty("docker.runtime", System.getenv("IS_DOCKER_RUNTIME"))
		);

		// showPromptToEnterCode();
		
		if (IS_DOCKER_RUNTIME) {
			// Take verification code input from console
			System.out.println("\n====================================================================");
			System.out.println("⚠️  VERIFICATION CODE REQUIRED (DELAY IN INPUT MIGHT CAUSE CODE EXPIRY)");
			System.out.println("======================================================================");
			System.out.println("Please enter the verification code from your email: ");
			
			Scanner scanner = new Scanner(System.in);
			String verificationCode = scanner.nextLine().trim();
			
			System.out.println("✓ Code received: " + verificationCode);
			System.out.println("============================================================\n");
			
			// Input the verification code into Salesforce
			OptimizedCommands command = new OptimizedCommands();
			command.waitTillElementClickable(driver, verificationCodeInput, 15);
			verificationCodeInput.sendKeys(verificationCode);
			verifyButton.click();
			scanner.close();
			try {
			Thread.sleep(5000); // Give time for verification to complete
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return;
		} 
		else {
			// In non-Docker environments, show on-screen prompt for manual code entry
			showPromptToEnterCode();
		}

		try {
			Thread.sleep(120000); // Give time for manual verification to complete
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}	
	
	public void showPromptToEnterCode() {
		// Cast driver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Script to create a <header> element at top center with larger size
        String script = 
            "var newHeader = document.createElement('header');" +
            "newHeader.style.position = 'fixed';" +
            "newHeader.style.top = '0';" +
            "newHeader.style.left = '0';" +
            "newHeader.style.right = '0';" +
            "newHeader.style.backgroundColor = '#fff3cd';" +
            "newHeader.style.zIndex = '10000';" +
            "newHeader.style.padding = '20px';" +
            "newHeader.innerHTML = " +
            "'<h1 style=\"color:red; text-align:center; font-size:48px; margin:0;\">Please manually enter Code</h1>' +" +
            "'<p style=\"text-align:center; font-size:22px; margin:8px 0 0; color:#d39e00; font-weight:bold;\">You have 2 minutes</p>';"+
            "document.body.insertBefore(newHeader, document.body.firstChild);";

        js.executeScript(script);
    }
	
}
