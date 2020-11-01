package com.XE.utilities;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	//create instance of ExtentReports class which comes under extend report jar files
	private static ExtentReports extent;
	
	//create methods which returns object of ExtentReports class
	public static ExtentReports getInstance(){
		
		if(extent==null){
			
			//pass contructor argument in below line and crate object extent
			extent = new ExtentReports(".\\target\\surefire-reports\\html\\extent.html",true,DisplayOrder.OLDEST_FIRST);
			//below command load ReportConfig.xml file in whic formatting of report is handled
			extent.loadConfig(new File(".\\src\\test\\resources\\extentconfig\\ReportsConfig.xml"));
			
		}
		
		return extent;
		
	}

}
