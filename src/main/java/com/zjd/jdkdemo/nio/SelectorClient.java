package com.zjd.jdkdemo.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

import com.sun.jmx.remote.internal.ClientCommunicatorAdmin;

public class SelectorClient {
	private static SelectionKey key1;
	private static SelectionKey key2;
	private static ByteBuffer buff = ByteBuffer.allocate(64);
	private static int c=0;
	
	public static void main(String[] arg) throws Exception{
		Selector selector = SelectorProvider.provider().openSelector();
		
		//第一个套接字
		SocketChannel channel1 = SocketChannel.open();
		channel1.configureBlocking(false);
		channel1.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 10001));
		key1 = channel1.register(selector, channel1.validOps());
          
          
		//第2个套接字
		SocketChannel channel2 = SocketChannel.open();
		channel2.configureBlocking(false);
		channel2.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 10002));
		key2 = channel2.register(selector, channel2.validOps());
		
		//开始监听网络
		while (true) {
            try {
                selector.select();
                Iterator selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = (SelectionKey) selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isConnectable()) {
                    	 // 得到通道
              	      SocketChannel sChannel = (SocketChannel) key.channel();
              	      // 是否连接完毕？
              	      boolean success = sChannel.finishConnect();
              	      if (!success) {
              	        // 异常
              	        key.cancel();
              	      }
              	      
                    } else if (key.isReadable()) {
                    	System.out.println("client read...");
                    	processRead(key);
                    } else if (key.isWritable()) {
//                    	System.out.println("client write...");
                    	processWrite(key);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
	
	private static void processRead(SelectionKey key) throws Exception{
		if(key1.equals(key)){
			System.out.println("process channel1 read：");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			channel.read(buff);
			buff.flip();
			while (buff.hasRemaining()) {
				System.out.println(buff.getChar());
			}
		}else if(key2.equals(key)){
			System.out.println("process channel2 read：");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			channel.read(buff);
			buff.flip();
			while (buff.hasRemaining()) {
				System.out.println(buff.getChar());
			}
		}
	}
	
	private static void processWrite(SelectionKey key) throws Exception{
		if(c>3) return;
		c++;
		if(key1.equals(key)){
			System.out.println("process channel1 write：");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			buff.put("client 111111111".getBytes("utf8"));
			buff.flip();
			channel.write(buff);
		}else if(key2.equals(key)){
			System.out.println("process channel2 write：");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			buff.put("client 222222222".getBytes("utf8"));
			buff.flip();
			channel.write(buff);
		}
	}
		 
}
