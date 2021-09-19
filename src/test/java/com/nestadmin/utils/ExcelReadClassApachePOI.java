package com.nestadmin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadClassApachePOI {

	public static HashMap<String, String> hm = new HashMap<String,String>();
	ArrayList<String> al=new ArrayList<String>();
	public HashMap<String, String> readExcel(String filePath, String fileName, String sheetName) throws IOException {

		// Create an object of File class to open xlsx file

		File file = new File(filePath + "\\" + fileName);
		
		// Create an object of FileInputStream class to read excel file

		FileInputStream inputStream = new FileInputStream(file);
		Workbook excelWorkbook = null;

		// Find the file extension by splitting file name in substring and getting only
		// extension name

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file

		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			excelWorkbook = new XSSFWorkbook(inputStream);
		}

		// Check condition if the file is xls file

		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			excelWorkbook = new HSSFWorkbook(inputStream);
		}

		// Read sheet inside the workbook by its name

		Sheet excelSheet = excelWorkbook.getSheet(sheetName);
		// Find number of rows in excel file
		int rowCount = excelSheet.getLastRowNum() - excelSheet.getFirstRowNum();
		// Create a loop over all the rows of excel file to read it
		//First line skipping by i=1
		for (int i = 1; i < rowCount + 1; i++) {
			Row row = excelSheet.getRow(i);
			// Create a loop to print cell values in a row
			for (int j = 0; j < row.getLastCellNum(); j++) {
				// Print Excel data in console
				System.out.print(row.getCell(j).getStringCellValue() + "| ");
				al.add(row.getCell(j).getStringCellValue());
			}
			hm.put(al.get(0),al.get(1));
			al.clear();
			System.out.println();
		}
		return hm;
	}

	// Main function is calling readExcel function to read data from excel file

	public static void main(String... strings) throws IOException {

		// Create an object of ReadexcelExcelFile class

		ExcelReadClassApachePOI objExcelFile = new ExcelReadClassApachePOI();
		// Prepare the path of excel file
		String filePath = System.getProperty("user.dir") + "\\src\\excelExportAndFileIO";
		// Call read file method of the class to read data
		objExcelFile.readExcel(filePath, "AutomationInput.xlsx", "Automation");
		System.out.println(hm);
	}

}
