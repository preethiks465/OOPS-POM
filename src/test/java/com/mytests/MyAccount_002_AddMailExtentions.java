package com.mytests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mypages.AdminPage;
import com.mypages.HomePage;
import com.mypages.LoginPage;
import com.mypages.MyAccountPage;

public class MyAccount_002_AddMailExtentions extends BaseTest {

	List<String> ColumnDetails = new ArrayList<String>();
	String SheetName = "MyAccountDetails";
	String CurrentTestCase = this.getClass().getName();

	@Test(dataProvider = "ARData")
	public void verify_MyAccount_002_AddMailExtentions_Test(String ExtensionName, String ExtensionDesc,
			String NotificationMsg) {

		HomePage homepage = page.getInstance(LoginPage.class).doLogin(page.prop.getProperty("username"),
				page.prop.getProperty("password"));
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		AdminPage adminPage = homepage.clickOnAdminMenu();
		MyAccountPage myAccountPage = adminPage.clickOnMyAccountLink();
		myAccountPage.AddMailExtensions(ExtensionName, ExtensionDesc);
		Assert.assertTrue(page.testutil.verifyData(NotificationMsg));
		page.testutil.ClickonOKButton();

	}

	@DataProvider(name = "ARData")
	public Object[][] getARTestData() {
		ColumnDetails.add("ExtensionName");
		ColumnDetails.add("ExtensionDesc");
		ColumnDetails.add("NotificationMsg");
		return page.testutil.getdata(SheetName, CurrentTestCase, ColumnDetails);
	}
}
