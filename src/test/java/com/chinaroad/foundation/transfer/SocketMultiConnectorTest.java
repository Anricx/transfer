package com.chinaroad.foundation.transfer;

import org.junit.Test;

import com.chinaroad.foundation.transfer.SocketMultiConnector;
import com.chinaroad.foundation.transfer.handler.DemoClientHandler;

public class SocketMultiConnectorTest {

	// @Test
	public void test() throws Exception {
		final SocketMultiConnector connector = new SocketMultiConnector();
		
		// sometimes, you should init before...
		connector.init();
		
		// Custom setting...
		connector.setSelectTimeout(100);
		connector.setReceiveBufferSize(1024);
		connector.setSendBufferSize(1024);

		// Inject the biz handler.
		connector.setHandler(new DemoClientHandler());
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					connector.connect();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}).start();
		
		for (int i = 0; i < 10000; i++) {
			connector.open("123.57.209.86", 9080);
			//Thread.sleep(3000);
		}
		
		for(;;) { try { Thread.sleep(10000); } catch (InterruptedException e) { } }
	}

}
