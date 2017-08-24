package com.chinaroad.foundation.transfer;

import org.junit.Test;

import com.chinaroad.foundation.transfer.DatagramConnector;
import com.chinaroad.foundation.transfer.handler.DemoClientHandler;

public class DatagramConnectorTest {
	
	private String host = "119.2.5.45";
	private int port = 7000;
	
	// @Test
	public void test() throws Exception {
		DatagramConnector connector = new DatagramConnector();
		
		// sometimes, you should init before...
		connector.init();
		
		// Custom setting...
		connector.setSelectTimeout(100);
		connector.setReceiveBufferSize(1024);
		connector.setSendBufferSize(1024);
		
		// Inject the biz handler.
		connector.setHandler(new DemoClientHandler());
		
		// Open connection to host:port
		// Thread will be blocked in here.
		connector.open(host, port);
	}
}
