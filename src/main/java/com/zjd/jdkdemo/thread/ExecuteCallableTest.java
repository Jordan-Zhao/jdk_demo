package com.zjd.jdkdemo.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteCallableTest {
	public static void main(String[] args) {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
//			ExecutorService executor = Executors.newFixedThreadPool(2);
			
			class Inc1 implements Callable<String> {
				public String call() throws Exception {
					for (int i = 0; i < 2; i++) {
						System.out.println(Thread.currentThread().getId()+"::inc1:::"+System.currentTimeMillis());
						Thread.sleep(2*1000); // Catch IE from possible cancel
					}
					return "abc1111";
				}
			}
			
			class Inc2 implements Callable<String> {
				public String call() throws Exception {
					for (int i = 0; i < 3; i++) {
//					for (;;) {
						System.out.println(Thread.currentThread().getId()+"::inc2:::"+System.currentTimeMillis());
						Thread.sleep(2*1000); // Catch IE from possible cancel
					}
					return "abc222";
				}
			}
			List tasks = Arrays.asList(new Inc1(),new Inc2());
			List<Future<String>> futures = executor.invokeAll(tasks);
			System.out.println("--------------");
			for (Future<String> future : futures) {
				System.out.println(future.get());
			}

			executor.shutdown();

		} catch (Throwable t) {
		}

		System.out.println("main exit");
	}
}
