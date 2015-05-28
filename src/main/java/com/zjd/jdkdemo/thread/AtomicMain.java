package com.zjd.jdkdemo.thread;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicMain {
	private static AtomicLong counter = new AtomicLong(0);
	
	public static void main(String[] arg) throws Exception{
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Runnable() {
				
				public void run() {
					counter.incrementAndGet();
					try {
						Thread.currentThread().sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
			t.start();
			System.out.println("thread:"+t.getId());
		}
		
		Thread.currentThread().sleep(1000);
		System.out.println(counter.get());
	}
	

}
