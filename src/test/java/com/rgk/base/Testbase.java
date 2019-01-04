package com.rgk.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.rgk.utilities.ExcelReader;
import com.rgk.utilities.ExtentManager;
import com.rgk.utilities.TestUtil;

public class Testbase {
	/*
	 * All initializations done in this class Initializing the following: //
	 * Webdriver - done //properties - done // logs: using log4j jar files:
	 * log4j.properties. Then create the logger class // extent reports database
	 * excel reading part mailing part reportNG and testNG Jenkins integration
	 * 
	 */
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded!!");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR File loaded!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {
				browser = config.getProperty("browser");
			}
			config.setProperty("browser", browser);
			
			if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
				log.debug("Firefox launched!!");
				Reporter.log("Firefox launched !!");
			} else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome launched!!");
				Reporter.log("Chrome launched !!");
			} else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer32.exe");
				driver = new InternetExplorerDriver();
				log.debug("Internet explorer launched!!");
				Reporter.log("Internet Explorer launched !!");
			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("navigated to :- " + config.getProperty("testsiteurl"));
			// Reporter.log("Navigated to:- " +
			// config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}
	}

	public void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(OR.getProperty(locator))).click();
		} else {
			test.log(LogStatus.ERROR, "Invalid locator entered" + locator);
		}
		test.log(LogStatus.INFO, "Clicked on:- " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_CLASSNAME")) {
			driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_LINKTEXT")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_TAGNAME")) {
			driver.findElement(By.tagName(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			driver.findElement(By.partialLinkText(OR.getProperty(locator))).sendKeys(value);
		} else {
			test.log(LogStatus.ERROR, "Invalid locator entered" + locator);
		}
		test.log(LogStatus.INFO, "Typing in the field:- " + locator + " and entered value is:- " + value);
	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		} else if (locator.endsWith("_NAME")) {
			dropdown = driver.findElement(By.name(OR.getProperty(locator)));
		} else if (locator.endsWith("_CLASSNAME")) {
			dropdown = driver.findElement(By.className(OR.getProperty(locator)));
		} else if (locator.endsWith("_LINKTEXT")) {
			dropdown = driver.findElement(By.linkText(OR.getProperty(locator)));
		} else if (locator.endsWith("_TAGNAME")) {
			dropdown = driver.findElement(By.tagName(OR.getProperty(locator)));
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			dropdown = driver.findElement(By.partialLinkText(OR.getProperty(locator)));
		} else {
			test.log(LogStatus.ERROR, "Invalid locator entered" + locator);
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown- " + locator + " and entered value is:- " + value);
	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static void verifyEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			TestUtil.captureScreenshot();
			// This is for reportNg
			Reporter.log("<br>" + " Verification failure:- " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");

			// Code for Extent report
			test.log(LogStatus.FAIL, "Verification failed with Exception:- " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		}
	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();
			log.debug("Test execution completed!!");
			Reporter.log("Test suite execution completed");
		}

	}
}
