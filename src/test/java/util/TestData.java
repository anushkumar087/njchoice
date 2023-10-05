package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import base.Base;

public class TestData {
	
	public static Object[][] getRequiredDataFromMasterSheet(String[] inputs, String output, String filePathToRead) throws IOException
	{
		Object[][] testDataCollection = null;
		Object[][] testDataCollectionComp = null;

		Workbook wb= WorkbookFactory.create(new File(filePathToRead)); 

		Sheet sheet = wb.getSheetAt(0);
		
		int noOfColumnsTotal = sheet.getRow(0).getLastCellNum();
		
		int noOfColumns = inputs.length;
		
		int noOfRows = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		testDataCollection = new Object[noOfRows][noOfColumnsTotal];
		testDataCollectionComp = new Object[noOfRows][noOfColumns+1];
		
		int[] cellIndexInRow = new int[noOfColumns+1];
		Arrays.fill(cellIndexInRow, -1);
		
		int rowCount=0;
		int cellCount=0;
		int inputIndexCount=0;
		
		for(Row row: sheet)  //iteration over row using for each loop  
		{  
			for(Cell cell: row)	//iteration over cell using for each loop  
			{ 
				if(rowCount==0)
				{
					for(int j = 0 ; j<noOfColumns; j++)
					{
						if(cell.getStringCellValue().toString().toLowerCase().contains(inputs[j].toLowerCase()))
						{
							cellIndexInRow[inputIndexCount] = cellCount;
							inputIndexCount++;
						}
						if(cell.getStringCellValue().toString().toLowerCase().contains(output.toLowerCase()))
						{
							cellIndexInRow[cellIndexInRow.length-1] = cellCount;
						}
					}
				}
				if(rowCount>0)
				{
					for(int i=0; i<cellIndexInRow.length; i++)
					{
						if(cellIndexInRow[i]==cellCount)
						{
							try
							{
								if(cell.getNumericCellValue()%1==0.0)
									testDataCollectionComp[rowCount-1][i] = Integer.toString((int)cell.getNumericCellValue());
								else
									testDataCollectionComp[rowCount-1][i] = Double.toString(cell.getNumericCellValue());
							}
							catch(IllegalStateException e)
							{
								testDataCollectionComp[rowCount-1][i] = cell.getStringCellValue();
							}
							inputIndexCount++;
						}
					}
					
					try
					{
						testDataCollection[rowCount-1][cellCount] = Integer.toString((int)cell.getNumericCellValue());
					}
					catch(IllegalStateException e)
					{
						testDataCollection[rowCount-1][cellCount] = cell.getStringCellValue();
					}
				}
				
				cellCount++;
			}
			
			cellCount=0;
			inputIndexCount=0;
			rowCount++;
		}
		System.out.println("cellIndexInRow = "+Arrays.toString(cellIndexInRow));
		System.out.println("testData = "+Arrays.deepToString(testDataCollection).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
		System.out.println();
		System.out.println();
		System.out.println("testDataComp = "+Arrays.deepToString(testDataCollectionComp).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
		return testDataCollectionComp;
		
	}

}
