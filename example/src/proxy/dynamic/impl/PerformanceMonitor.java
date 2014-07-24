package proxy.dynamic.impl;

import proxy.dynamic.inf.Monitor;

public class PerformanceMonitor implements Monitor{
	
	public void monitor(){
		String str = "";
		for(int x=0; x<500; x++){
			str += x;
		}
		System.out.println(str);
	}
}
