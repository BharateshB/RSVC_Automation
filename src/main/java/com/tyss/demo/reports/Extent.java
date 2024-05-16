package com.tyss.demo.reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.tyss.demo.util.WebActionUtil;
import com.tyss.demo.util.commonutils.FileVariables;

/**
 * Description: Implements the creation of the Extent Html report with specified
 * name after loading the Extent config file from specified location.
 * 
 * @author Manikandan A
 */
public class Extent {
	
	private ExtentReports extent;
	FileVariables fileVariables = new FileVariables();
	/**
	 * Description: Creates of HTML report in specified path
	 * 
	 * @author Manikandan A
	 */
	public  ExtentReports getExtent(String filepath) {

		if (extent == null) {
			try {
				extent = new ExtentReports();
				extent.attachReporter(getHtmlReporter(filepath+  " _Report.html"));
				return extent; 
			} catch (Exception e) {
				WebActionUtil.info("Exception while creating report html file.");
				
			}
		}
		return extent;
	}
	/**
	 * Description :Loads the Extent-config file specified from the location
	 * 
	 * @author Manikandan A
	 */
	private static ExtentHtmlReporter getHtmlReporter(String filePath) {
		
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "./conf\\extent-config.xml");
		return htmlReporter;
	}

}
