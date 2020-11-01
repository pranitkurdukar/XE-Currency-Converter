package com.XE.bizComponents;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.apache.commons.math3.util.Precision;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

import com.XE.base.TestBase;
import com.XE.utilities.TestUtil;

public class XECurrencyConverterPage extends TestBase {

	public static void handleCookie() throws Exception  {

		if (TestUtil.isElementPresent(TestUtil.getObjectBy("HOMEPAGE.cookieManagementButton_CSS"))) {
			TestUtil.click("HOMEPAGE.cookieManagementButton_CSS");
			Reporter.log("Clicked OK button on Cookie Warning Message Box \n");
			log.debug("Clicked OK button on Cookie Warning Message Box");
		} else {
			Reporter.log("Cookies warning message is not displayed \n");
			log.debug("Cookies warning message is not displayed");
		}
	
	}

	public static void handleMarkettingMessage() throws Exception {
			if (TestUtil.isElementPresent(TestUtil.getObjectBy("HOMEPAGE.welcomeFrame_XPATH"))) {
			WebElement frame = driver.findElement(TestUtil.getObjectBy("HOMEPAGE.welcomeFrame_XPATH"));
			driver.switchTo().frame(frame);
			if (TestUtil.isElementPresent(TestUtil.getObjectBy("HOMEPAGE.welcomeToXeDialog_CSS"))) {
			TestUtil.switchToParentFrame();
			if (TestUtil.isElementPresent(TestUtil.getObjectBy("WELCOMEDIALOG.closeButton_CSS"))) {
			WebElement ele=driver.findElement(TestUtil.getObjectBy("WELCOMEDIALOG.closeButton_CSS"));
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", ele);
			Reporter.log("Closed Marketting Message Box \n");
			log.debug("Closed Marketting Message Box");
			} else if (TestUtil.isElementPresent(TestUtil.getObjectBy("WELCOMEFRAME.continueToSiteButton_XPATH"))) {
				WebElement ele=driver.findElement(TestUtil.getObjectBy("WELCOMEFRAME.continueToSiteButton_XPATH"));
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("arguments[0].click()", ele);
				Reporter.log("Closed Marketting Message Box \n");
				log.debug("Closed Marketting Message Box");
			}
		}
	}
			
}

	public static void convertCurrency(double amt, String fromCurrencyCode, String toCurrencyCode) throws Exception {
		
				
		Robot robot = new Robot();
		

		TestUtil.type("HOMEPAGE.amountTextBox_CSS", Double.toString(amt));

		handleMarkettingMessage();
		TestUtil.click("HOMEPAGE.currencyCodeFROM_XPATH");
		TestUtil.type("HOMEPAGE.currencyCodeFROM_XPATH", fromCurrencyCode);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
			
		TestUtil.click("HOMEPAGE.currencyCodeTO_XPATH");
		TestUtil.type("HOMEPAGE.currencyCodeTO_XPATH", toCurrencyCode);
		Thread.sleep(3000);
		robot.keyPress(KeyEvent.VK_ENTER);
		TestUtil.click("HOMEPAGE.submitButton_XPATH");
		
		if (TestUtil.isElementPresent(TestUtil.getObjectBy("CONVERSIONRESULT.convertedAmount_XPATH"))) {
			double resultAmount = Double.valueOf(driver.findElement(TestUtil.getObjectBy("CONVERSIONRESULT.convertedAmount_XPATH")).getText().replaceAll(",", ""));
			double exchangeRate= Double.valueOf(driver.findElement(TestUtil.getObjectBy("CONVERSIONRESULT.exchangeRate_XPATH")).getText().split("= ")[1].replaceAll(toCurrencyCode, ""));
			Assert.assertEquals(Precision.round(resultAmount,0), Precision.round((exchangeRate*amt),0),"Converted Amount is successfully verified");
			Reporter.log("Test Case PASSED - Converted Amount is correclty calculated on Result Page");
			log.debug("Test Case PASSED - Converted Amount is correclty calculated on Result Page");
			System.out.println("Result Amount "+ resultAmount);
			System.out.println("Calculated Amount "+exchangeRate*amt);
			
					
		}
		
	}

}
