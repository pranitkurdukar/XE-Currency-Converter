package com.XE.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.XE.bizComponents.XECurrencyConverterPage;
import com.XE.utilities.ExcelReader;
import com.XE.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {

	/*
	 * initialise the objects of webdriver, wait, properties, fileinputstream, logger, excel reader extendreports class
	 * Load Properties files 
	 * Define Implicit and Explicit waits
	 * Logs = log4j jar, .log, log4j.properties(this file need to be added in the
	 * properties folder, logger class, ExtentReports, DB Excel, ReportNG,
	 * 
	 */

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	// Below object is for capturing the log, devpinoyLogger is standard name
	public static Logger log = Logger.getLogger("devpinoyLogger");
	// excel object is used in all test classes where test data is fetched from excel sheet
	public static ExcelReader excel = new ExcelReader(".\\src\\test\\resources\\excel\\testdata.xlsx");
	// create object of extend report
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static XECurrencyConverterPage currencyConverterPage = new XECurrencyConverterPage();

	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		// 1. load config and or properties files

		if (driver == null) {

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				config.load(fis);
				// use log.debug to generate logs
				Reporter.log("Config property file is loaded !!! \n");
				log.debug("Property file is loaded !!!");
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			try {
				fis = new FileInputStream(".\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
			try {
				OR.load(fis);
				Reporter.log("Object Repository (OR) property file is loaded !!! \n");
				log.debug("Object Repository (OR) property file is loaded !!!");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		// 2. Load WebDrivers.

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			Reporter.log("Chrome WebDriver is loaded \n");
			log.debug("Chrome WebDriver is loaded");
		} else if (browser.equals("ie")) {
			
			InternetExplorerOptions ioptions = new InternetExplorerOptions();
			
			ioptions.setCapability("nativeEvents", false);
			ioptions.setCapability("unexpectedAlertBehaviour", "accept");
			ioptions.setCapability("ignoreProtectedModeSettings", true);
			ioptions.setCapability("disable-popup-blocking", true);
			ioptions.setCapability("enablePersistentHover", true);
			ioptions.setCapability("ignoreZoomSetting", true);
			ioptions.setCapability("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
			ioptions.setCapability("screenResolution", "1024×768");
	
			
			System.setProperty("webdriver.ie.driver", ".\\src\\test\\resources\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(ioptions);
			Reporter.log("Chrome WebDriver is loaded \n");
			log.debug("Chrome WebDriver is loaded");
		} else if (browser.equals("firefox")) {
			
			FirefoxOptions foptions = new FirefoxOptions();
			foptions.setCapability("nativeEvents", false);
			foptions.setCapability("unexpectedAlertBehaviour", "accept");
			foptions.setCapability("ignoreProtectedModeSettings", true);
			foptions.setCapability("disable-popup-blocking", true);
			foptions.setCapability("enablePersistentHover", true);
			foptions.setCapability("ignoreZoomSetting", true);
			foptions.setCapability("INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS", true);
			
			System.setProperty("webdriver.gecko.driver", ".\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			Reporter.log("Firefox WebDriver is loaded \n");
			log.debug("Firefox WebDriver is loaded");

		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\src\\test\\resources\\executables\\msedgedriver.exe");
			driver = new EdgeDriver();
			Reporter.log("Edge WebDriver is loaded \n");
			log.debug("Edge WebDriver is loaded");

		} 
	
		// 3. waits
		// explicit wait
		wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));
		

		// 4. implicit timeouts
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
	}

	@AfterClass
	public void tear() {

		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
			Reporter.log("Quit from WebDriver \n");
			log.debug("Quit from WebDriver");

		}
		Reporter.log("Test Execution is completed \n");
		log.debug("Test Execution is completed");

	}


}