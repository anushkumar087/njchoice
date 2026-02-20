package dataproviders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.DataProvider;
import util.DataFile;
import util.TestData;

/**
 * Central repository for TestNG DataProviders.
 * Contains reusable data providers for test classes with various data selection options.
 */
public class MasterDataProvider {
	
	/**
	 * DYNAMIC Data Provider - Selects which data provider to use based on runtime parameter
	 * Usage in TestNG XML: <parameter name="dataProviderType" value="First3Rows"/>
	 * Usage in Maven: -DdataProviderType=Random10Rows
	 * 
	 * Valid values: AllRows, First3Rows, First5Rows, First10Rows, 
	 *               Random3Rows, Random5Rows, Random10Rows, Random20Rows, SingleRandomRow
	 * Default: AllRows (if not specified)
	 * 
	 * @param context - TestNG context to read parameters
	 * @return Object[][] based on the selected data provider type
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="DynamicDataProvider")
	public static Object[][] getDynamicData(org.testng.ITestContext context) throws IOException {
		// Get parameter from TestNG XML or System property (Maven -D)
		String dataProviderType = context.getCurrentXmlTest().getParameter("dataProviderType");
		
		// If not in XML, check system property (for Maven command line)
		if (dataProviderType == null || dataProviderType.isEmpty()) {
			dataProviderType = System.getProperty("dataProviderType", "AllRows");
		}
		
		System.out.println("\n╔════════════════════════════════════════════════════════╗");
		System.out.println("║  DYNAMIC DATA PROVIDER SELECTION                       ║");
		System.out.println("╠════════════════════════════════════════════════════════╣");
		System.out.println("║  Selected Type: " + String.format("%-38s", dataProviderType) + "║");
		System.out.println("╚════════════════════════════════════════════════════════╝\n");
		
		// Route to appropriate data provider based on type
		switch (dataProviderType.toLowerCase()) {
			case "first3rows":
				return getFirst3Rows();
			case "first5rows":
				return getFirst5Rows();
			case "first10rows":
				return getFirst10Rows();
			case "random3rows":
				return getRandom3Rows();
			case "random5rows":
				return getRandom5Rows();
			case "random10rows":
				return getRandom10Rows();
			case "random20rows":
				return getRandom20Rows();
			case "singlerandomrow":
				return getSingleRandomRow();
			case "alldata":
				return getAllTestData();
			default:
				return getSingleRandomRow();
		}
	}
	
	/**
	 * Data provider that reads ALL rows from Master_Data.xlsx
	 * @return Object[][] containing all rows and columns from the Excel file
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="AllData")
	public static Object[][] getAllTestData() throws IOException {
		Object[][] testData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getFirstNRows(testData, 30);
	}
	
	/**
	 * Data provider that reads only the FIRST 3 rows from Master_Data.xlsx
	 * @return Object[][] containing first 3 rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="First3Rows")
	public static Object[][] getFirst3Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getFirstNRows(allData, 3);
	}
	
	/**
	 * Data provider that reads only the FIRST 5 rows from Master_Data.xlsx
	 * @return Object[][] containing first 5 rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="First5Rows")
	public static Object[][] getFirst5Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getFirstNRows(allData, 5);
	}
	
	/**
	 * Data provider that reads only the FIRST 10 rows from Master_Data.xlsx
	 * @return Object[][] containing first 10 rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="First10Rows")
	public static Object[][] getFirst10Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getFirstNRows(allData, 10);
	}
	
	/**
	 * Data provider that randomly picks 3 rows from Master_Data.xlsx
	 * @return Object[][] containing 3 random rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="Random3Rows")
	public static Object[][] getRandom3Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getRandomNRows(allData, 3);
	}
	
	/**
	 * Data provider that randomly picks 5 rows from Master_Data.xlsx
	 * @return Object[][] containing 5 random rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="Random5Rows")
	public static Object[][] getRandom5Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getRandomNRows(allData, 5);
	}
	
	/**
	 * Data provider that randomly picks 10 rows from Master_Data.xlsx
	 * @return Object[][] containing 10 random rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="Random10Rows")
	public static Object[][] getRandom10Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getRandomNRows(allData, 10);
	}
	
	/**
	 * Data provider that randomly picks 20 rows from Master_Data.xlsx
	 * @return Object[][] containing 20 random rows
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="Random20Rows")
	public static Object[][] getRandom20Rows() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getRandomNRows(allData, 20);
	}
	
	/**
	 * Data provider that picks a single random row from Master_Data.xlsx
	 * @return Object[][] containing 1 random row
	 * @throws IOException if file reading fails
	 */
	@DataProvider(name="SingleRandomRow")
	public static Object[][] getSingleRandomRow() throws IOException {
		Object[][] allData = TestData.getAllDataFromMasterSheet(DataFile.master_DataFile);
		return getRandomNRows(allData, 1);
	}
	
	// ==================== HELPER METHODS ====================
	
	/**
	 * Helper method to get first N rows from data
	 * @param allData - complete data array
	 * @param n - number of rows to return
	 * @return Object[][] containing first N rows
	 */
	private static Object[][] getFirstNRows(Object[][] allData, int n) {
		if (allData == null || allData.length == 0) {
			return new Object[0][0];
		}
		
		int rowsToReturn = Math.min(n, allData.length);
		Object[][] result = new Object[rowsToReturn][];
		
		for (int i = 0; i < rowsToReturn; i++) {
			result[i] = allData[i];
		}
		
		System.out.println("✓ DataProvider: Returning first " + rowsToReturn + " rows out of " + allData.length + " total rows");
		return result;
	}
	
	/**
	 * Helper method to get N random rows from data
	 * @param allData - complete data array
	 * @param n - number of random rows to return
	 * @return Object[][] containing N random rows
	 */
	private static Object[][] getRandomNRows(Object[][] allData, int n) {
		if (allData == null || allData.length == 0) {
			return new Object[0][0];
		}
		
		int rowsToReturn = Math.min(n, allData.length);
		
		// Convert array to list for easier manipulation
		List<Object[]> dataList = new ArrayList<>(Arrays.asList(allData));
		
		// Shuffle the list randomly
		Collections.shuffle(dataList, new Random(System.currentTimeMillis()));
		
		// Take first N rows after shuffling
		Object[][] result = new Object[rowsToReturn][];
		for (int i = 0; i < rowsToReturn; i++) {
			result[i] = dataList.get(i);
		}
		
		System.out.println("✓ DataProvider: Returning " + rowsToReturn + " random rows out of " + allData.length + " total rows");
		return result;
	}
}
