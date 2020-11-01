package com.XE.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;


import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;


	public ExcelReader(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();

	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {

		int index = workbook.getSheetIndex(sheetName);

		sheet = workbook.getSheetAt(index);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		System.out.println("cell string value is " + cell.getStringCellValue());
		return cell.getStringCellValue();
	}

}
