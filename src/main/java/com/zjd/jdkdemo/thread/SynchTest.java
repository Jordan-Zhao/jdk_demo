package com.zjd.jdkdemo.thread;

public class SynchTest {
	public synchronized void m1(){
		synchronized (SynchTest.class) {
			System.out.println("m1 synch");
			try{
				Thread.sleep(1000*20);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
		
	public synchronized static void m2(){
		System.out.println("m2 synch");
		try{
			Thread.sleep(1000*20);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg){
		final SynchTest st = new SynchTest();
		
		Runnable r1 = new Runnable() {
			@Override
			public void run() {
				st.m1();
			}
		};
		
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				st.m2();
			}
		};
		
		new Thread(r1).start();
		new Thread(r2).start();
	}
}
