package com.chinaroad.foundation.transfer.demo;

import com.chinaroad.foundation.transfer.DatagramConnector;
import com.chinaroad.foundation.transfer.handler.DemoClientHandler;

public class DatagramConnectorDemo {
	
	public static void main(String[] args) throws Exception {
		
		if (args.length < 2) {
			System.out.println("Usages:host port");
			System.exit(-1);
		}
		String host = null;
		int port = 0;
		try {
			host = args[0];
			port = Integer.parseInt(args[1]);
		} catch (Exception e) {
			System.out.println("Usages:host port");
			System.exit(-1);
		}
		System.out.println("Connecting /" + host + ":" + port);
		
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
