package com.chinaroad.foundation.transfer;

import org.junit.Test;

import com.chinaroad.foundation.transfer.SocketConnector;
import com.chinaroad.foundation.transfer.handler.DemoClientHandler;

public class SocketConnectorTest {
	
	private String host = "124.172.168.33";
	private int port = 8081;

	// @Test
	public void test() throws Exception {
		SocketConnector connector = new SocketConnector();
		
		// sometimes, you should init before...
		connector.init();
		
		// Custom setting...
		connector.setSelectTimeout(10000);
		connector.setReceiveBufferSize(1024);
		connector.setSendBufferSize(1024);
		
		// Inject the biz handler.
		connector.setHandler(new DemoClientHandler());

		// Open connection to host:port
		// Thread will be blocked in here.
		connector.open(host, port);
	}

}
