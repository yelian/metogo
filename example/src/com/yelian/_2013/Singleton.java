package com.yelian._2013;

public class Singleton {
	public static String A;
	public int I;
	private static Singleton s=null;
	private Singleton(){
		this("123456", 123456);
	}
	private Singleton(String a, int i){
		A = a;
		I = i;
	}
	
	public static Singleton getSingleton(){
		if(s == null){
			s = new Singleton();
		} 
		return s;
	}
}
