package com.zjd.jdkdemo.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelMain {
	public static void main(String[] arg) throws Exception{
		File file = new File("d:/r.txt");
		FileInputStream is = new FileInputStream(file);
		FileChannel inChannel = is.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(2);
		
		inChannel.read(buffer, 2);
		
		buffer.rewind();
		
		while (buffer.hasRemaining()) {
			System.out.println((char)buffer.get());
			
		}
	}
}
