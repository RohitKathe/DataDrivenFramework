package com.rgk.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.rgk.base.Testbase;

public class TestUtil extends Testbase {
	private static final String name = null;
	public static String screenshotName;
	public static String screenshotPath; //// target//surefire-reports//html--->
											//// folder

	public static void captureScreenshot() throws IOException {
		Date d = new Date();
		screenshotName = d.toString().replaceFirst(" : ", "_").replace(" ", "_").replace(":", "_") + ".jpg";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// FileHandler.copy(scrFile, new File(System.getProperty("user.dir")
		// + "\\target\\surefire-reports\\" + screenshotName ));
		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		// org.openqa.selenium.io.FileHandler.copy(scrFile, new File(
		// System.getProperty("user.dir") + "\\target\\surefire-reports\\Data
		// Driven Basics\\" + screenshotName));
	}

	@DataProvider(name = "dp")
	public Object[][] getData(Method m) {
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] data = new Object[rows - 1][1];
		Hashtable<String, String> table = null;
		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			table = new Hashtable<String, String>();
			for (int colNum = 0; colNum < cols; colNum++) {
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}
		return data;

	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {
		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);
		for (int rNum = 2; rNum <= rows; rNum++) {
			String testCase = excel.getCellData(sheetName, "TCID", rNum);
			if (testCase.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				if (runmode.equalsIgnoreCase("Y")) {
					return true;
				} else
					return false;
			}
		}
		return false;

	}
}
