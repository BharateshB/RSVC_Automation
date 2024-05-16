package com.tyss.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tyss.demo.baseutil.BaseTest;
import com.tyss.demo.util.WebActionUtil;
/**
 * Description This class has the implementations of the Login 
 * @author Ramya R
 */
public class Login_Page {
	
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Login_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Sign In Button */
	@FindBy(id = "loginButton")
	private WebElement btnSignIn;

	/* User Name Text Box */
	@FindBy(name = "username")
	private WebElement txtUsername;

	/* Password Text Box */
	@FindBy(name = "pwd")
	private WebElement txtPassword;
	
	/* Login Error Message*/
     @FindBy(xpath = "(//span[@class='errormsg'])[1]")
	 private WebElement txtErrorMessage;
     
     
 	/**
 	 * Description Method to Login to the application
 	 * @author Ramya R
 	 * @param username
 	 * @param password
 	 * 
 	 */
	public synchronized void loginToApplication(String username, String password) {
		try {
			WebActionUtil.info("Login To the App");
			WebActionUtil.typeText(txtUsername, username, "User Name Text Box");
			WebActionUtil.typeText(txtPassword, password, "Password Text Box");
			WebActionUtil.waitForElement(btnSignIn, " Dash Board Sign In Button", 20);
			WebActionUtil.clickOnWebElement(btnSignIn, "Sign In Button", "Unable To Click On Sign in Button");
			
			if (WebActionUtil.isElementVisible(txtErrorMessage, "Error message")==false) {
				WebActionUtil.info("Succesfully Logged In");
			} else {
				String errormsg=BaseTest.prop.getProperty("001");
				WebActionUtil.verifytext(errormsg, txtErrorMessage, "Error Message");
				Assert.fail();
			}

		} catch (Exception e) {
		
			WebActionUtil.error(e.getMessage());
			WebActionUtil.error("-Unable to sign in the Application");
			Assert.fail("Unable to signIn in the Login Page");
		}

	}

}
