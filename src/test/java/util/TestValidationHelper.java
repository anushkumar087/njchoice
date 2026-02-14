package util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebElement;

/**
 * Utility class for validating test outputs and comparing expected vs actual
 * values. Provides reusable validation logic for comprehensive test scenarios.
 */
public class TestValidationHelper {

	// Thread-local storage for tracking failed output columns in current test
	private static ThreadLocal<Set<String>> failedOutputColumns = ThreadLocal.withInitial(HashSet::new);

	/**
	 * Validate a single output and return validation result
	 *
	 * @param outputElement - WebElement containing the actual output value
	 * @param outputKey     - The key to lookup expected value in dataMap
	 * @param dataMap       - Map containing all expected values from test data
	 * @param count         - Validation sequence number (for logging)
	 * @return 1 if validation passed, 0 if failed
	 */
	public static int validateOutput(WebElement outputElement, String outputKey, Map<String, String> dataMap,
			int count) {
		try {
			String expectedValue = dataMap.get(outputKey);
			if(expectedValue.contains(":"))
				expectedValue = expectedValue.split(":")[0].trim();

			// Skip if no expected value in data
			if (expectedValue == null || expectedValue.trim().isEmpty()) {
				System.out.println(count + ". " + outputKey + ": SKIPPED (No expected value in data)");
				return 1; // Consider as passed if no expected value
			}

			// Get actual output from UI
			String actualOutput = outputElement.getText().trim();

			// Try to extract value after colon (e.g., "Label: Value")
			try {
				actualOutput = actualOutput.split(": ")[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				// If no colon found and expected is NA, consider it passed
				if (expectedValue.startsWith("NA")) {
					System.out.println(count + ". " + outputKey + ": PASSED (NA)");
					return 1;
				}
				actualOutput = "NA";
			}

			// Compare expected vs actual
			boolean matches = expectedValue.startsWith(actualOutput)
					|| (actualOutput.equals("NA") && expectedValue.startsWith("NA"));

			if (matches) {
				System.out.println(count + ". " + outputKey + ": PASSED [Expected: " + expectedValue + ", Actual: "
						+ actualOutput + "]");
				return 1;
			} else {
				System.out.println(count + ". " + outputKey + ": FAILED [Expected: " + expectedValue + ", Actual: "
						+ actualOutput + "]");
				// Track this failed output column
				failedOutputColumns.get().add(outputKey);
				return 0;
			}

		} catch (Exception e) {
			System.out.println(count + ". " + outputKey + ": ERROR - " + e.getMessage());
			// Track as failed on error
			failedOutputColumns.get().add(outputKey);
			return 0;
		}
	}

	/**
	 * Get the set of failed output column names for the current test.
	 * 
	 * @return Set of failed output column names
	 */
	public static Set<String> getFailedOutputColumns() {
		return new HashSet<>(failedOutputColumns.get());
	}

	/**
	 * Clear the failed output columns tracking for a new test. Should be called at
	 * the start of each test.
	 */
	public static void clearFailedOutputColumns() {
		failedOutputColumns.get().clear();
	}

	/**
	 * Reset thread-local storage (call after test completes).
	 */
	public static void resetValidationTracking() {
		failedOutputColumns.remove();
	}

	/**
	 * Print validation summary with formatted output
	 * 
	 * @param totalValidations - Total number of validations performed
	 * @param passedCount      - Number of validations that passed
	 * @param failedCount      - Number of validations that failed
	 */
	public static void printValidationSummary(int totalValidations, int passedCount, int failedCount) {
		System.out.println("\n╔════════════════════════════════════════════════════╗");
		System.out.println("║  VALIDATION SUMMARY                                ║");
		System.out.println("╠════════════════════════════════════════════════════╣");
		System.out.println("║  Total Validations: " + String.format("%-28d", totalValidations) + "║");
		System.out.println("║  Passed:            " + String.format("%-28d", passedCount) + "║");
		System.out.println("║  Failed:            " + String.format("%-28d", failedCount) + "║");
		System.out.println(
				"║  Pass Rate:         " + String.format("%-25.2f%%", (passedCount * 100.0 / totalValidations)) + "║");
		System.out.println("╚════════════════════════════════════════════════════╝");
	}

	/**
	 * Send keys to a WebElement only if data exists for that field
	 * 
	 * @param element  - WebElement to fill
	 * @param fieldKey - The key to lookup value in dataMap
	 * @param dataMap  - Map containing all field values from test data
	 * @param command  - OptimizedCommands instance for sendKeysWithBuffer
	 */
	public static void sendKeysIfDataExists(WebElement element, String fieldKey, Map<String, String> dataMap,
			OptimizedCommands command) {
		String value = dataMap.get(fieldKey);
		//Only exception fpr P3
		if(fieldKey.equals("iP3"))
		{
			int numericValue = Integer.parseInt(value);
			if(numericValue/10==0)
				value = "0"+value;
		}
			
		if (value != null && !value.trim().isEmpty() && !value.equalsIgnoreCase("NA")) {
			try {
				command.sendKeysWithBuffer(element, value);
			} catch (Exception e) {
				System.out.println("⚠ Warning: Could not fill field " + fieldKey + " - " + e.getMessage());
			}
		}
	}

	/**
	 * Populate a data map from Excel row data
	 * 
	 * @param rowData      - Array of objects from DataProvider
	 * @param dataFilePath - Path to the Excel file for reading column headers
	 * @return Map with column names as keys and row values
	 * @throws Exception if reading column headers fails
	 */
	public static Map<String, String> populateDataMap(Object[] rowData, String dataFilePath) throws Exception {
		// Read all column headers from Excel file
		String[] allColumns = TestData.getAllColumnHeaders(dataFilePath);

		// Create map
		Map<String, String> dataMap = new java.util.HashMap<>();

		// Map column names to values
		for (int i = 0; i < allColumns.length && i < rowData.length; i++) {
			String value = rowData[i] != null ? rowData[i].toString() : "";
			dataMap.put(allColumns[i].split(": ")[0], value);
		}

		return dataMap;
	}
}
