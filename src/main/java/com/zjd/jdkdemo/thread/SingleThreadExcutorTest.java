package com.zjd.jdkdemo.thread;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExcutorTest {
	public static void main(String[] arg) throws Exception {
		ExecutorService exs = Executors.newSingleThreadExecutor();

		Runnable runnable1 = new Runnable() {
			public void run() {
				try {
					Thread.currentThread().sleep(100);
					System.out.println("aaaaaaa" + Thread.currentThread().getId());
					Thread.currentThread().sleep(100);
				} catch (Exception e) {
				}
			}
		};
		exs.execute(runnable1);

		Runnable runnable2 = new Runnable() {
			public void run() {
				try {
					System.out.println("bbbbb" + Thread.currentThread().getId());
					Thread.currentThread().sleep(100);
				} catch (Exception e) {
				}
			}
		};
		exs.execute(runnable2);

		System.out.println("main exit");
	}
}
