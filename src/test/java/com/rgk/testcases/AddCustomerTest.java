package com.rgk.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rgk.base.Testbase;
import com.rgk.utilities.TestUtil;

public class AddCustomerTest extends Testbase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException {
		if (!data.get("runmode").equalsIgnoreCase("Y")) {
			throw new SkipException("Skipping the test case as runmode for the data set is No");
		}
		click("addCustBtn_CSS");
		type("firstname_CSS", data.get("firstname"));
		type("lastname_CSS", data.get("lastname"));
		type("postcode_CSS", data.get("postcode"));
		click("addbtn_CSS");
		Thread.sleep(3000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		alert.accept();
		Reporter.log("Customer successfully added and alert text validated");
		// method to add screenshot link that opens in new tab after clicking on
		// hyperlink of screenshot in reprtng report
		// Reporter.log(
		// "<a target=\"_blank\"href=\"C:\\Users\\Rohit\\Pictures\\HD
		// Wallpapers\\screenshot.jpg\">Screenshot</a>");
		// // to add small thumbnail
		// Reporter.log("<br>");
		// Reporter.log(
		// "<a target=\"_blank\"href=\"C:\\Users\\Rohit\\Pictures\\HD
		// Wallpapers\\screenshot.jpg\"><img
		// src=\"C:\\Users\\Rohit\\Pictures\\HD Wallpapers\\screenshot.jpg\"
		// height=200 width=200></img></a>");
		// Assert.fail();

	}

	@DataProvider
	public Object[][] getData() {
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			for (int colNum = 0; colNum < cols; colNum++) {
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
			}

		}
		return data;

	}
}
