package util;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import report.ComprehensiveTestReportManager;

/**
 * Utility class to manage test execution metrics and reporting for comprehensive tests.
 * Handles tracking of test results, execution times, and Excel report generation.
 * 
 * This class is designed to be used across multiple test runs and maintains
 * cumulative statistics for summary reporting.
 */
public class TestExecutionTracker {
	
	private int totalTestsExecuted;
	private int totalTestsPassed;
	private int totalTestsFailed;
	private double totalExecutionTime;
	private long currentTestStartTime;
	private boolean reportInitialized;
	
	/**
	 * Constructor - Initializes all counters to zero
	 */
	public TestExecutionTracker() {
		this.totalTestsExecuted = 0;
		this.totalTestsPassed = 0;
		this.totalTestsFailed = 0;
		this.totalExecutionTime = 0.0;
		this.currentTestStartTime = 0;
		this.reportInitialized = false;
	}
	
	/**
	 * Initialize the Excel report with column headers from the data source.
	 * Should be called once before any test execution.
	 * 
	 * @param dataFilePath - Path to the Master_Data.xlsx file
	 * @throws IOException if report initialization fails
	 */
	public void initializeReport(String dataFilePath) throws IOException {
		if (!reportInitialized) {
			String[] allColumns = TestData.getAllColumnHeaders(dataFilePath);
			ComprehensiveTestReportManager.initializeReport(allColumns);
			reportInitialized = true;
			System.out.println("✓ Test Execution Tracker: Report initialized with " + allColumns.length + " columns");
		} else {
			System.out.println("⚠ Test Execution Tracker: Report already initialized, skipping...");
		}
	}
	
	/**
	 * Start the timer for a test execution.
	 * Call this at the beginning of each test method.
	 */
	public void startTestTimer() {
		currentTestStartTime = System.currentTimeMillis();
	}
	
	/**
	 * Calculate the execution time for the current test.
	 * 
	 * @return Execution time in seconds
	 */
	public double calculateExecutionTime() {
		long endTime = System.currentTimeMillis();
		return (endTime - currentTestStartTime) / 1000.0;
	}
	
	/**
	 * Record a test result and update the Excel report.
	 * Automatically tracks pass/fail counts and execution times.
	 *
	 * @param testData - Map containing all field values from the test
	 * @param validationsPassed - Number of validations that passed
	 * @param validationsFailed - Number of validations that failed
	 * @param executionTimeSeconds - Execution time in seconds
	 * @throws IOException if writing to report fails
	 */
	public void recordTestResult(
			Map<String, String> testData,
			int validationsPassed,
			int validationsFailed,
			double executionTimeSeconds) throws IOException {
		// Call overloaded method with empty failed columns set
		recordTestResult(testData, validationsPassed, validationsFailed, executionTimeSeconds, new HashSet<>());
	}

	/**
	 * Record a test result and update the Excel report with failed column highlighting.
	 * Automatically tracks pass/fail counts and execution times.
	 *
	 * @param testData - Map containing all field values from the test
	 * @param validationsPassed - Number of validations that passed
	 * @param validationsFailed - Number of validations that failed
	 * @param executionTimeSeconds - Execution time in seconds
	 * @param failedOutputColumns - Set of output column names that failed validation (for red highlighting)
	 * @throws IOException if writing to report fails
	 */
	public void recordTestResult(
			Map<String, String> testData,
			int validationsPassed,
			int validationsFailed,
			double executionTimeSeconds,
			Set<String> failedOutputColumns) throws IOException {
		// Call overloaded method with empty output validation map
		recordTestResult(testData, validationsPassed, validationsFailed, executionTimeSeconds, 
		                 failedOutputColumns, new java.util.HashMap<>());
	}
	
	/**
	 * Record a test result and update the Excel report with output validation map.
	 * Automatically tracks pass/fail counts and execution times.
	 *
	 * @param testData - Map containing all field values from the test
	 * @param validationsPassed - Number of validations that passed
	 * @param validationsFailed - Number of validations that failed
	 * @param executionTimeSeconds - Execution time in seconds
	 * @param failedOutputColumns - Set of output column names that failed validation (for red highlighting)
	 * @param outputValidationMap - Map of output key -> validation result (1=pass, 0=fail)
	 * @throws IOException if writing to report fails
	 */
	public void recordTestResult(
			Map<String, String> testData,
			int validationsPassed,
			int validationsFailed,
			double executionTimeSeconds,
			Set<String> failedOutputColumns,
			Map<String, Integer> outputValidationMap) throws IOException {

		// Determine test status
		String testStatus = (validationsFailed == 0) ? "PASS" : "FAIL";

		// Update counters
		totalTestsExecuted++;
		if (testStatus.equals("PASS")) {
			totalTestsPassed++;
		} else {
			totalTestsFailed++;
		}
		totalExecutionTime += executionTimeSeconds;

		// Add result to Excel report with output validation map
		ComprehensiveTestReportManager.addTestResult(
			testData,
			testStatus,
			executionTimeSeconds,
			validationsPassed,
			validationsFailed,
			failedOutputColumns,
			outputValidationMap
		);

		// Console output
		System.out.println("✓ Test Execution Tracker: Test #" + totalTestsExecuted + " recorded - " + testStatus +
		                   " (" + validationsPassed + "/" + (validationsPassed + validationsFailed) + ") - " +
		                   String.format("%.2f", executionTimeSeconds) + "s");
	}
	
	/**
	 * Finalize the report by adding a summary row with all cumulative statistics.
	 * Call this after all tests have completed.
	 * 
	 * @throws IOException if writing summary fails
	 */
	public void finalizeReport() throws IOException {
		ComprehensiveTestReportManager.addSummaryRow(
			totalTestsExecuted,
			totalTestsPassed,
			totalTestsFailed,
			totalExecutionTime
		);
		
		System.out.println("✓ Test Execution Tracker: Report finalized with summary statistics");
	}
	
	/**
	 * Get the pass rate as a percentage
	 * @return Pass rate (0-100)
	 */
	public double getPassRate() {
		if (totalTestsExecuted == 0) return 0.0;
		return (totalTestsPassed * 100.0) / totalTestsExecuted;
	}
	
	/**
	 * Get the average execution time per test
	 * @return Average time in seconds
	 */
	public double getAverageExecutionTime() {
		if (totalTestsExecuted == 0) return 0.0;
		return totalExecutionTime / totalTestsExecuted;
	}
	
	/**
	 * Print current statistics to console
	 */
	public void printStatistics() {
		System.out.println("\n╔════════════════════════════════════════════════════╗");
		System.out.println("║  TEST EXECUTION STATISTICS                         ║");
		System.out.println("╠════════════════════════════════════════════════════╣");
		System.out.println("║  Total Tests:    " + String.format("%-30d", totalTestsExecuted) + "║");
		System.out.println("║  Passed:         " + String.format("%-30d", totalTestsPassed) + "║");
		System.out.println("║  Failed:         " + String.format("%-30d", totalTestsFailed) + "║");
		System.out.println("║  Pass Rate:      " + String.format("%-27.2f%%", getPassRate()) + "║");
		System.out.println("║  Total Time:     " + String.format("%-27.2fs", totalExecutionTime) + "║");
		System.out.println("║  Average Time:   " + String.format("%-27.2fs", getAverageExecutionTime()) + "║");
		System.out.println("╚════════════════════════════════════════════════════╝");
	}
	
	/**
	 * Reset all counters (useful for new test suites)
	 */
	public void reset() {
		totalTestsExecuted = 0;
		totalTestsPassed = 0;
		totalTestsFailed = 0;
		totalExecutionTime = 0.0;
		currentTestStartTime = 0;
		reportInitialized = false;
		System.out.println("✓ Test Execution Tracker: Reset complete");
	}
	
	// ==================== Getters ====================
	
	public int getTotalTestsExecuted() {
		return totalTestsExecuted;
	}
	
	public int getTotalTestsPassed() {
		return totalTestsPassed;
	}
	
	public int getTotalTestsFailed() {
		return totalTestsFailed;
	}
	
	public double getTotalExecutionTime() {
		return totalExecutionTime;
	}
	
	public boolean isReportInitialized() {
		return reportInitialized;
	}
}
