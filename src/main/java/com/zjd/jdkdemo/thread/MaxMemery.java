package com.zjd.jdkdemo.thread;

import java.util.ArrayList;
import java.util.List;

public class MaxMemery {
	public static void main(String[] arg){
		List<byte[]> data = new ArrayList<byte[]>();
		long c=0;
		while (true) {
			try {
				data.add(new byte[1024*1024]);
				System.out.println("c="+c);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("result: c="+c);
			}
			c++;
		}
	}
}
