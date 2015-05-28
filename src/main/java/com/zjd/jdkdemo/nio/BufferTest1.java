package com.zjd.jdkdemo.nio;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest1 {
	public static void main(String[] arg) throws Exception {
		//源文件
		RandomAccessFile aFile = new RandomAccessFile("d:/a.txt", "rw");
		FileChannel inChannel = aFile.getChannel();
		
		//目的文件
		RandomAccessFile aFile2 = new RandomAccessFile("d:/b.txt", "rw");
		FileChannel inChannel2 = aFile2.getChannel();

		// create buffer with capacity of 48 bytes
		ByteBuffer buf = ByteBuffer.allocate(48);	//缓存buffer

		int bytesRead = inChannel.read(buf); // read into buffer.
		while (bytesRead != -1) {
			/*
			注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
			*/
			buf.flip(); // make buffer ready for read
			
			inChannel2.write(buf);	//写入目的文件

			buf.clear(); // make buffer ready for writing
			bytesRead = inChannel.read(buf);
		}
		aFile.close();
	}
}
