package com.zjd.jdkdemo;

import java.lang.reflect.Method;

public class IntegerTest {
	public static void main(String[] arg){
		Integer m = 200;
		Integer n = 200;
		System.out.println(m == n);
		System.out.println(m.equals(n));

		Integer i = 100;
		Integer j = 100;
		System.out.println(i == j);
		
		System.out.println(i.equals(j));
		
		
		String s1 = "abc";
		String s2 = "abc";
		System.out.println(s1==s2);
		
		String s3 = new String("abc");
		System.out.println(s1==s3);
		
		System.out.println(s1==s3.intern());
		
		String[] arr = new String[3];
		arr[0] = s1;
		arr[1] = s3;
		arr[2] = s3.intern();
		System.out.println(arr);
		
		int a = 2147483647;
		System.out.println(a);
		
	}
}
