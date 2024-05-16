package com.tyss.actitime.scripts;

import org.testng.annotations.Test;

import com.tyss.demo.baseutil.BaseTest;
import com.tyss.demo.baseutil.InitializePages;
import com.tyss.demo.dataproviders.CreateUsersDataProvider;

public class CreateUsers extends BaseTest {
	@Test(description = "Create Users", dataProvider = "TestUsersDataProvider", dataProviderClass = CreateUsersDataProvider.class)

	public void createRequisition(String customerName,String projectName,String taskName) throws Exception {

		String username = prop.getProperty("UserName");
		String password = prop.getProperty("Password");

		InitializePages pages = new InitializePages(driver, ETO, WebActionUtil);
		pages.loginPage.loginToApplication(username, password);
		
		/* Click on User Tab */
		pages.usersPage.clkUsersTab();
		
		
		pages.usersPage.clklogoutButton();
		
	}
}
