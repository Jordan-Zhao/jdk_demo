package com.zjd.jdkdemo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MaxThread extends Thread{
	private static final AtomicInteger count = new AtomicInteger(); 
	private List<byte[]> data = new ArrayList<byte[]>();
	private static int len = 0;
	private static int c = 0;
	
    public static void main(String[] args) { 
    	len = Integer.parseInt(args[0]);
    	c = Integer.parseInt(args[1]);
    	int i = 0;
        while (i<c) {
            (new MaxThread()).start(); 
            System.out.println(count.incrementAndGet()); 
            i++;
        }
    } 
    @Override 
    public void run() { 
        while (true) 
            try { 
            	data.add(new byte[len]);
                Thread.sleep(Integer.MAX_VALUE); 
            } catch (InterruptedException e) { 
                break; 
            } 
    } 
}
