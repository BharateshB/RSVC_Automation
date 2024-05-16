package com.tyss.demo.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tyss.demo.baseutil.BaseTest;
import com.tyss.demo.reports.ExtentHCLManager;

/**
 * Description: All the action utilities added in this class e.g
 * clickonelement,WaitforElement etc
 * 
 * @author : Manikandan A
 */


public class WebActionUtil {
	public WebDriver driver;
	WebDriverWait wait;
	public long ETO;
	public JavascriptExecutor jsExecutor;
	public Actions action;
	public static TakesScreenshot screenshot;

	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver, ETO);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		screenshot=(TakesScreenshot)driver;
	}


	/*@Test
	public void kendoCalendarExample() {
		driver.navigate().to(kendoURL);
		WebElement calIcon=driver.findElement(By.cssSelector(".k-icon.k-i-calendar"));
		calIcon.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("datetimepicker_dateview")));
		System.out.println("Calendar Found");
		selectKendoDate("10");
	}*/

	public void selectKendoDate(String date) {

		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("k-content")));
		WebElement table = driver.findElement(By.className("k-content")); 

		System.out.println("Kendo Calendar");
		List<WebElement> tableRows = table.findElements(By.xpath("//tr"));
		for (WebElement row : tableRows) {
			List<WebElement> cells = row.findElements(By.xpath("td"));

			for (WebElement cell : cells) {
				if (cell.getText().equals(date)) {
					driver.findElement(By.linkText(date)).click();
				}
			}
		}
	}

	/**
	 * 
	 * Description Method for the pass updation in extent Report,Log file,TestNG
	 *              Report
	 * @author Sushmitha P H
	 */

	public static void pass(String message) {
		ExtentHCLManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}
	/**Description Method to provide info in the log,extent reports,testNg reports
	 * @author Sushmitha P H
	 * 
	 */
	public static void info(String message) {
		Reporter.log(message, true);
		BaseTest.logger.info(message);
		ExtentHCLManager.getTestReport().info(message);

	}

	/**
	 * 
	 * Description Method for the Warning updation in extent Report,Log file,TestNG
	 *              Report
	 *  @author Sushmitha P H
	 */

	public void warn(String message) {

		BaseTest.logger.warn(message);
		Reporter.log(message, true);
	}

	/**
	 * 
	 * Description Method for the error/Failure updation in extent Report,Log
	 *              file,TestNG Report
	 *  @author Sushmitha P H
	 *              
	 */

	public static void fail(String message) {
		Reporter.log(message, true);
		ExtentHCLManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));

	}
	public void error(String message) {

		BaseTest.logger.error(message);
		Reporter.log(message, true);
		ExtentHCLManager.getTestReport().error(message);
	}

	/**
	 * 
	 * Description Method for fetching of get Current Date Time
	 *  @author Sushmitha P H
	 *              
	 */

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
	/**
	 * 
	 * Description Method clicks on web element
	 *  @author Sushmitha P H
	 *              
	 */

	public synchronized void clickOnWebElement(WebElement element, String elementName,String message) {


		if (isElementClickable(element, elementName)) {
			element.click();
			pass("Click on " + elementName);
		}
		else {
			fail(message);
			Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(element))==null);
		} 

	}

	/**
	 * 
	 * Description Check whether the Element is displayed with expected conditions
	 * @author Sushmitha P H
	 * @param element elementName
	 */
	public synchronized boolean isElementClickable(WebElement element, String elementName) {

		info("Verify "+elementName+" is Clickable or Not");
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			fail(elementName + " is not Clickable ");
			return false;
		}
	}

	public void waitForElement(WebElement element, String eleName,long seconds) {
		try {

			wait= new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) != null);

		} catch (Exception e) {
			fail( "Element is not visible---------" + eleName);

		}
	}
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}
	public static void deleteFolderDir(File folderToDelete) {
		File[] folderContents = folderToDelete.listFiles();
		if (folderContents != null) {
			for (File folderfile : folderContents) {
				if (!Files.isSymbolicLink(folderfile.toPath())) {
					deleteFolderDir(folderfile);
				}
			}

		}
		folderToDelete.delete();
	}
	/**
	 * Description Capture the screenshot of the complete screen 
	 * @author Sushmitha P H
	 * @param path driver
	 */
	public static String getScreenShot(String path) {

		File src = (screenshot.getScreenshotAs(OutputType.FILE));
		String destinationPath = path + getCurrentDateTime() + ".png";
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationPath;
	}

	/**
	 * Description To Enter the Text to the Text filed 
	 * @author Sushmitha P H 
	 * @param element value elementName
	 */
	public synchronized void typeText(WebElement element, String value, String elementName) {
		try {
			info("Enter the value into " + elementName);
			element.sendKeys(value);
			pass("User is able to type " + value + " into " + elementName);
		} catch (AssertionError error) {
			fail(" User is not able to type " + value + " into " + elementName);
			Assert.fail("Unable to type on " + elementName);
		} catch (Exception e) {
			fail(" User is not able to type " + value + "into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		}
	}

	/**
	 * Description To Select the value by the Visible Text
	 * @author Sushmitha P H
	 * @param element value elementName
	 */
	public synchronized void selectByText(WebElement element, String value) {
		try {
			info("Select the value " + value);
			Select selecvalue=new Select(element);
			selecvalue.selectByVisibleText(value);
			pass("User is able to select the value"+value);
		} catch (Exception e) {
			fail(" User is not able to Select" + value );
			Assert.fail("Unable to select  " + value);
		}

	}
	public synchronized void switchWindowControl()
	{
		// It will return the parent window name as a String
		String mainWindow=driver.getWindowHandle();
		// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set =driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr= set.iterator();
		while(itr.hasNext()){
			String childWindow=itr.next();
		}
		// Compare whether the main windows is not equal to child window. If not equal, we will clo
	}
	/*
	 * Description Switches to tab
	 * 
	 * @author Ramya R
	 * @param tabindex
	 * 
	 */
	public synchronized void switchToTab(int tabindex) {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(tabindex));
			pass("Switch to tab complete");
		} catch (Exception e) {
			fail("Switching to tab failed ");

		}

	}
	/**
	 * Description Click on the check box
	 * @author Sushmitha P H
	 * @param element  elementName
	 */
	public void clickCheckBox(WebElement element, String elementname) {

		if (element.isSelected()) {
			pass("Already Selected " + elementname);
		} else {
			element.click();
			pass("Selected the " + elementname);

		}
	}
	/**
	 * @author Sushmitha P H
	 * @description Scroll to the Element
	 * @param elementName 
	 */
	public void scrollToElement(WebElement element, String elementName)  {
		info("-------------Scrolling till the Element------------");
		try {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			pass("-------------Scrolling till the Element Completed------------");
		} catch (Exception e) {
			fail("-------------Scroll Till the Element Has Failed ------------");
		}

	}
	/**
	 * Description Click on the Element using JavaSCript 
	 * @author Sushmitha P H
	 * @param element  elementName
	 */
	public void clickOnElementUsingJS(WebElement element, String elementName) {
		try {
			if (isElementClickable(element, elementName)) {
				pass("User is able to click " + " into " + elementName);
				jsExecutor.executeScript("arguments[0].click();", element);
			}
		} catch (NoSuchElementException e) {
			pass("User is not able to click " + " into " + elementName);
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) == null);
		}
	}

	/**
	 * Description Double Click On Element
	 * @author Sushmitha P H
	 * @param element  elementName
	 */
	public void doubleClickOnElement(WebElement element, String elementName) {
		try {
			pass("User is able to click " + " into " + elementName);
			action.doubleClick(element).perform();
		} catch (Exception e) {
			fail(" User is not able to double click on  " + elementName);
			Assert.fail("Unable to Double click on  " + elementName);
		}
	}


	/**
	 * Description Clear the Text
	 * @author Sushmitha P H
	 * @param element elementName
	 */
	public void clearText(WebElement element, String elementName) {
		try {
			info("Clear the Text Present in" + elementName);
			element.clear();
			pass("Cleared the Text Present in" + elementName);
		}catch (Exception e) {
			fail("Unable to clear the text in "+elementName);
		}
	}
	/**
	 * Description To create the duration of the Test Run
	 * @author Ramya R 
	 * @param element elementName
	 */
	public static String formatDuration(final long millis) {
		long seconds = (millis / 1000) % 60;
		long minutes = (millis / (1000 * 60)) % 60;
		long hours = millis / (1000 * 60 * 60);

		StringBuilder b = new StringBuilder();
		b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) :
			String.valueOf(hours));
		b.append(":");
		b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) :    
			String.valueOf(minutes));
		b.append(":");
		b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) :
			String.valueOf(seconds));
		return b.toString();
	}

	/**
	 * Description Wait for the angular page to load
	 * @author Ramya R
	 * @param element elementName
	 */
	public void waitForAngularPageLoad() {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
							"return (window.angular !== undefined) "
									+ "&& (angular.element(document).injector() !== undefined) "
									+ "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			fail("Unable to load the page correctly");
		}
	}

	/**
	 * Description Verifies  the Text
	 * 
	 * @author Ramya R
	 * @param expected
	 * @param element
	 * @param elementname
	 */
	public synchronized void verifytext(String expected, WebElement element, String elementname) {
		try {
			info("Getting text from " + elementname);
			String actual = element.getText();
			Assert.assertEquals(actual, expected);
		} catch (Exception e) {
			fail("Failed to fetch the text from " + elementname);
		}

	}


	/**
	 * Description :Checks whether an element is visible
	 * 
	 * @author Ramya R
	 * @param element
	 * @param elementName
	 * 
	 */
	public synchronized boolean isElementVisible(WebElement element, String elemantName) {

		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			pass(elemantName + " is Visible ");
			return true;
		} catch (Exception e) {
			return false;
		}
	}





	public void testDatePicker(String date) throws Exception {
		/*button to open calendar*/
		WebElement selectDate = driver.findElement(By.xpath("//span[@aria-controls='datetimepicker_dateview']"));
		selectDate.click();
		/*	 button to move next in calendar*/
		WebElement nextLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]"));
		/* button to click in center of calendar header*/
		WebElement midLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]"));
		/* button to move previous month in calendar*/
		WebElement previousLink = driver.findElement(By.xpath(
				"//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]"));
		// Split the date time to get only the date part
		String date_dd_MM_yyyy[] = (date.split(" ")[0]).split("/");
		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);
		midLink.click();
		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					System.out.println("Year Diff->" + i);
					nextLink.click();
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					System.out.println("Year Diff->" + i);
					previousLink.click();
				}
			}
		}

		Thread.sleep(1000);
		// Get all months from calendar to select correct one
		List<WebElement> list_AllMonthToBook = driver.findElements(By.xpath(
				"//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
		list_AllMonthToBook.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1).click();
		Thread.sleep(1000);
		// get all dates from calendar to select correct one
		List<WebElement> list_AllDateToBook = driver.findElements(By.xpath(
				"//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]"));
		list_AllDateToBook.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1).click();
		
		
	}
	/**
	 * Description :Switch the window Control
	 * 
	 * @author Manikandan A
	 * 
	 */

	public synchronized void switchWindowControlTo()
	{
		String parentWindow=driver.getWindowHandle();
		Set<String> set =driver.getWindowHandles();
		Iterator<String> itr= set.iterator();
		String parent = itr.next();
		String child1 = itr.next();
		String child2 = itr.next();

		driver.switchTo().window(child2);
	}


}

