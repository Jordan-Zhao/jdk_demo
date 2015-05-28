package com.zjd.jdkdemo.thread;

public class VolatileTest {
	static class Thread1 implements Runnable{
		private int age;
		
		public void run() {
			while (age==0) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("ttttttt111111==="+System.currentTimeMillis());
			}
		}
		
		public void setAge(int a){
			age = a;
			System.out.println("setttttttt"+System.currentTimeMillis());
		}
	}
	public static void main(String[] arg) throws InterruptedException{
		Thread1 thread1 = new Thread1();
		new Thread(thread1).start();
		Thread.sleep(10000);
		thread1.setAge(2);
		
	}
}
