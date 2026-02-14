package report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Report Manager for Comprehensive All Sections Test.
 * Generates Excel report with all input fields, validation results, and execution metrics.
 * 
 * Report Location: /reports/ComprehensiveTestReport_[timestamp].xlsx
 * Format: All input columns + Status (PASS/FAIL) + Execution Time (seconds)
 */
public class ComprehensiveTestReportManager {
	
	private static int rowIndex = 0;
	private static String currentTime = null;
	private static String reportFilePath = null;
	private static String[] columnHeaders = null;
	
	/**
	 * Initialize the Excel report with headers.
	 * Creates new workbook with all input field columns + Status + Execution Time columns.
	 * 
	 * @param headers - Array of column headers from Master_Data.xlsx
	 * @throws IOException if file creation fails
	 */
	public static void initializeReport(String[] headers) throws IOException {
		rowIndex = 0;
		columnHeaders = headers;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss");
		currentTime = dateFormat.format(new Date());
		
		// Report saved to: /reports/ComprehensiveTestReport_[timestamp].xlsx
		// Use System.getProperty("user.dir") + "/reports/" to ensure correct path
		String reportDirectory = System.getProperty("user.dir") + "/reports";
		reportFilePath = reportDirectory + "/ComprehensiveTestReport_" + currentTime + ".xlsx";
		
		// Create reports directory if it doesn't exist
		File reportsDir = new File(reportDirectory);
		if (!reportsDir.exists()) {
			boolean created = reportsDir.mkdirs();
			if (created) {
				System.out.println("✓ Created reports directory: " + reportDirectory);
			}
		}
		
		// Create new workbook
		Workbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Test Results");
		
		// Create header row
		Row headerRow = sheet.createRow(rowIndex);
		
		// Calculate total columns: all input fields + Status + Execution Time
		int totalColumns = headers.length + 2;
		
		// Style for header cells
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(headerFont);
		
		// Create all input field header cells
		for (int i = 0; i < headers.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(headerStyle);
			sheet.setColumnWidth(i, 4000); // Set reasonable column width
		}
		
		// Add "Status" column header
		Cell statusCell = headerRow.createCell(headers.length);
		statusCell.setCellValue("Status");
		statusCell.setCellStyle(headerStyle);
		sheet.setColumnWidth(headers.length, 3000);
		
		// Add "Execution Time (seconds)" column header
		Cell timeCell = headerRow.createCell(headers.length + 1);
		timeCell.setCellValue("Execution Time (seconds)");
		timeCell.setCellStyle(headerStyle);
		sheet.setColumnWidth(headers.length + 1, 5000);
		
		// Write to file
		OutputStream fileOut = new FileOutputStream(reportFilePath);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		
		System.out.println("╔════════════════════════════════════════════════════════════════╗");
		System.out.println("║  COMPREHENSIVE TEST REPORT INITIALIZED                         ║");
		System.out.println("╠════════════════════════════════════════════════════════════════╣");
		System.out.println("║  Report File: " + String.format("%-47s", reportFilePath.substring(reportFilePath.lastIndexOf("/") + 1)) + "║");
		System.out.println("║  Total Columns: " + String.format("%-45d", totalColumns) + "║");
		System.out.println("╚════════════════════════════════════════════════════════════════╝");
	}
	
	/**
	 * Add a test result row to the Excel report (backward compatible version).
	 *
	 * @param testData - Map containing all field values (key = field name, value = field value)
	 * @param status - Test status: "PASS" or "FAIL"
	 * @param executionTimeSeconds - Execution time in seconds
	 * @param validationsPassed - Number of validations passed
	 * @param validationsFailed - Number of validations failed
	 * @throws IOException if file write fails
	 */
	public static synchronized void addTestResult(
			Map<String, String> testData,
			String status,
			double executionTimeSeconds,
			int validationsPassed,
			int validationsFailed) throws IOException {
		// Call overloaded method with empty failed columns set and empty output validation map
		addTestResult(testData, status, executionTimeSeconds, validationsPassed, validationsFailed, 
		              new HashSet<>(), new java.util.HashMap<>());
	}

	/**
	 * Add a test result row to the Excel report with failed output column highlighting.
	 * Failed output columns will be highlighted in RED.
	 *
	 * @param testData - Map containing all field values (key = field name, value = field value)
	 * @param status - Test status: "PASS" or "FAIL"
	 * @param executionTimeSeconds - Execution time in seconds
	 * @param validationsPassed - Number of validations passed
	 * @param validationsFailed - Number of validations failed
	 * @param failedOutputColumns - Set of output column names that failed validation (will be highlighted RED)
	 * @throws IOException if file write fails
	 */
	public static synchronized void addTestResult(
			Map<String, String> testData,
			String status,
			double executionTimeSeconds,
			int validationsPassed,
			int validationsFailed,
			Set<String> failedOutputColumns) throws IOException {
		// Call overloaded method with empty output validation map
		addTestResult(testData, status, executionTimeSeconds, validationsPassed, validationsFailed, 
		              failedOutputColumns, new java.util.HashMap<>());
	}

	/**
	 * Add a test result row to the Excel report with output validation map for cell coloring.
	 * This method colors ALL data cells based on their corresponding output validation results:
	 * - GREEN background for cells where output validation passed (outputValidationMap value = 1)
	 * - RED background for cells where output validation failed (outputValidationMap value = 0)
	 *
	 * @param testData - Map containing all field values (key = field name, value = field value)
	 * @param status - Test status: "PASS" or "FAIL"
	 * @param executionTimeSeconds - Execution time in seconds
	 * @param validationsPassed - Number of validations passed
	 * @param validationsFailed - Number of validations failed
	 * @param failedOutputColumns - Set of output column names that failed validation
	 * @param outputValidationMap - Map of output key -> validation result (1=pass, 0=fail)
	 * @throws IOException if file write fails
	 */
	public static synchronized void addTestResult(
			Map<String, String> testData,
			String status,
			double executionTimeSeconds,
			int validationsPassed,
			int validationsFailed,
			Set<String> failedOutputColumns,
			Map<String, Integer> outputValidationMap) throws IOException {

		rowIndex++;

		// Open existing workbook
		Workbook workbook = new XSSFWorkbook(new FileInputStream(reportFilePath));
		XSSFSheet sheet = (XSSFSheet) workbook.getSheet("Test Results");

		// Create new row
		Row row = sheet.createRow(rowIndex);

		// Create cell styles
		CellStyle passStyle = workbook.createCellStyle();
		passStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		CellStyle failStyle = workbook.createCellStyle();
		failStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
		failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		// Style for failed output cells (RED background with white bold text)
		CellStyle failedOutputStyle = workbook.createCellStyle();
		failedOutputStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		failedOutputStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font whiteFontFailed = workbook.createFont();
		whiteFontFailed.setColor(IndexedColors.WHITE.getIndex());
		whiteFontFailed.setBold(true);
		failedOutputStyle.setFont(whiteFontFailed);
		
		// Style for passed output cells (GREEN background with white bold text)
		CellStyle passedOutputStyle = workbook.createCellStyle();
		passedOutputStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		passedOutputStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font whiteFontPassed = workbook.createFont();
		whiteFontPassed.setColor(IndexedColors.WHITE.getIndex());
		whiteFontPassed.setBold(true);
		passedOutputStyle.setFont(whiteFontPassed);
		
		

		CellStyle alternateRowStyle = workbook.createCellStyle();
		if (rowIndex % 2 == 0) {
			alternateRowStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			alternateRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		}

		// DEBUG: Print testData map size
		System.out.println("DEBUG: testData map size = " + (testData != null ? testData.size() : "null"));
		System.out.println("DEBUG: outputValidationMap size = " + outputValidationMap.size());
		if (testData != null && testData.size() > 0) {
			System.out.println("DEBUG: First 5 entries in testData:");
			int count = 0;
			for (Map.Entry<String, String> entry : testData.entrySet()) {
				System.out.println("  " + entry.getKey() + " = " + entry.getValue());
				if (++count >= 5) break;
			}
		}

		// Fill all input field values
		for (int i = 0; i < columnHeaders.length; i++) {
			Cell cell = row.createCell(i);

			// Get the field key (first part before colon if exists)
			String fieldKey = columnHeaders[i].contains(": ")
				? columnHeaders[i].split(": ")[0]
				: columnHeaders[i];

			// Try to lookup value using full header first, then try with just the field key
			String value = testData.get(columnHeaders[i]);
			if (value == null || value.isEmpty()) {
				value = testData.get(fieldKey);
			}

			// IMPORTANT: Set the cell value FIRST before applying any styling
			if (value != null && !value.isEmpty()) {
				// Try to parse as number, otherwise store as string
				try {
					double numValue = Double.parseDouble(value);
					cell.setCellValue(numValue);
				} catch (NumberFormatException e) {
					cell.setCellValue(value);
				}
			} else {
				cell.setCellValue("");
			}

			// THEN apply styling (color the cell based on output validation results)
			// Check if this column header matches any output key in the validation map
			Integer validationResult = null;
			
			// Try exact match first
			if (outputValidationMap.containsKey(columnHeaders[i])) {
				validationResult = outputValidationMap.get(columnHeaders[i]);
			} else if (outputValidationMap.containsKey(fieldKey)) {
				validationResult = outputValidationMap.get(fieldKey);
			}
			
			// Apply cell style based on validation result AFTER value is set
			if (validationResult != null) {
				if (validationResult == 1) {
					// Output validation passed - GREEN background
					cell.setCellStyle(passedOutputStyle);
				} else {
					// Output validation failed - RED background
					cell.setCellStyle(failedOutputStyle);
				}
			}
			// If no validation result found, leave cell without special styling (it's an input field)
		}

		// Add Status cell
		Cell statusCell = row.createCell(columnHeaders.length);
		String statusText = status.equalsIgnoreCase("PASS") ? 
			status + " (" + validationsPassed + "/" + (validationsPassed + validationsFailed) + ")" :
					status + " (" + validationsFailed + "/" + (validationsPassed + validationsFailed) + ")";
		statusCell.setCellValue(statusText);

		if (status.equalsIgnoreCase("PASS")) {
			statusCell.setCellStyle(passStyle);
		} else {
			statusCell.setCellStyle(failStyle);
		}

		// Add Execution Time cell
		Cell timeCell = row.createCell(columnHeaders.length + 1);
		timeCell.setCellValue(String.format("%.2f", executionTimeSeconds));

//		if (rowIndex % 2 == 0) {
//			timeCell.setCellStyle(alternateRowStyle);
//		}

		// Write to file
		OutputStream fileOut = new FileOutputStream(reportFilePath);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();

		String failedInfo = failedOutputColumns.isEmpty() ? "" : " [" + failedOutputColumns.size() + " failed outputs highlighted]";
		System.out.println("✓ Report updated: Row " + rowIndex + " - Status: " + status + " - Time: " + String.format("%.2f", executionTimeSeconds) + "s" + failedInfo);
	}
	
	/**
	 * Add summary row at the end of the report with total statistics.
	 * 
	 * @param totalTests - Total number of tests executed
	 * @param totalPassed - Total tests passed
	 * @param totalFailed - Total tests failed
	 * @param totalExecutionTime - Total execution time in seconds
	 * @throws IOException if file write fails
	 */
	public static void addSummaryRow(int totalTests, int totalPassed, int totalFailed, double totalExecutionTime) throws IOException {
		rowIndex += 2; // Skip one row for spacing
		
		Workbook workbook = new XSSFWorkbook(new FileInputStream(reportFilePath));
		XSSFSheet sheet = (XSSFSheet) workbook.getSheet("Test Results");
		
		// Create summary style
		CellStyle summaryStyle = workbook.createCellStyle();
		summaryStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
		summaryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font summaryFont = workbook.createFont();
		summaryFont.setBold(true);
		summaryStyle.setFont(summaryFont);
		
		// Summary row
		Row summaryRow = sheet.createRow(rowIndex);
		
		Cell labelCell = summaryRow.createCell(0);
		labelCell.setCellValue("SUMMARY");
		labelCell.setCellStyle(summaryStyle);
		
		Cell totalTestsCell = summaryRow.createCell(1);
		totalTestsCell.setCellValue("Total Tests: " + totalTests);
		totalTestsCell.setCellStyle(summaryStyle);
		
		Cell passedCell = summaryRow.createCell(2);
		passedCell.setCellValue("Passed: " + totalPassed);
		passedCell.setCellStyle(summaryStyle);
		
		Cell failedCell = summaryRow.createCell(3);
		failedCell.setCellValue("Failed: " + totalFailed);
		failedCell.setCellStyle(summaryStyle);
		
		Cell timeCell = summaryRow.createCell(4);
		timeCell.setCellValue("Total Time: " + String.format("%.2f", totalExecutionTime) + "s");
		timeCell.setCellStyle(summaryStyle);
		
		Cell avgTimeCell = summaryRow.createCell(5);
		double avgTime = totalTests > 0 ? totalExecutionTime / totalTests : 0;
		avgTimeCell.setCellValue("Avg Time: " + String.format("%.2f", avgTime) + "s");
		avgTimeCell.setCellStyle(summaryStyle);
		
		// Write to file
		OutputStream fileOut = new FileOutputStream(reportFilePath);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();
		
		System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
		System.out.println("║  COMPREHENSIVE TEST REPORT COMPLETED                           ║");
		System.out.println("╠════════════════════════════════════════════════════════════════╣");
		System.out.println("║  Report Location: " + String.format("%-41s", reportFilePath) + "║");
		System.out.println("║  Total Tests:     " + String.format("%-41d", totalTests) + "║");
		System.out.println("║  Passed:          " + String.format("%-41d", totalPassed) + "║");
		System.out.println("║  Failed:          " + String.format("%-41d", totalFailed) + "║");
		System.out.println("║  Total Time:      " + String.format("%-38.2fs", totalExecutionTime) + "║");
		System.out.println("║  Average Time:    " + String.format("%-38.2fs", avgTime) + "║");
		System.out.println("╚════════════════════════════════════════════════════════════════╝");
	}
	
	/**
	 * Get the current report file path
	 * @return Full path to the report file
	 */
	public static String getReportFilePath() {
		return reportFilePath;
	}
	
	/**
	 * Reset the report manager (useful for new test runs)
	 */
	public static void reset() {
		rowIndex = 0;
		currentTime = null;
		reportFilePath = null;
		columnHeaders = null;
	}
}
