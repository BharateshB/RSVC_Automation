package com.tyss.demo.baseutil;

import org.openqa.selenium.WebDriver;

import com.tyss.demo.pages.Login_Page;
import com.tyss.demo.pages.Users_Page;
import com.tyss.demo.util.WebActionUtil;

/**
 * Description Initialize all pages with driver,ETO, WebAactionUtil
 * 
 * @author Manikandan A
 * 
 */

public class InitializePages {
	public Login_Page loginPage;
	public Users_Page usersPage;

	public InitializePages(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {

		loginPage = new Login_Page(driver, ETO, WebActionUtil);
		usersPage = new Users_Page(driver, ETO, WebActionUtil);
	}

}
