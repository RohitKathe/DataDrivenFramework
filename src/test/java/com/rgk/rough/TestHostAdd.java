package com.rgk.rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestHostAdd {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		
		String messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
				+ "8080/job/DataDrivenLiveProject/Extent_20Reports/";
		System.out.println(messageBody);
	}

}
