package com.zjd.jdkdemo.nio;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {
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
		aFile.close();
	}
}
