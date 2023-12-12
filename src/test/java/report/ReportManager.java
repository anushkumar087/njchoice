package report;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.DataFile;
import util.Reusables;

public class ReportManager {
	
	public static Map<String,String[]> ioMapping;
	
	public static int rowIndex = 0;
	public static String current_time = null;
	
	public static void createSheet() throws IOException
	{
		
		ioMapping = new HashMap<String,String[]>();
		
		SimpleDateFormat f = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss_SSS");
		System.out.println(f.format(new Date()));
		
		current_time = f.format(new Date());
		    
		//creating an instance of Workbook class   
		Workbook wb = new XSSFWorkbook();   
		
		//invoking creatSheet() method and passing the name of the sheet to be created   
		XSSFSheet sheet = (XSSFSheet) wb.createSheet("Report");   
		
		//creating the 0th row using the createRow() method  
		XSSFRow rowhead = sheet.createRow((short)rowIndex);  
		// Create array of Cell
		Cell[] headCells = new Cell[6];
		
		for(int i =0; i<headCells.length; i++)
		{
			headCells[i] = rowhead.createCell(i);
		}
		
		
		for (int i = 0; i < headCells.length; i++) 
		{
			CellStyle style = wb.createCellStyle(); //Create new style
            style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
            style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headCells[i].setCellStyle(style); //Apply style to cell
            style.setFillPattern(FillPatternType.BIG_SPOTS);
			if(i==4)
				sheet.setColumnWidth(i, 255*10);
			else
				sheet.setColumnWidth(i, 255*30);
		}
		
		headCells[0].setCellValue("Caps/Scales");  
		headCells[1].setCellValue("Inputs");  
		headCells[2].setCellValue("Expected Output");  
		headCells[3].setCellValue("Actual Output");  
		headCells[4].setCellValue("Result");
		headCells[5].setCellValue("Execution time in minutes");
				
		//creates an excel file at the specified location  
		OutputStream fileOut = new FileOutputStream(DataFile.reportSheet+"_"+current_time+".xlsx");   
		wb.write(fileOut); 
		fileOut.close();
		wb.close();
	}
	
	
	
	public static synchronized void makeRowEntry(String methodName, Object[] ioValues, int status, long executionTime) throws FileNotFoundException, IOException
	{
		rowIndex++;
		
		String[] ioValuesString = Arrays.copyOf(ioValues, ioValues.length, String[].class);
		
		Workbook wb = new XSSFWorkbook(new FileInputStream(DataFile.reportSheet+"_"+current_time+".xlsx"));
		XSSFSheet sheet = (XSSFSheet)wb.getSheet("Report");
		
		//creating the 0th row using the createRow() method  
		XSSFRow row = sheet.createRow((short)rowIndex);  
		// Create array of Cell
		Cell[] cells = new Cell[6];
		
		for(int i =0; i<cells.length; i++)
		{
			cells[i] = row.createCell(i);
		}
		
		
		for (int i = 0; i < cells.length; i++) 
		{
			CellStyle style = wb.createCellStyle(); //Create new style
			if(rowIndex%2==0)
			{
	            style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	            style.setFillForegroundColor(IndexedColors.WHITE.getIndex());
	            cells[i].setCellStyle(style); //Apply style to cell
	            style.setFillPattern(FillPatternType.BIG_SPOTS);
			}
		}
		
		String[] ioFields = ReportManager.ioMapping.get(methodName);
		
		// First cell is Caps / Scales name
		cells[0].setCellValue(ioFields[ioFields.length-1]); 
		
		// Second cell should be input values in proper format
		String inputs = "";
		for(int i=0; i<ioFields.length-1;i++)
		{
			inputs = inputs + ioFields[i]+" = "+ioValues[i]+"; ";
		}
		cells[1].setCellValue(inputs);
		
		// Third cell should be expected output
		cells[2].setCellValue(ioValuesString[ioValuesString.length-1]);
		
		// Fourth cell should be actual output from UI
		cells[3].setCellValue(Reusables.actualOutputFromUI);
		
		// Fifth cell should be result
		String result = "";
		CellStyle styleResult = wb.createCellStyle();
		if(status==1)
		{
			result = "PASS";
			styleResult.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
			styleResult.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cells[4].setCellStyle(styleResult); //Apply style to cell
            styleResult.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		else if(status==2)
		{
			result = "FAIL";
			styleResult.setFillBackgroundColor(IndexedColors.RED.getIndex());
			styleResult.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cells[4].setCellStyle(styleResult); //Apply style to cell
            styleResult.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		else if(status==3)
		{
			result = "SKIP";
			styleResult.setFillBackgroundColor(IndexedColors.BLUE_GREY.getIndex());
			styleResult.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cells[4].setCellStyle(styleResult); //Apply style to cell
            styleResult.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		else {
			result = "???";
			styleResult.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
			styleResult.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            cells[4].setCellStyle(styleResult); //Apply style to cell
            styleResult.setFillPattern(FillPatternType.BIG_SPOTS);
		}
		
		cells[4].setCellValue(result);
		cells[5].setCellValue(Long.toString(executionTime));
		
//		if(!Reusables.actualOutputFromUI.isEmpty())
//		{
			//creates an excel file at the specified location  
			OutputStream fileOut = new FileOutputStream(DataFile.reportSheet+"_"+current_time+".xlsx");   
			wb.write(fileOut); 
			fileOut.close();
//		}
		wb.close();
		
	}

}
