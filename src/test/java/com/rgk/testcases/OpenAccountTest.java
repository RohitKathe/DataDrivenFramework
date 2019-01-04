package com.rgk.testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.TestException;
import org.testng.annotations.Test;

import com.rgk.base.Testbase;
import com.rgk.utilities.TestUtil;

public class OpenAccountTest extends Testbase {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException {

		if (!TestUtil.isTestRunnable("OpenAccountTest", excel)) {
			throw new TestException(
					"Skipping the test" + "OpenAccountTest".toUpperCase() + "as the runmode for test is set to No");
		}
		click("openAccount_CSS");
		select("customer_CSS", data.get("customer"));
		select("currency_CSS", data.get("currency"));
		click("process_CSS");
		Thread.sleep(3000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
	}
}