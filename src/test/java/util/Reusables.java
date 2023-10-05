package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
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
	
	
}
