package com.tyss.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.demo.util.WebActionUtil;

/**
 * Description This class has the implementations of the Users related methods
 * 
 * @author Sushmita P H
 */
public class Users_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Users_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Users Tab */
	@FindBy(xpath = "//div[text()='Users']")
	private WebElement tabUsers;

	/* Users button */
	@FindBy(id = "ext-gen7")
	private WebElement btnUser;

	/* First Name textbox */
	@FindBy(id = "userDataLightBox_firstNameField")
	private WebElement txtFirstName;

	/* Last Name textbox */
	@FindBy(id = "userDataLightBox_lastNameField")
	private WebElement txtLastName;

	/* Email Address textbox */
	@FindBy(id = "userDataLightBox_emailField")
	private WebElement txtEmailAddress;

	/* Enter Time Track checkbox */
	@FindBy(id = "userDataLightBox_submitTimeTrackChBox")
	private WebElement cbEnterTimeTrack;

	/* Generate Time Reports checkbox */
	@FindBy(id = "userDataLightBox_generateTimeReportsChBox")
	private WebElement cbGenerateTimeReports;

	/* Has Access to Dropdown */
	@FindBy(id = "ext-gen270")
	private WebElement ddHasAccessTo;

	/* All Projects of All Customers Radio button */
	@FindBy(id = "ext-gen408")
	private WebElement rdbtnAllProjectsofAllCustomers;

	/* Close Button */
	@FindBy(id = "ext-gen495")
	private WebElement btnClose;

	/* Create User button */
	@FindBy(id = "userDataLightBox_commitBtn")
	private WebElement btnCreateUser;

	/* username */
	@FindBy(id = "logoutLink")
	private WebElement lnkLogout;

	/**
	 * Description Method to click on Logout Button
	 * 
	 * @author Manish Kumar C D
	 * 
	 */
	public synchronized void clklogoutButton() {

		WebActionUtil.clickOnWebElement(lnkLogout, "logout", "Unable to click on Logout link");

	}

	/**
	 * Description Method clicks on CreateUserbutton
	 * 
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkCreateUserButton() {
		WebActionUtil.waitForElement(btnCreateUser, "CreateUserbutton", 45);
		WebActionUtil.clickOnWebElement(btnCreateUser, "CreateUserbutton", "Unable to Click on CreateUserbutton");

	}

	/**
	 * Description Method clicks on Closebutton
	 * 
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkClosebutton() {
		WebActionUtil.waitForElement(btnClose, "Closebutton", 45);
		WebActionUtil.clickOnWebElement(btnClose, "Closebutton", "Unable to Click on Closebutton");

	}

	/**
	 * Description Method clicks on AllProjectsofAllCustomers Radiobutton
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkAllProjectsofAllCustomersRadiobutton() {
		WebActionUtil.waitForElement(rdbtnAllProjectsofAllCustomers, "AllProjectsofAllCustomers Radiobutton", 45);
		WebActionUtil.clickOnWebElement(rdbtnAllProjectsofAllCustomers, "AllProjectsofAllCustomers Radiobutton",
				"Click on AllProjectsofAllCustomers Radiobutton");

	}

	/**
	 * Description Method clicks on Has Access To Dropdown
	 * 
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkHasAccessToDropdown() {
		WebActionUtil.waitForElement(ddHasAccessTo, "Has AccessTo Dropdown", 45);
		WebActionUtil.clickOnWebElement(ddHasAccessTo, "Has AccessTo Dropdown", "Click on Has Access To Dropdown");

	}

	/**
	 * Description Method clicks on Enter Time Track Checkbox
	 * 
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkGenerateTimeReportsCheckbox() {
		WebActionUtil.waitForElement(cbGenerateTimeReports, "Generate TimeReports Checkbox", 45);
		WebActionUtil.clickCheckBox(cbGenerateTimeReports, "GenerateTimeReports Checkbox");

	}

	/**
	 * Description Method clicks on Enter Time Track Checkbox
	 * 
	 * @author Sushmita P H
	 * 
	 */
	public synchronized void clkEnterTimeTrackCheckbox() {
		WebActionUtil.waitForElement(cbEnterTimeTrack, "EnterTimeTrack Checkbox", 45);
		WebActionUtil.clickCheckBox(cbEnterTimeTrack, "EnterTimeTrack Checkbox");

	}

	/**
	 * Description Method clicks on Email Address TextBox
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void typeEmailAddressTextBox(String emailaddress) {
		WebActionUtil.waitForElement(txtEmailAddress, "EmailAddress TextBox", 45);
		WebActionUtil.typeText(txtEmailAddress, emailaddress, "unable to click on EmailAddress TextBox");

	}

	/**
	 * Description Method clicks on Last Name TextBox
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void typeLastNameTextBox(String lastname) {
		WebActionUtil.waitForElement(txtLastName, "Last Name TextBox", 45);
		WebActionUtil.typeText(txtLastName, lastname, "unable to click on Last Name TextBox");

	}

	/**
	 * Description Method clicks on First Name TextBox
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void typeFirstNameTextBox(String firstname) {
		WebActionUtil.waitForElement(txtFirstName, "First Name TextBox", 45);
		WebActionUtil.typeText(txtFirstName, firstname, "unable to click on FirstName TextBox");

	}

	/**
	 * Description Method clicks on User button
	 * 
	 * @author Ramya R
	 * 
	 */
	public synchronized void clkUsersbutton() {
		WebActionUtil.waitForElement(btnUser, "Users button", 45);
		WebActionUtil.clickOnElementUsingJS(btnUser, "Users button");

	}

	/**
	 * Description Method clicks on User Tab
	 * 
	 * @author Manish Kumar C D
	 * 
	 */
	public synchronized void clkUsersTab() {
		WebActionUtil.waitForElement(tabUsers, "Users Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabUsers, "Users Tab");

	}
}
