package com.chinaroad.foundation.transfer.handler;

import java.nio.ByteBuffer;

import com.chinaroad.foundation.transfer.handler.Handler;
import com.chinaroad.foundation.transfer.session.IdleStatus;
import com.chinaroad.foundation.transfer.session.Session;

public class DemoClientHandler implements Handler {
	
	@Override
	public void sessionCreated(Session session) throws Exception {
		System.out.println("Create:" + session);
		//session.setIdleTime(IdleStatus.READ_IDLE, 1);
		//session.setIdleTime(IdleStatus.WRITE_IDLE, 1);
	}
	
	@Override
	public void sessionOpened(Session session) throws Exception {
		System.out.println("Open:" + session);
		
		// session.send("Fuck U!");
		String merchant = "666660";
		String uuid = "1-1-1-1";
		String parkingId = "川A12345";
		int idType = 1;
		
		String sign = "sign";
		
		String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" +
				"<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +
				"<soap:Body>" +
					"<GetParkingFee xmlns=\"http://tempuri.org/\">" +
						"<merchant>" + merchant + "</merchant>" +
						"<parkUuid>" + uuid + "</parkUuid>" +
						"<parkingId>" + parkingId + "</parkingId>" +
						"<idType>" + idType + "</idType>" +
						"<sign>" + sign + "</sign>" +
					"</GetParkingFee>" +
				"</soap:Body>" +
			"</soap:Envelope>";
		
		String request = 
				"POST /LuLuTong/ParkService.asmx HTTP/1.1\r\n" +
				"Host: 124.172.168.33\r\n" +
				"Content-Type: text/xml; charset=utf-8\r\n" +
				"Content-Length: " + content.getBytes().length + "\r\n" +
				"SOAPAction: \"http://tempuri.org/GetParkingFee\"\r\n\r\n" + content ;
				
		session.send(request);
		// 
	}

	@Override
	public void sessionIdle(Session session, IdleStatus status)
			throws Exception {
		System.out.println("Idle:" + status);
		session.send(ByteBuffer.wrap((System.currentTimeMillis() + "A").getBytes()).array());
		session.send(ByteBuffer.wrap((System.currentTimeMillis() + "B").getBytes()).array());
		session.send(ByteBuffer.wrap((System.currentTimeMillis() + "C").getBytes()).array());
	}

	@Override
	public void sessionClosed(Session session) throws Exception {
		System.out.println("Closed:" + session);
	}
	
	@Override
	public void exceptionCaught(Session session, Throwable cause) {
		System.out.println("Exception:" + cause);
		cause.printStackTrace();
	}

	@Override
	public void dataReceived(Session session, Object data) throws Exception {
	    String rec = new String((byte[]) data);
		System.out.println("Received:" + rec);
	}

	@Override
	public void dataNotSent(Session session, Object data) throws Exception {
		System.out.println("NotSent:" + new String((byte[]) data));
	}

	@Override
	public void dataSent(Session session, Object data) throws Exception {
		System.out.println("Sent:" + data);
	}
}