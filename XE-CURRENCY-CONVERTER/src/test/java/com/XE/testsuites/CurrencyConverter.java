package com.XE.testsuites;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.XE.base.TestBase;
import com.XE.bizComponents.XECurrencyConverterPage;
import com.XE.utilities.TestUtil;

public class CurrencyConverter extends TestBase {
	
	@Test(dataProvider="getData")
	public static void verifyConversionRate(String Amt, String FromCurrency, String ToCurrency) throws Exception {
	TestUtil.launchURL("xeweburl");
	XECurrencyConverterPage.handleCookie(); 
	XECurrencyConverterPage.convertCurrency(Double.valueOf(Amt),FromCurrency,ToCurrency);
	driver.manage().deleteAllCookies();
	
	}
	
	@DataProvider
	public Object[][] getData() {
		String sheetName = "verifyConversionRate";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		System.out.println("Rows count "+rows);
		System.out.println("Cols count "+cols);
		
		//declare the 2D-array of type Object along with length
		Object[][] data = new Object[rows-1][cols];
		
		for (int rowNum=1; rowNum<rows; rowNum++) {
			for (int colNum=0; colNum<cols; colNum++) {
				//first record data[0][0]
				data[rowNum-1][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		return data;
		
	}
}
