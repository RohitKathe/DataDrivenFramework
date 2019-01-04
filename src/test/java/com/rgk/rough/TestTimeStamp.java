package com.rgk.rough;

import java.util.Date;

public class TestTimeStamp {

	public static void main(String[] args) {
		Date d = new Date();
		String screenshotName = d.toString().replaceFirst(" : ", "_").replace(" ", "_").replace(":", "_") + ".jpg";
		System.out.println(screenshotName);

	}
}
