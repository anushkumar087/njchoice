package listener;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import report.ReportManager;
import util.Reusables;

public class ListenerTest implements ITestListener						
{		

	public static boolean reportSheetCreated = false;
	public static long executionTime;

    @Override		
    public synchronized void onStart(ITestContext test) {
    	
    	System.out.println("---- ON START ------");
    	if(!reportSheetCreated)
    	{
    		try {
    			ReportManager.createSheet(); reportSheetCreated = true;
    		} catch (IOException e) {e.printStackTrace();}
    	}
        
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult result) {		
    	executionTime = System.currentTimeMillis() - executionTime;
    	executionTime /= 60000;
    	try {
    		if(Reusables.actualOutputFromUI!=null)
    			ReportManager.makeRowEntry(result.getMethod().getMethodName(),result.getParameters(), result.getStatus(), executionTime);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Reusables.actualOutputFromUI = null;
        		
    }		

    @Override		
    public void onTestSkipped(ITestResult result) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestStart(ITestResult arg0) {					
        // TODO Auto-generated method stub				
    	Reusables.actualOutputFromUI = "UI failure before value validation";
    	executionTime = System.currentTimeMillis(); // initializing timer when starting test
    }		

    @Override		
    public void onTestSuccess(ITestResult result) {		
    	executionTime = System.currentTimeMillis() - executionTime;
    	executionTime /= 60000;
    	
    	try {
    		if(Reusables.actualOutputFromUI!=null)
    			ReportManager.makeRowEntry(result.getMethod().getMethodName(),result.getParameters(), result.getStatus(), executionTime);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	Reusables.actualOutputFromUI = null;
    }		
    
    @Override	
    public void onFinish(ITestContext test) {
    	
      }
}
