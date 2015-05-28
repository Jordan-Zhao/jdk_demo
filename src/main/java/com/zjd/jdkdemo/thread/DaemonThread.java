package com.zjd.jdkdemo.thread;

import java.io.IOException;

public class DaemonThread {
	static  byte[] data = new byte[1];
	static  boolean b = false;
	
	public static void main(String[] arg) throws Exception{
		
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					if(b){
						System.out.println(new String(data)+"=========");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		t.setDaemon(true);
		t.start();
		
		System.in.read(data);
		b= true;
	}
}
