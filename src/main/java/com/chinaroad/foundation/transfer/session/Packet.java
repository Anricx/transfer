package com.chinaroad.foundation.transfer.session;

import java.nio.ByteBuffer;

/**
 * Pakcet
 * @author <a href="mailto:joe.dengtao@gmail.com">DengTao</a>
 * @version 1.0
 * @since 1.0
 */
public class Packet {

	private Object data;
	private ByteBuffer buf;
	
	public Packet(final Object data) {
		super();
		this.data = data;
		if (data instanceof byte[]) {
			buf = ByteBuffer.wrap((byte[]) data);
		} else if (data instanceof ByteBuffer) {
			buf = (ByteBuffer) data;
		} else if (data instanceof Packet) {
			buf = ((Packet) data).buf;
		} else {
			buf = ByteBuffer.wrap(data.toString().getBytes());
		}
	}

	public Object getData() {
		return data;
	}

	public ByteBuffer getBuf() {
		return buf;
	}
	
}
