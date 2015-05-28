package com.zjd.jdkdemo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadExcutorTest {
	public static void main(String[] arg) throws Exception {
		ExecutorService exs = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 5; i++) {
			Runnable runnable1 = new Runnable() {
				public void run() {
					try {
						System.out.println("thread-" + Thread.currentThread().getId());
						Thread.currentThread().sleep(10 * 1000);
					} catch (Exception e) {
					}
				}
			};
			exs.execute(runnable1);
		}

//		System.gc();
//		System.runFinalization();
//		Thread.sleep(10);
//		System.gc();
//		System.runFinalization();
//		Thread.sleep(10);

		System.out.println("main exit");
	}
}
