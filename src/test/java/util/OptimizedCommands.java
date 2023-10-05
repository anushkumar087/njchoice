package util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pagefactory.NJChoicePage;
import pagefactory.ReferralPage;

public class OptimizedCommands {
	
	private static final int pollingTimeInSecond = 3;
	
	public void waitForElement(WebDriver driver, WebElement element, long sec)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)                            
				.withTimeout(Duration.ofSeconds(sec))          
				.pollingEvery(Duration.ofSeconds(pollingTimeInSecond))          
				.ignoring(NoSuchElementException.class);    

				  WebElement aboutMe= wait.until(new Function<WebDriver, WebElement>() {       
				public WebElement apply(WebDriver driver) { 
				return element;     
				 }  
				});  
	}
	
	public void waitTillElementDisplayed(WebDriver driver, WebElement element, long sec)
	{
		int iter = (int)sec;
		boolean elementDisplayed = false;
		for(int i=0; i<iter; i++)
		{
			try {
				Thread.sleep(1000);
				element.isDisplayed();
				System.out.println("i = "+i);
				break;
			}
			catch(Exception e) {
				if((i==iter-1)&&(!elementDisplayed))
				{
					Assert.assertFalse(true);
				}
			}
		}
	}
	
	public void sendKeysWithBuffer(WebElement element,String keys)
	{
		try { element.click();}catch(Exception e) {}
		element.clear();
		element.sendKeys(keys);
		try{Thread.sleep(2000);}catch(Exception e) {}
	}
	
	public boolean waitForAlertAndAccept(WebDriver driver,String alertExpectedSubText,int secs)
	{
		int i=0;
		while(i++<secs)
		{
	        try
	        {
	        	Thread.sleep(1000);
	            Alert alert = driver.switchTo().alert();
	            
	            if(alert.getText().contains(alertExpectedSubText))
	            {
	            	alert.accept();
	            	return true;
	            }
	        }
	        catch(NoAlertPresentException e) { continue; }
	        catch(InterruptedException e) {}
		}
		return false;
	}
	
	public boolean waitForAnyAlertAndAccept(WebDriver driver,int secs)
	{
		int i=0;
		while(i++<secs)
		{
	        try
	        {
	        	Thread.sleep(1000);
	            Alert alert = driver.switchTo().alert();
	            alert.accept();
	            return true;
	        }
	        catch(NoAlertPresentException e) { continue; }
	        catch(InterruptedException e) {}
		}
		return false;
	}
	
	public String getAlertText(WebDriver driver,int secs)
	{
		int i=0;
		while(i++<secs)
		{
	        try
	        {
	        	Thread.sleep(1000);
	            Alert alert = driver.switchTo().alert();
	            return alert.getText();
	        }
	        catch(NoAlertPresentException e) { continue; }
	        catch(InterruptedException e) {}
		}
		return null;
	}
	
	public void waitAfterNavigatingToCapsAndAlgoTab()
	{
		// Wait for 8 secs
		try{Thread.sleep(8000);}catch(Exception e) {}
	}
	
	public void waitAfterClickingRefreshOnCapsAndAlgoTab()
	{
		// Wait for 3 secs
		try{Thread.sleep(5000);}catch(Exception e) {}
	}

	public void selectPresentDate(WebElement dateField, String dateValue, WebDriver driver) {
		NJChoicePage njChoicePage = PageFactory.initElements(driver, NJChoicePage.class);		
		int currentPositionOfNJChoiceSections = njChoicePage.njChoiceAssessmentSections.getLocation().getY();
		int pageTopPosY = currentPositionOfNJChoiceSections - ReferralPage.startingReferencePositionOfNJChoiceSections;
		try{Thread.sleep(3000);}catch(Exception e) {}
		int width = dateField.getSize().getWidth();
		int dateXpos = dateField.getLocation().getX();
		int dateYpos = dateField.getLocation().getY();
		Actions actions = new Actions(driver);
		
		System.out.println("dateXpos: "+dateXpos+", dateYpos: "+(dateYpos-pageTopPosY)+", pageTopPosY: "+(pageTopPosY));
		
//		actions.moveByOffset(dateXpos, dateYpos-pageTopPosY).build().perform();
		actions.moveToElement(dateField).build().perform();
		actions.moveByOffset(width/2, 0).build().perform();
		actions.moveByOffset(-5, 0).build().perform();
		actions.click().build().perform();
		try{Thread.sleep(2000);}catch(Exception e) {}
		actions.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
		
	}
	

}
