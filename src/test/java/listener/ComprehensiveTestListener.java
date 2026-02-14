package listener;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import util.DataFile;
import util.TestExecutionTracker;
import util.TestValidationHelper;

/**
 * TestNG Listener for Comprehensive Tests using the new reporting system.
 * 
 * This listener manages the lifecycle of comprehensive test execution reports
 * using TestExecutionTracker and ComprehensiveTestReportManager.
 * 
 * Key Features:
 * - Creates comprehensive Excel reports with 350+ input columns
 * - Tracks execution metrics (passed/failed counts, execution times)
 * - Generates summary statistics at the end of test suite
 * - Thread-safe for parallel execution
 * 
 * Usage:
 * @Listeners(listener.ComprehensiveTestListener.class)
 * public class YourComprehensiveTest extends Base { ... }
 */
public class ComprehensiveTestListener implements ITestListener {
	
	// Shared tracker instance for the entire test suite
	private static TestExecutionTracker executionTracker = null;
	private static boolean reportInitialized = false;
	
	// Thread-local storage for test-specific data
	private static ThreadLocal<Long> testStartTime = new ThreadLocal<>();
	private static ThreadLocal<Map<String, String>> testDataMap = new ThreadLocal<>();
	
	/**
	 * Called once before any test methods are invoked.
	 * Initializes the comprehensive report with all column headers.
	 */
	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("\n╔════════════════════════════════════════════════════╗");
		System.out.println("║  COMPREHENSIVE TEST SUITE - STARTING               ║");
		System.out.println("╚════════════════════════════════════════════════════╝");
		
		if (!reportInitialized) {
			try {
				executionTracker = new TestExecutionTracker();
				executionTracker.initializeReport(DataFile.master_DataFile);
				reportInitialized = true;
				System.out.println("✓ Comprehensive Report Initialized");
			} catch (IOException e) {
				System.err.println("✗ ERROR: Failed to initialize comprehensive report - " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Called before each test method starts.
	 * Starts the execution timer for the test.
	 */
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("\n► Starting Test: " + result.getMethod().getMethodName());
		if (executionTracker != null) {
			executionTracker.startTestTimer();
			testStartTime.set(System.currentTimeMillis());
		}
	}
	
	/**
	 * Called when a test method succeeds.
	 * Records the test result with PASS status and validation counts.
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		if (executionTracker != null) {
			try {
				// Calculate execution time
				double executionTimeSeconds = executionTracker.calculateExecutionTime();

				// Get test data from result parameters
				Map<String, String> dataMap = extractDataMap(result);
				
				// DEBUG: Check if dataMap is populated
				System.out.println("DEBUG Listener: dataMap size = " + (dataMap != null ? dataMap.size() : "null"));
				
				// Extract output validation map (output key -> 1=pass, 0=fail)
				Map<String, Integer> outputValidationMap = extractOutputValidationMap(result);
				System.out.println("DEBUG Listener: outputValidationMap size = " + (outputValidationMap != null ? outputValidationMap.size() : "null"));

				// Extract validation results from test result
				int[] validationResults = extractValidationResults(result);
				int passedCount = validationResults[0];
				int failedCount = validationResults[1];

				// Get failed output columns for highlighting in report
				Set<String> failedColumns = TestValidationHelper.getFailedOutputColumns();

				// Record the result with output validation map
				executionTracker.recordTestResult(
					dataMap,
					passedCount,
					failedCount,
					executionTimeSeconds,
					failedColumns,
					outputValidationMap
				);

				System.out.println("✓ Test PASSED: " + result.getMethod().getMethodName() +
				                   " (" + passedCount + "/" + (passedCount + failedCount) + ") - " +
				                   String.format("%.2f", executionTimeSeconds) + "s");

			} catch (Exception e) {
				System.err.println("✗ ERROR: Failed to record test success - " + e.getMessage());
				e.printStackTrace();
			} finally {
				// Clean up thread-local storage after recording result
				clearTestData();
				TestValidationHelper.resetValidationTracking();
			}
		}
	}
	
	/**
	 * Called when a test method fails.
	 * Records the test result with FAIL status and validation counts.
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		if (executionTracker != null) {
			try {
				// Calculate execution time
				double executionTimeSeconds = executionTracker.calculateExecutionTime();

				// Get test data from result parameters
				Map<String, String> dataMap = extractDataMap(result);
				
				// Extract output validation map (output key -> 1=pass, 0=fail)
				Map<String, Integer> outputValidationMap = extractOutputValidationMap(result);

				// Extract validation results from test result
				int[] validationResults = extractValidationResults(result);
				int passedCount = validationResults[0];
				int failedCount = validationResults[1];

				// Get failed output columns for highlighting in report
				Set<String> failedColumns = TestValidationHelper.getFailedOutputColumns();

				// Record the result with output validation map
				executionTracker.recordTestResult(
					dataMap,
					passedCount,
					failedCount,
					executionTimeSeconds,
					failedColumns,
					outputValidationMap
				);

				System.out.println("✗ Test FAILED: " + result.getMethod().getMethodName() +
				                   " (" + passedCount + "/" + (passedCount + failedCount) + ") - " +
				                   String.format("%.2f", executionTimeSeconds) + "s");
				System.out.println("   Reason: " + result.getThrowable().getMessage());

			} catch (Exception e) {
				System.err.println("✗ ERROR: Failed to record test failure - " + e.getMessage());
				e.printStackTrace();
			} finally {
				// Clean up thread-local storage after recording result
				clearTestData();
				TestValidationHelper.resetValidationTracking();
			}
		}
	}
	
	/**
	 * Called when a test method is skipped.
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("⊘ Test SKIPPED: " + result.getMethod().getMethodName());
		// Clean up thread-local storage
		clearTestData();
		TestValidationHelper.resetValidationTracking();
	}
	
	/**
	 * Called when a test fails but is within success percentage.
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		onTestFailure(result); // Treat as failure for reporting purposes
	}
	
	/**
	 * Called once after all test methods have been invoked.
	 * Finalizes the report and prints summary statistics.
	 */
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("\n╔════════════════════════════════════════════════════╗");
		System.out.println("║  COMPREHENSIVE TEST SUITE - COMPLETED              ║");
		System.out.println("╚════════════════════════════════════════════════════╝");
		
		if (executionTracker != null && reportInitialized) {
			try {
				executionTracker.finalizeReport();
				executionTracker.printStatistics();
				System.out.println("\n✓ Comprehensive Report Finalized");
				
				// Print report location
				System.out.println("\n📊 Report Location:");
				System.out.println("   " + report.ComprehensiveTestReportManager.getReportFilePath());
				
			} catch (IOException e) {
				System.err.println("✗ ERROR: Failed to finalize comprehensive report - " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		// Clean up
		reportInitialized = false;
		executionTracker = null;
	}
	
	/**
	 * Extract data map from test result parameters.
	 * The test method should store the dataMap in a way accessible to the listener.
	 */
	private Map<String, String> extractDataMap(ITestResult result) {
		// Try to get from thread-local storage first
		Map<String, String> dataMap = testDataMap.get();
		
		if (dataMap == null) {
			// Create empty map if not available
			dataMap = new java.util.HashMap<>();
			
			// Try to extract from test parameters if available
			Object[] parameters = result.getParameters();
			if (parameters != null && parameters.length > 0) {
				try {
					// If first parameter is Object[], try to populate data map
					if (parameters[0] instanceof Object[]) {
						dataMap = TestValidationHelper.populateDataMap(
							(Object[]) parameters[0], 
							DataFile.master_DataFile
						);
					}
				} catch (Exception e) {
					System.err.println("⚠ Warning: Could not extract data map from parameters - " + e.getMessage());
				}
			}
		}
		
		return dataMap;
	}
	
	/**
	 * Extract validation results from test result.
	 * Returns [passedCount, failedCount].
	 * 
	 * The test method can store validation results in ITestResult attributes:
	 * result.setAttribute("validationsPassed", passedCount);
	 * result.setAttribute("validationsFailed", failedCount);
	 */
	private int[] extractValidationResults(ITestResult result) {
		int passedCount = 0;
		int failedCount = 0;
		
		try {
			// Try to get from attributes
			Object passed = result.getAttribute("validationsPassed");
			Object failed = result.getAttribute("validationsFailed");
			
			if (passed != null && passed instanceof Integer) {
				passedCount = (Integer) passed;
			}
			if (failed != null && failed instanceof Integer) {
				failedCount = (Integer) failed;
			}
			
			// If no attributes set, check if test passed/failed
			if (passedCount == 0 && failedCount == 0) {
				if (result.getStatus() == ITestResult.SUCCESS) {
					passedCount = 46; // Assume all 46 validations passed
					failedCount = 0;
				} else if (result.getStatus() == ITestResult.FAILURE) {
					passedCount = 0;
					failedCount = 46; // Assume all 46 validations failed
				}
			}
			
		} catch (Exception e) {
			System.err.println("⚠ Warning: Could not extract validation results - " + e.getMessage());
		}
		
		return new int[]{passedCount, failedCount};
	}
	
	/**
	 * Extract output validation map from test result.
	 * Returns Map<String, Integer> where key is output name and value is 1 (pass) or 0 (fail).
	 * 
	 * The test method stores this via:
	 * result.setAttribute("outputValidationMap", outputValidationMap);
	 */
	@SuppressWarnings("unchecked")
	private Map<String, Integer> extractOutputValidationMap(ITestResult result) {
		try {
			Object mapObj = result.getAttribute("outputValidationMap");
			if (mapObj != null && mapObj instanceof Map) {
				return (Map<String, Integer>) mapObj;
			}
		} catch (Exception e) {
			System.err.println("⚠ Warning: Could not extract output validation map - " + e.getMessage());
		}
		
		// Return empty map if not available
		return new java.util.HashMap<>();
	}
	
	/**
	 * Static method to store test data map in thread-local storage.
	 * Call this from your test method after populating the data map.
	 * 
	 * @param dataMap The data map containing all test field values
	 */
	public static void setTestDataMap(Map<String, String> dataMap) {
		testDataMap.set(dataMap);
	}
	
	/**
	 * Static method to clear thread-local storage.
	 * Should be called at the end of each test method.
	 */
	public static void clearTestData() {
		testDataMap.remove();
		testStartTime.remove();
	}
}
