package com.zjd.jdkdemo.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class SelectorServer {

	private static SelectionKey key1;
	private static SelectionKey key2;
	private static ByteBuffer buff = ByteBuffer.allocate(64);
	
	private static int c=0;
	
	public static void main(String[] arg) throws Exception{
		ByteBuffer buff = ByteBuffer.allocate(64);
		
		 Selector selector = SelectorProvider.provider().openSelector();
		 
		 //第一个server
	     ServerSocketChannel serverChannel1 = ServerSocketChannel.open();
	     serverChannel1.configureBlocking(false);
	     serverChannel1.socket().bind(new InetSocketAddress("localhost", 10001));
	     key1 = serverChannel1.register(selector, serverChannel1.validOps());
	     
	     //第2个server
	     ServerSocketChannel serverChannel2 = ServerSocketChannel.open();
	     serverChannel2.configureBlocking(false);
	     serverChannel2.socket().bind(new InetSocketAddress("localhost", 10002));
	     key2 = serverChannel2.register(selector, SelectionKey.OP_ACCEPT);
         
	    while(true){
			selector.select();
	        Iterator selectedKeys = selector.selectedKeys().iterator();
	        while (selectedKeys.hasNext()) {
	            SelectionKey key = (SelectionKey) selectedKeys.next();
	            selectedKeys.remove();
	
	            if (!key.isValid()) {
	                continue;
	            }
	
	            if (key.isAcceptable()) {
	            	System.out.println("server accept...");
	            	if(serverChannel1.equals(key.channel())){
		            	System.out.println("注册server1 read事件");
		            	SocketChannel socketChannel1 = serverChannel1.accept();
		            	socketChannel1.configureBlocking(false);
	            		key1 = socketChannel1.register(selector, socketChannel1.validOps());
	            	}else if(serverChannel2.equals(key.channel())){
		            	System.out.println("注册server2 write事件");
		            	SocketChannel socketChannel2 = serverChannel2.accept();
		            	socketChannel2.configureBlocking(false);
	            		key2 = socketChannel2.register(selector, socketChannel2.validOps());
	            	}
	            } else if (key.isConnectable()) {
	            	System.out.println("server connect...");
	            } else if (key.isReadable()) {
	            	System.out.println("server read...");
	            	processServerRead(key);
	            } else if (key.isWritable()) {
//	            	System.out.println("server write...");
	            	processServerWrite(key);
	            }
	        }
	    }
	}
	
	private static void processServerRead(SelectionKey key) throws Exception{
		if(key1.equals(key)){
			System.out.println("process server1 Read .");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			channel.read(buff);
			buff.flip();
			while (buff.hasRemaining()) {
				System.out.println(buff.getChar());
			}
		}else if(key2.equals(key)){
			System.out.println("process server2 Read .");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			channel.read(buff);
			buff.flip();
			while (buff.hasRemaining()) {
				System.out.println(buff.getChar());
			}
		}
	}
	
	private static void processServerWrite(SelectionKey key) throws Exception{
		if(c>5)return;
		c++;
		if(key1.equals(key)){
			System.out.println("process server1 write .");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			buff.put(new String("server 111111111").getBytes("gbk"));
			buff.flip();
			channel.write(buff);
		}else if(key2.equals(key)){
			System.out.println("process server2 write .");
			SocketChannel channel = (SocketChannel)key.channel();
			buff.clear();
			buff.put("server 222222222".getBytes("gbk"));
			buff.flip();
			channel.write(buff);
		}
	}
		 
}
