package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import report.ReportManager;  

public class Reusables {

	public static String actualOutputFromUI = null;
	@SuppressWarnings("deprecation")
	public static String getNextMonthDate()
	{
		Date date = new Date();
		date.setDate(01);
		if(date.getMonth()==12)
			date.setMonth(1);
		else
			date.setMonth(date.getMonth()+1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
	    String strDate= formatter.format(date);  
		return strDate;
	}

	public static void validateOutput(WebElement outputValue, String output) {
		
		String actualOuput = outputValue.getText().trim();
		boolean outPutActualMatchesExpected = false;
		try
		{
			actualOuput = actualOuput.split(": ")[1];
			Reusables.actualOutputFromUI = actualOuput;
			Assert.assertTrue(output.startsWith(actualOuput));
			outPutActualMatchesExpected = true;
		}
		catch(ArrayIndexOutOfBoundsException ae)
		{
			if(!output.startsWith("NA"))
			{
				Reusables.actualOutputFromUI = "Output value not populated";
			}
			Assert.assertTrue(output.startsWith("NA"));
			outPutActualMatchesExpected = true;
		}
		
		Assert.assertTrue(outPutActualMatchesExpected);
	}
	
	public static void validateOutputSComm(WebElement outputValue, String output) {
		
		String actualOuput = outputValue.getText().trim();
		boolean outPutActualMatchesExpected = false;
		try
		{
			actualOuput = actualOuput.split("Value ")[1];
			Reusables.actualOutputFromUI = actualOuput;
			Assert.assertTrue(output.startsWith(actualOuput));
			outPutActualMatchesExpected = true;
		}
		catch(ArrayIndexOutOfBoundsException ae)
		{
			if(!output.startsWith("NA"))
			{
				Reusables.actualOutputFromUI = "Output value not populated";
			}
			Assert.assertTrue(output.startsWith("NA"));
			outPutActualMatchesExpected = true;
		}
		
		Assert.assertTrue(outPutActualMatchesExpected);
	}
	
	public static void validateOutputBMI(WebElement outputValue, String output) {
		
		String actualOuput = outputValue.getText().trim();
		boolean outPutActualMatchesExpected = false;
		try
		{
			actualOuput = actualOuput.split(": ")[1];
			Reusables.actualOutputFromUI = actualOuput;
			Assert.assertTrue(actualOuput.startsWith(output));
			outPutActualMatchesExpected = true;
		}
		catch(ArrayIndexOutOfBoundsException ae)
		{
			Assert.assertTrue(output.startsWith("NA"));
			outPutActualMatchesExpected = true;
		}
		
		Assert.assertTrue(outPutActualMatchesExpected);
	}
	
	public static String getAge(String dobDateStr, String refDateStr)
	{
		
		dobDateStr = dobDateStr.substring(0, 4)+"-"+dobDateStr.substring(4, 6)+"-"+dobDateStr.substring(6, 8);
		refDateStr = refDateStr.substring(0, 4)+"-"+refDateStr.substring(4, 6)+"-"+refDateStr.substring(6, 8);
		LocalDate dateDOB = LocalDate.parse(dobDateStr);
		LocalDate dateRef = LocalDate.parse(refDateStr);
		dateRef = dateRef.plusDays(3);
		Period period = dateDOB.until(dateRef);
		int yearsBetween = period.getYears();
		
		return Integer.toString(yearsBetween);
	}
	
	
	
	
	public static void setIOMapping(String[] inputs, String output, String testMethodName)
	{
		String[] allValues = new String[inputs.length+1];
		
		for(int i=0 ; i<inputs.length; i++)
		{
			allValues[i] = inputs[i];
		}
		allValues[allValues.length-1] = output;
		
		ReportManager.ioMapping.put(testMethodName, allValues);
	}
	
	public static void enterKey(WebElement element, WebDriver driver,char key)
	{
		Actions actions = new Actions(driver);
		switch (key) {
		case '0':
			actions.keyDown(Keys.NUMPAD0).keyUp(Keys.NUMPAD0).build().perform();
			break;
			
		case '1':
			actions.keyDown(Keys.NUMPAD1).keyUp(Keys.NUMPAD1).build().perform();
			break;
			
		case '2':
			actions.keyDown(Keys.NUMPAD2).keyUp(Keys.NUMPAD2).build().perform();
			break;
			
		case '3':
			actions.keyDown(Keys.NUMPAD3).keyUp(Keys.NUMPAD3).build().perform();
			break;
			
		case '4':
			actions.keyDown(Keys.NUMPAD4).keyUp(Keys.NUMPAD4).build().perform();
			break;
			
		case '5':
			actions.keyDown(Keys.NUMPAD5).keyUp(Keys.NUMPAD5).build().perform();
			break;
			
		case '6':
			actions.keyDown(Keys.NUMPAD6).keyUp(Keys.NUMPAD6).build().perform();
			break;
			
		case '7':
			actions.keyDown(Keys.NUMPAD7).keyUp(Keys.NUMPAD7).build().perform();
			break;
			
		case '8':
			actions.keyDown(Keys.NUMPAD8).keyUp(Keys.NUMPAD8).build().perform();
			break;
			
		case '9':
			actions.keyDown(Keys.NUMPAD9).keyUp(Keys.NUMPAD9).build().perform();
			break;

		default:
			Assert.assertTrue(false, "This value is not applicable for a number input");;
			break;
		}
	}
	
}
