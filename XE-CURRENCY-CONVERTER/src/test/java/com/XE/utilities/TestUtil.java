package com.XE.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Reporter;

import com.XE.base.TestBase;


public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		//TakeScreenshot returns the file
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		//copy srcfile to target folder
		FileUtils.copyFile(scrFile,
				new File(".\\target\\surefire-reports\\html\\" + screenshotName));
		FileUtils.copyFile(scrFile,
				new File(".\\test-output\\html\\" + screenshotName));

		
	}

	
	public static void click(String locator) {

		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).click();
			Reporter.log("Clicked on "+locator);
			log.debug("Clicked on "+locator);
		}
		
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();			
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).clear();		
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).clear();		
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).clear();		
			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).clear();	
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(OR.getProperty(locator))).clear();	
			driver.findElement(By.partialLinkText(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).clear();
			driver.findElement(By.tagName(OR.getProperty(locator))).sendKeys(value);
			Reporter.log("Typing in: " + locator + " entered value as " + value);
			log.debug("Typing in: " + locator + " entered value as " + value);
		}

		
	}
	
	
	public static boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }
	}
	
	public static void scrollPage()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	
	public static boolean isElementPresent(By by) {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			
			return true;

		} catch (Throwable t) {
			
			return false;

		}
	}
	
	public static By getObjectBy(String objKey) throws Exception {
		
		By objectBy=null;
		
		try {
		if (objKey.endsWith("_XPATH")) {
			
			if (isElementPresent(By.xpath(OR.getProperty(objKey)))) {
				objectBy=By.xpath(OR.getProperty(objKey));
				Reporter.log("Object "+objKey+" found.");
				log.debug("Object "+objKey+" found.");
			};
		} else if (objKey.endsWith("_CSS")) {
			if (isElementPresent(By.cssSelector(OR.getProperty(objKey)))) {
			objectBy=By.cssSelector(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else if (objKey.endsWith("_ID")) {
			if (isElementPresent(By.id(OR.getProperty(objKey)))) {
			objectBy=By.id(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else if (objKey.endsWith("_NAME")) {
			if (isElementPresent(By.name(OR.getProperty(objKey)))) {
			objectBy=By.name(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else if (objKey.endsWith("_LINKTEXT")) {
			if (isElementPresent(By.linkText(OR.getProperty(objKey)))) {
			objectBy=By.linkText(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else if (objKey.endsWith("_CLASSNAME")) {
			if (isElementPresent(By.className(OR.getProperty(objKey)))) {
			objectBy=By.className(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else if (objKey.endsWith("_PARTIALLINKTEXT")) {
			if (isElementPresent(By.partialLinkText(OR.getProperty(objKey)))) {
			objectBy=By.partialLinkText(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else if (objKey.endsWith("_TAGNAME")) {
			if (isElementPresent(By.tagName(OR.getProperty(objKey)))) {
			objectBy=By.tagName(OR.getProperty(objKey));
			Reporter.log("Object "+objKey+" found.");
			log.debug("Object "+objKey+" found.");
			}
		} else {
			Reporter.log("Object "+objKey+" NOT found.");
			log.debug("Object "+objKey+" NOT found.");
			throw new Exception("Object identifier needs to be corrected in property file:"+objKey);
		}
				
		return objectBy;
		
		} catch (NoSuchElementException e) {
			Reporter.log(e.getMessage());
			log.debug(e.getMessage());
			return objectBy;
		}

		
	}
	
	public static void launchURL(String configParameter) {
		// launch application URL
		driver.get(config.getProperty(configParameter));
		Reporter.log("URL: "+configParameter+" Launched");
		log.debug("URL: "+configParameter+" Launched");

		// maximise window
		driver.manage().window().maximize();
		Reporter.log("Window maximised");
		log.debug("Window maximised");

	}
	

		
		public static void switchToParentFrame() {
					
				driver.switchTo().defaultContent();
				
			}
	}


