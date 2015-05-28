package com.zjd.jdkdemo;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Runtime rt = Runtime.getRuntime();
    	rt.availableProcessors();
    	User u = new User();
    	u.name="a";
    	updateObject(u);
    	System.out.println(u.name);	//a
    	
    	BigDecimal bd = new BigDecimal(4.294967295E9);
    	System.out.println(bd.longValue());
    	
    }
    
    public static void updateObject(User u){
    	u = new User();
    	u.name = "b";
    }
    
    public static  class User{
    	public String name;
    }
}
