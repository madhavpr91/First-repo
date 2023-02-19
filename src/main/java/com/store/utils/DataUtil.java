package com.store.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.store.exceptions.DataSheetNotFoundInExcelException;
import com.store.exceptions.FileNotFoundException;
import com.store.exceptions.UnrecognizedFileFormatException;

public class DataUtil extends GenericUtil {
	private HashMap<String, String> map;
	
	private static ThreadLocal<DataUtil> threadLocal = new ThreadLocal<>();
	
	public static void set(DataUtil dataUtil) {
		threadLocal.set(dataUtil);
	}
	
	public static DataUtil get() {
		return threadLocal.get();
	}
	
	public HashMap<String, String> map() {
		return map;
	}
	
	public Map<String, String> getPropertyData(String filePath){
		Map<String, String> propertyData = new HashMap<>();
	
		try {
			Reader fileReader = new FileReader(filePath);
			Properties props = new Properties();
			props.load(fileReader);
			Set<Object> allKeys =  props.keySet();
			for (Object key: allKeys) {
				propertyData.put(key.toString(), props.getProperty(key.toString()));
			}
		System.out.println("FileReader ="+fileReader);
		System.out.println("Filepath "+filePath);

		} catch (Exception e) {
			System.out.println("Unable to fetch the property data from the file : " + filePath);
			System.out.println(e.getStackTrace());
		}

		return propertyData;
	}
	
	public Map<String, String> getTCData(String dataFilePath, String sheetName, String tcId){
		Map<String, String> tcData = new HashMap<>();
		XSSFWorkbook wb = getWorkBook(dataFilePath);
		XSSFSheet sheet = getDataSheet(wb, dataFilePath, sheetName);
		XSSFRow headerRow = sheet.getRow(0);
		XSSFRow tcRow = getTCRow(sheet, tcId);
		
		int lastCellNumber = headerRow.getLastCellNum();
		
		for (int cellNum = 0; cellNum < lastCellNumber; cellNum++) {			
			XSSFCell headerCell = headerRow.getCell(cellNum);			
			if (headerCell != null) {
				XSSFCell tcCell = tcRow.getCell(cellNum);
				String tcVal = (tcCell !=null)?tcCell.toString():"";
				tcData.put(headerCell.toString(), tcVal);
			}
//	System.out.println("CellNum = "+cellNum);
		}
	
	System.out.println("DataUtil Class tcData = "+tcData);
		return tcData;
		
	}

	private XSSFRow getTCRow(XSSFSheet sheet, String tcID) {
		int totalRows = sheet.getLastRowNum();
		int columnNumber = getColumnNumberByHeader(sheet, "TC_ID");
		for (int rNum = 1; rNum <= totalRows; rNum++) {
			XSSFRow row = sheet.getRow(rNum);
			if (row !=null) {
				XSSFCell cell =row.getCell(columnNumber);
				if (cell != null && cell.toString().equalsIgnoreCase(tcID))
						return row;
			}
		}
//		System.out.println("DataUtil Class  getTCRow(XSSFSheet sheet = "+sheet);
//		System.out.println("getTCRow tcID = "+tcID);
		return null;
	}

	private int getColumnNumberByHeader(XSSFSheet sheet, String columnHeader) {
		int columnNumber = -1;
		XSSFRow headerRow = sheet.getRow(0);
		int totalCols = headerRow.getLastCellNum();

		for (int cNum = 0; cNum < totalCols; cNum++) {
			XSSFCell cell = headerRow.getCell(cNum);
			if (cell != null) {
				if (cell.toString().equalsIgnoreCase(columnHeader)) {
					return cNum;
				}
			}
//		System.out.println("sheet = "+sheet);
//		System.out.println("columnHeader = "+columnHeader);
		}

		return columnNumber;
	}
	
	private XSSFSheet getDataSheet(XSSFWorkbook wb, String dataFilePath, String sheetName) {

		XSSFSheet sheet = wb.getSheet(sheetName);
		if (sheet == null) {
			throw new DataSheetNotFoundInExcelException("given data sheet : " + sheetName+ " not found in the data file : " + dataFilePath);
		} else {
			return sheet;
		}

	}

	private XSSFWorkbook getWorkBook(String filePath) {
//  System.out.println("DataUtil classFilepath getWorkBook = "+filePath);
		if (checkFileExists(filePath)) {
			try {
				XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
				System.out.println("Workbook "+wb);
				return wb;
			}catch (Exception e) {				
				throw new UnrecognizedFileFormatException("The given data file : " + filePath+ " is not a valid file. Data file should be a valid excel file.");
			}

		} else {
			throw new FileNotFoundException("The test data file : " + filePath+ " was not found.");
		}
	

	}
	
	public void data() {
		getTCData(DATA_FILES_PATH, tcName, CONFIG_FOLDER_PATH);
	}

	/*
	public Map<String, String> getPriceData(Map<String, String> element){
		Map<String, String> pricesData = new HashMap<>();
		
//	System.out.println("CellNum = "+cellNum);
		GlobalVariables.priceData = element;
	
	System.out.println("DataUtil Class priceData = "+priceData);
		return priceData;
		
	}*/
	
}
