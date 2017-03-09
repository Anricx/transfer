package com.chinaroad.foundation.transfer.filter;

import org.junit.Test;

import com.chinaroad.foundation.transfer.filter.Filter;
import com.chinaroad.foundation.transfer.filter.FilterChain;
import com.chinaroad.foundation.transfer.handler.Handler;
import com.chinaroad.foundation.transfer.session.DatagramSession;
import com.chinaroad.foundation.transfer.session.IdleStatus;
import com.chinaroad.foundation.transfer.session.Session;

public class FilterChainTest {

	@Test
	public void test() {
		FilterChain filterChain = new FilterChain();
		filterChain.setHandler(new DatagramAcceptorHandler());
		Session session = new DatagramSession();
		Filter filter = new MyFilter();
		filterChain.addFirst("logger", filter);
		filterChain.addLast("logger2", filter);
		filterChain.addLast("logger3", filter);
		filterChain.replace("logger3", filter);
		filterChain.fireSessionCreated(session);
		filterChain.fireSessionOpened(session);
		filterChain.fireExceptionCaught(session, new Exception());
		filterChain.fireDataReceived(session, new Object());
		filterChain.fireDataSent(session, new Object());
		filterChain.fireSessionClosed(session);
	}
	
	static class DatagramAcceptorHandler implements Handler {

		@Override
		public void sessionCreated(Session session) {
			System.err.println("Created:" + session);
		}

		@Override
		public void sessionOpened(Session session) {
			System.err.println("Open:" + session);
		}

		@Override
		public void sessionIdle(Session session, IdleStatus status)
				throws Exception {
			System.err.println("Idle:" + status);
		}

		@Override
		public void sessionClosed(Session session) {
			System.err.println("Closed:" + session);
		}

		@Override
		public void exceptionCaught(Session session, Throwable cause) {
			System.err.println("exceptionCaught:" + cause);
			cause.printStackTrace();
		}

		@Override
		public void dataReceived(Session session, Object data) throws Exception {
			System.err.println("dataReceived:" + data);
		}

		@Override
		public void dataNotSent(Session session, Object data) throws Exception {
			System.err.println("dataNotSent:" + data);
		}

		@Override
		public void dataSent(Session session, Object data) throws Exception {
			System.err.println("dataSent:" + data);
			
		}
		
	}

}