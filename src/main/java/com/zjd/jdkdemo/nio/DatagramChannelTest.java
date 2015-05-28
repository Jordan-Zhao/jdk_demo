package com.zjd.jdkdemo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramChannelTest {
	public static void main(String[] arg) throws Exception{
		DatagramChannel readChannel = DatagramChannel.open();
		readChannel.socket().bind(new InetSocketAddress(9999));
		

		DatagramChannel writeChannel = DatagramChannel.open();
		writeChannel.socket().bind(new InetSocketAddress(19999));
		writeChannel.socket().setSendBufferSize(1);
		
		//发送数据
//		String newData = "123";
//		ByteBuffer buf = ByteBuffer.allocate(48);
//		buf.clear();
//		buf.put(newData.getBytes());
//		buf.flip();
//		int bytesSent = writeChannel.send(buf, new InetSocketAddress("localhost", 9999));
		
		//模拟链接，但不会创建真正的连接，而是锁住DatagramChannel ，让其只能从特定地址收发数据。
		String s = "abc";
		ByteBuffer buf3 = ByteBuffer.allocate(48);
		buf3.clear();
		buf3.put(s.getBytes());
		buf3.flip();
		writeChannel.connect(new InetSocketAddress("localhost", 9999));
		writeChannel.write(buf3);
		//如果已经connect了一个address，再向另外一个address发数据则会报异常：
		//java.lang.IllegalArgumentException: Connected address not equal to target address
//		writeChannel.connect(new InetSocketAddress("jenkov.com", 80));
//		int bytesSent = writeChannel.send(buf3, new InetSocketAddress("localhost", 9999));
		
		//接收数据
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf2.clear();
		//receive()方法会将接收到的数据包内容复制到指定的Buffer. 如果Buffer容不下收到的数据，多出的数据将被丢弃。
		readChannel.receive(buf2);
		
		buf2.flip();
		while(buf2.hasRemaining()){
			System.out.print((char)buf2.get());
		}
		
		writeChannel.close();
		
	}
}
