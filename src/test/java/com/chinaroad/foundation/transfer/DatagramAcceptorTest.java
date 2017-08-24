package com.chinaroad.foundation.transfer;

import org.junit.Test;

import com.chinaroad.foundation.transfer.handler.DemoServerHandler;

public class DatagramAcceptorTest {

	private String host = "0.0.0.0";
	private int port = 7001;
	
	// @Test
	public void test() throws Exception {
		DatagramAcceptor acceptor = new DatagramAcceptor(host, port);
		
		// sometimes, you should init before...
		acceptor.init();
		
		// Custom setting...
		acceptor.setSelectTimeout(100);
		acceptor.setSendBufferSize(1024);
		acceptor.setReceiveBufferSize(1024);
		
		// Inject the biz handler.
		acceptor.setHandler(new DemoServerHandler());
		
		// Start service
		// Thread will be blocked in here.
		acceptor.start();
	}
}
