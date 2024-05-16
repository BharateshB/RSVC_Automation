package com.tyss.actitime.listeners;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.tyss.demo.baseutil.BaseTest;
import com.tyss.demo.reports.ExtentHCLManager;
import com.tyss.demo.reports.ExtentHCLTest;
import com.tyss.demo.util.WebActionUtil;
/**
 * Description: This class implements ITestListener and overrides methods like
 * onFinish, onTestFailure,onTestSkipped,onTestSuccess which are used in Extent
 * report and Emailable report.
 * 
 * @author Manikandan A
 * 
 */
public class TestListener implements ITestListener {

	String className;
	public static int iPassCount = 0;
	public static int iFailCount = 0;
	public static int iSkippedCount = 0;
	public static String profile = null;
	public static ArrayList sTestName = new ArrayList<String>();
	public static ArrayList sStatus = new ArrayList<String>();
	public static long totPassedTime = 0;
	public static long totFailedTime = 0;
	public static long totSkippedTime = 0;
	public static long totalTimeTaken = 0;
	public static String pass_Time = "0";
	public static String fail_Time = "0";
	public static String skip_Time = "0";
	public static String tot_Time = "0";
	public static Properties prop;
	public static String sDirPath = System.getProperty("user.dir");
	public static final String PDFREPORTPATH=sDirPath +"./docs"+".pdf";
	public static final String CONFIGPATH = sDirPath + "./conf/config.properties";
	static {
		BaseTest.logger.info("");
		profile = System.getProperty("profile");
		profile = "JavaMail";
		System.setProperty("profile", profile);
		BaseTest.logger.info("Running locally " + profile);
	}
	
	static Map<String, String> reportMailBody = new HashMap<String, String>();
	File pdfReports = new File(PDFREPORTPATH);
	
	/**
	 * Description :Flushes the Extent report and sends an email of the report.
	 * 
	 * @author Manikandan A
	 * @paran context
	 * 
	 */
	
	public void onFinish(ITestContext context) {
		
		iPassCount = context.getPassedTests().size();
		iFailCount = context.getFailedTests().size();
		iSkippedCount = context.getSkippedTests().size();
		int iTotal_Executed = iPassCount + iFailCount + iSkippedCount;
		totalTimeTaken = totPassedTime + totFailedTime + totSkippedTime;
		tot_Time = WebActionUtil.formatDuration(totalTimeTaken);
    	//sendMail(iPassCount, iFailCount, iSkippedCount, iTotal_Executed, pdfReports, profile);
		ExtentHCLTest.getExtent().flush();

	}

	public void onStart(ITestContext context) {

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	/**
	 * Description :Increases the fail count and add fail result in Extent
	 * report,adds screenshots to the  Extent report on Test case failure.
	 * 
	 * @author Manikandan A
	 * @param result
	 * 
	 */
	public void onTestFailure(ITestResult result) {
		
		iFailCount =iFailCount+1;
		ExtentHCLManager.getTestReport().log(Status.FAIL, result.getMethod().getMethodName() + "-Test case failed");
		try {
			ExtentHCLManager.getTestReport().addScreenCaptureFromPath(
					WebActionUtil.getScreenShot(System.getProperty("user.dir") + "/reports" + "/ScreenShots-"
							+ WebActionUtil.getCurrentDateTime() + "/screenshots/"));
		} catch (IOException e) {
			BaseTest.logger.error("Unable to attach the screenshot");
		}

	}
	/**
	 * Description :Increases the skip count and adds the skip result in Extent report.
	 * 
	 * @author Manikandan A
	 * @param result
	 * 
	 */
	public void onTestSkipped(ITestResult result) {
		iSkippedCount = iSkippedCount + 1;
		ExtentHCLManager.getTestReport().log(Status.SKIP, result.getMethod().getMethodName() + "-Test case Skipped");

	}

	public void onTestStart(ITestResult result) {

	}
	/**
	 * Description:Increases the pass count and adds the pass result in Extent report
	 * 
	 * @author Manikandan A
	 * @param result
	 * 
	 */
	public void onTestSuccess(ITestResult result) {
		iPassCount = iPassCount + 1;
		ExtentHCLManager.getTestReport().log(Status.PASS, result.getMethod().getMethodName() + "-Test case passed");
	}

	

	/**
	 * Description :Sets the status of the test execution in the mail report.
	 * 
	 * @author Manikandan A
	 * @param sName
	 * @param sResult
	 * @param sTestName
	 * @param sStatus
	 * 
	 */
	public static void setStatus(String sName, String sResult, ArrayList sTestName, ArrayList sStatus) {
		sName = sName.replace("test", "");
		sTestName.add(sName);
		sStatus.add(sResult);

		if (sResult.equals("Passed")) {
			iPassCount = iPassCount + 1;
		} else if (sResult.equals("Failed")) {
			iFailCount = iFailCount + 1;
		} else {
			iSkippedCount = iSkippedCount + 1;
		}
	}
}
