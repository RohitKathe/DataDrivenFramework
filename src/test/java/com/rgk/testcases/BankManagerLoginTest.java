package com.rgk.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rgk.base.Testbase;

public class BankManagerLoginTest extends Testbase {

	@Test
	public void bankManagerLoginTest() throws InterruptedException, IOException {
		log.debug("loginAsBankManager test case initiated !!");
		// verifyEquals("abc", "xyz");
		// Reporter.log("loginAsBankManager test case initiated !!");
		click("bmlBtn_CSS");
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "Login successful");
		log.debug("loginAsBankManager test case successfully completed!!");
		// Reporter.log("loginAsBankManager test case successfully
		// completed!!");
		// Assert.fail("login Not successful");
	}
}
