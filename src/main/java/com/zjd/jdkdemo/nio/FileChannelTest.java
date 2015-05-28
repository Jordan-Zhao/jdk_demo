package com.zjd.jdkdemo.nio;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
	public static void main(String[] arg) throws Exception {
		RandomAccessFile aFile = new RandomAccessFile("d:/a.txt", "rw");
		FileChannel inChannel = aFile.getChannel();

		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);

		int bytesRead = inChannel.read(buf); // read into buffer.
		while (bytesRead != -1) {
			/*
			注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
			*/
			buf.flip(); // make buffer ready for read

			while (buf.hasRemaining()) {
				System.out.print((char) buf.get()); // read 1 byte at a time
			}

			buf.clear(); // make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		
		String data = "after before this is write data.";
		buf.clear();
		buf.put(data.getBytes());
		buf.flip();
		inChannel.position(500);	//在500位置写入数据
		while (buf.hasRemaining()) {
			inChannel.write(buf);
		}
		
		//查看空洞数据
		inChannel.position(400);
		buf.clear();
		int r = inChannel.read(buf);
		buf.flip();
		if(r != -1){
			while(buf.hasRemaining()){
				System.out.print(buf.get());
			}
		}
		
		//截取
		inChannel.truncate(20);
		buf.clear();
		int r1 = inChannel.read(buf);
		buf.flip();
		if(r1 != -1){
			while(buf.hasRemaining()){
				System.out.print((char)buf.get());
			}
		}
		
		inChannel.force(true);

		
		inChannel.close();
		aFile.close();
	}
}
