package com.XE.listeners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.XE.base.TestBase;
import com.XE.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

//extending this report to use some objects from TestBase class
public class CustomListeners extends TestBase implements ITestListener 
{
	public void onTestSkipped(ITestResult arg0)
	{
		
	}
	
	public void onTestFailure(ITestResult arg0)
	{
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		try {
			TestUtil.captureScreenshot();
			
			//generate link over here for showing screenshots and open screenshot in new tab
			 
			//Reporter.log("<a target=\"_blank\" href=\"C:\\temp\\TMPSS.JPG\">Screenshot</a>");
			Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
			//genearet thumnail of screenshot and open it in a new tab
			Reporter.log("<br>"); //break the line
			Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=200 width=200></a>");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//This is part of extent report, this code is mark test case as fail and capture screenshot
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+"Failed with exception :"+ arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		rep.flush();
		
		Reporter.log("Capturing screenshot");
		
		
	}
	
	public void onTestStart(ITestResult arg0)
	{
		test= rep.startTest(arg0.getName().toUpperCase());
		
	}
	
	//Extent Report objects and methdos are called from TestBase and ExtentManager report 
	public void onTestSuccess(ITestResult arg0)
	{
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS");
		rep.endTest(test);
		rep.flush();
	}
	
}