package b2bSite.tests;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import b2bSite.util.ExtentReport;
import b2bSite.util.OrderFlow;
import b2bSite.util.TestUtil;


public class ShirtmaxTests extends ExtentReport {

	WebDriver driver;
	
	
	@BeforeTest
	public void beforeMethod(ITestContext context) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\vramaswamy\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.shirtmax.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
   }

	
	@DataProvider
	public Iterator<Object[]> getRegProducts() {
		ArrayList<Object[]> data = TestUtil.excelReader("sheet1", "ProductsData.xlsx");
		return data.iterator();
	}
	
	@Test(dataProvider = "getRegProducts")
	public void SingleOrderTest(String Style, String Color, String Size, String Qnty) throws Exception {
		System.out.println("Style ---"+Style);
		System.out.println("Shirtmax test------Started");
		Properties prop = TestUtil.initProperties();
		test = extent.createTest("ShirtMax Search and Order ");
		test.log(Status.INFO, "Opened Shirtmax Home Page ");
		
		//String[] data = { prop.getProperty("userid_admin"), prop.getProperty("pwd_admin"), Style, Color, Size, Qnty,
		//		"804 N Jerry St", "Raymore", "Missouri", "64083-9763", "3333222111" };
		String[] data = { Style,Color,Size,Qnty };
		//System.out.println("contens of data" + data[0]+','+data[1]);
		OrderFlow orderflow = new OrderFlow();
		orderflow.startOrder(driver, data, "SM", test);

	}	
	
	@AfterTest
	public void afterTest(){
		driver.quit();
	}
}
