package b2bSite.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestNGRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		XmlSuite suite = new XmlSuite();
		suite.setName("ShirtMax Suite");
		 
		XmlTest test = new XmlTest(suite);
		
		test.setName("ShirtMax Search and Proceed");		
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("b2bSite.tests.ShirtmaxTests"));
		test.setXmlClasses(classes) ;
		
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.setOutputDirectory(System.getProperty("user.dir")+"/test-output");
		tng.run();
		
		
	}

}
