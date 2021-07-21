package b2bSite.util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport {
	public static ExtentHtmlReporter extentHtmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp(){
		
		
		final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd.HH.mm.ss");
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("--Inside Extent Report method-------->>"+timestamp);
		String path= System.getProperty("user.dir")+"/Reports/ShirtmaxReport"+".html";
		System.out.println("extent Report path------->>"+path);
		extentHtmlReporter = new ExtentHtmlReporter(path);
		extentHtmlReporter.config().setEncoding("utf-8");
		extentHtmlReporter.config().setReportName("Test Case Extent report");
		extentHtmlReporter.config().setTheme(Theme.STANDARD);
		System.out.println("Inside Base Extent Report method");
		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Retail");
		extent.setSystemInfo("Browser", "Chrome");
		extent.attachReporter(extentHtmlReporter);
		//return extent;
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
	    if(result.getStatus() == ITestResult.FAILURE)
	    {
	        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
	        test.fail(result.getThrowable());
	    }
	    else if(result.getStatus() == ITestResult.SUCCESS)
	    {
	        test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
	    }
	    else
	    {
	        test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	        test.skip(result.getThrowable());
	    }
	}

	@AfterSuite
	public void tearDown(){
	    extent.flush();
	   }
}
