package com.zjd.jdkdemo;

public class Main {
	private int abc=100;
	private static int c=0;
	public static void main(String[] arg) throws Exception{
		while (true) {
			c++;
			Thread.sleep(1000*10);
		}
	}
}
