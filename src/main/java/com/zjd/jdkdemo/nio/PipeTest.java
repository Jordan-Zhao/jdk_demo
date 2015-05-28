package com.zjd.jdkdemo.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class PipeTest {
	public static void main(String[] arg) throws Exception{
		Pipe pipe = Pipe.open();
		//写
		Pipe.SinkChannel sinkChannel = pipe.sink();
		for(int i=0;i<100*1;i++){
			String data = "abc";
			ByteBuffer buf = ByteBuffer.allocate(48);
			buf.clear();
			buf.put(data.getBytes());
			buf.flip();
			while(buf.hasRemaining()){
				sinkChannel.write(buf);
			}
		}
		//读
		Pipe.SourceChannel sourceChannel = pipe.source();
		ByteBuffer buf2 = ByteBuffer.allocate(48);
		buf2.clear();
		int n = sourceChannel.read(buf2);
		buf2.flip();
		
		while(buf2.hasRemaining()){
			System.out.print((char)buf2.get());
		}
		
		
	}
}
