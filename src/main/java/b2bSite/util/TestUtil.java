package b2bSite.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class TestUtil {
	
	static String filepath=System.getProperty("user.dir")+"/";
	
	public static ArrayList<Object[]> excelReader(String sheetname, String name) {
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		System.out.println("inside Excel reader---sheet-->"+sheetname);
		try {
				File file = new File(filepath+name);
				if(!file.exists())
					Thread.sleep(3000);
		Workbook book = WorkbookFactory.create(file);
		Sheet sheet = (org.apache.poi.ss.usermodel.Sheet) book.getSheet(sheetname);
		System.out.println("created sheet"+((org.apache.poi.ss.usermodel.Sheet) sheet).getLastRowNum());
		String cell;
		int colCount = 0;
		for (int i = 1; i <= ((org.apache.poi.ss.usermodel.Sheet) sheet).getLastRowNum(); i++) {
			org.apache.poi.ss.usermodel.Row RowValue = ((org.apache.poi.ss.usermodel.Sheet) sheet).getRow(i);
			colCount = RowValue.getLastCellNum();
			ArrayList<String> row = new ArrayList<String>();
			for (int j = 0; j < colCount; j++) {
				cell = RowValue.getCell(j).toString().trim();
				row.add(cell);
			}
			data.add(row.toArray());
			System.out.println("--Single -Row " + row);
		}
		}
	 catch (IOException  e) {
		e.printStackTrace();
	}
	 catch (InterruptedException e) {
		e.printStackTrace(); 
	  }	
		return data;
	}

	//Reading the properties files 
	public static Properties initProperties() throws IOException {
		Properties prop = new Properties();
		FileInputStream Locator = new FileInputStream(
				"C:\\Users\\vramaswamy\\workspace\\b2b\\src\\main\\resources\\locators.properties");
		prop.load(Locator);
		return prop;
	}
	

}
