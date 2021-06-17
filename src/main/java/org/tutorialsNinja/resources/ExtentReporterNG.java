package org.tutorialsNinja.resources;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	static ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
		Date d = new Date();
		
		String filename = d.toString().replace(":", "_").replace(" ", "_") + ".html" ;
		String path= System.getProperty("user.dir")+"\\reports\\" + filename;
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Tutorials_Ninja Automation Report");
		reporter.config().setDocumentTitle("Test Result");
		
		extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Tutorials Ninja Team");
		
		return extent;
	}

}
