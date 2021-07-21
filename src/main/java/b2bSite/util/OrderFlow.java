package b2bSite.util;
import b2bSite.util.TestUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.xml.sax.Locator;

import com.aventstack.extentreports.Status;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import org.apache.log4j.Logger;


public class OrderFlow {
	
	public WebDriver driver;
	public static WebElement ele;
	public static Workbook book;
	public static Sheet sheet;
	public final String SCENARIO_SHEET_PATH = System.getProperty("user.dir") + "/TestCase.xls";
	public static Properties prop;
	int f = 0;
	
	public void startOrder(WebDriver driver, String[] data, String sheetname, com.aventstack.extentreports.ExtentTest test) throws Exception, IOException  {
		prop = TestUtil.initProperties();
		File file = new File(SCENARIO_SHEET_PATH);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = (org.apache.poi.ss.usermodel.Sheet) book.getSheet(sheetname);
		int col = 0, j = 0;
		for (int i = 1; i <= sheet.getLastRowNum(); i++) 
		{
			try {
				//logging the executed steps in Extent Report
				test.log(Status.INFO, sheet.getRow(i).getCell(col).toString().trim());
				String locatorType = sheet.getRow(i).getCell(col + 1).toString().trim();
				String action = sheet.getRow(i).getCell(col + 2).toString().trim();
				String locatorValue = sheet.getRow(i).getCell(col + 3).toString().trim();
							
				if (action.equalsIgnoreCase("sendkeys")) 
					{
						OrderFlow.sendKey(driver,locatorType,locatorValue,data[j]);
						System.out.println(data[j]);
						j++;
					}else if (action.equalsIgnoreCase("enterkey")) 
					{   j--;
						OrderFlow.enterKey(driver,locatorType,locatorValue);
						
					}else if (action.equalsIgnoreCase("click"))
					{
						OrderFlow.click(driver,locatorType,locatorValue);
					}
				
				}
			catch(Exception e) {
				
				e.printStackTrace();
				throw(e);
				
				}
		 }
		
	 }

	public static void sendKey(WebDriver driver, String locatorType, String locator, String value) throws Exception {
		ele = OrderFlow.getElement(driver, locator, locatorType);
		ele.sendKeys(value);
	}

	private static void enterKey(WebDriver driver2, String locatorType, String locatorValue) throws IOException {
		ele = OrderFlow.getElement(driver2, locatorValue, locatorType);
		ele.sendKeys(Keys.ENTER);
	}
	
	public static void click(WebDriver driver, String locatorType, String locator) throws Exception {
		WebElement clkEle = OrderFlow.getElement(driver, locator, locatorType);
		clkEle.click();
	}	
	
	public static WebElement getElement(WebDriver driver, String locator, String locatorType) throws IOException {
		Properties prop = TestUtil.initProperties();
		WebElement ele = null;
		if (locatorType.equalsIgnoreCase("id"))
			ele = driver.findElement(By.id(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("xpath"))
			ele = driver.findElement(By.xpath(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("name"))
			ele = driver.findElement(By.name(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("cssselector"))
			ele = driver.findElement(By.cssSelector(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("linktext"))
			ele = driver.findElement(By.linkText(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("classname"))
			ele = driver.findElement(By.className(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("tagname"))
			ele = driver.findElement(By.tagName(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("partiallinktext"))
			ele = driver.findElement(By.partialLinkText(prop.getProperty(locator)));
		else if (locatorType.equalsIgnoreCase("dynamicxpath"))
			ele = driver.findElement(By.xpath(locator));
		return ele;
	}
	
}