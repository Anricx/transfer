package com.chinaroad.foundation.transfer.handler;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.chinaroad.foundation.transfer.HexUtils;
import com.chinaroad.foundation.transfer.session.IdleStatus;
import com.chinaroad.foundation.transfer.session.Session;

public class DemoServerHandler implements Handler {
	
	@Override
	public void sessionCreated(Session session) throws Exception {
		System.out.println("Created:" + session);
	}
	
	@Override
	public void sessionOpened(Session session) throws Exception {
		System.out.println("Opened:" + session);
		// session.send(". HELO Welcome to 007ka's MsgSrv\n".getBytes());
		//session.setIdleTime(IdleStatus.READ_IDLE, 5000);
		//session.setIdleTime(IdleStatus.WRITE_IDLE, 10);
	}

	@Override
	public void sessionIdle(Session session, IdleStatus status)
			throws Exception {
		session.close();
	    //System.out.println("Idle:" + status + ", " + System.currentTimeMillis());
	}

	@Override
	public void sessionClosed(Session session) throws Exception {
		System.out.println("Closed:" + session);
	}
	
	@Override
	public void exceptionCaught(Session session, Throwable cause) {
		cause.printStackTrace();
	}

	@Override
	public void dataReceived(Session session, Object data) throws Exception {
		System.out.println(new String((byte[]) data));
		// String payload = new String((byte[]) data);
		
		String payload = HexUtils.bytesToHEX((byte[]) data);
		// System.out.println("Payload:\n" + payload + "");

		if (payload.startsWith("AA0781")) {
			/*
			ByteBuffer buf = ByteBuffer.allocate(4).putInt((int) (System.currentTimeMillis() / 1000));
			buf.flip();
			
			byte[] dst = new byte[4];
			buf.get(dst);
			// session.send(("55070020" + HexUtils.bytesToHEX(dst)).getBytes());
			session.send(HexUtils.hexToBytes("5507002081" + HexUtils.bytesToHEX(dst)));
			*/
			byte[] parameters = HexUtils.hexToBytes(payload.substring(6));
			byte type = 0x55;
			short len = (short) (5 + parameters.length);
			byte cmd = 0x20;
			ByteBuffer buf = ByteBuffer.allocate(1024).order(ByteOrder.LITTLE_ENDIAN);
			buf.put(type);
			buf.putShort(len);
			buf.put(cmd);
			buf.put((byte) 0x81);
			buf.put(parameters);
			buf.flip();
			
			byte[] bts = new byte[len];
			buf.get(bts);
			
			// session.send(bts);
		}
		
		/*
		ByteBuffer buffer = ByteBuffer.wrap((byte[]) data).order(ByteOrder.LITTLE_ENDIAN);
		
		byte[] header = new byte[2];
		buffer.get(header);
		short length = buffer.get();
		short number = buffer.getShort();
		byte[] sender = new byte[4];
		buffer.get(sender);
		byte[] receiver = new byte[4];
		buffer.get(receiver);
		byte control = buffer.get();
		byte[] payload = new byte[length];
		buffer.get(payload);
		short crc = buffer.getShort();
		byte tail = buffer.get();

		// int length = buffer.get();
		// int length = buffer.get();
		System.out.println("Header:" + HexUtils.bytesToHEX(header) + "");
		System.out.println("Length: " + length + "");
		System.out.println("Number:" + number + "");
		System.out.println("Sender:" + HexUtils.bytesToHEX(sender) + "");
		System.out.println("Receiver:" + HexUtils.bytesToHEX(receiver) + "");
		System.out.println("Control:" + control + "");
		System.out.println("CRC:" + crc + "");
		System.out.println("Payload:\n" + HexUtils.bytesToHEX(payload) + "");
		System.out.println("Tail:" + HexUtils.bytesToHEX(new byte[] { tail }) + "\n");
		//session.send(data);*/
	}

	@Override
	public void dataNotSent(Session session, Object data) throws Exception {
		System.out.println("NotSent:" + new String((byte[]) data));
	}

	@Override
	public void dataSent(Session session, Object data) throws Exception {
		System.out.println("Sent:" + new String((byte[]) data));
	}
	
}