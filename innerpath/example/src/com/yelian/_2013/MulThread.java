package com.yelian._2013;

import java.util.ArrayList;
import java.util.List;

public class MulThread {

	/**
	 * @param args
	 * @date 20131230-12:47:00
	 */
	
	public static List<String> list = new ArrayList<String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new MT()).run();
		new Thread(new MT1()).run();
	}

 }

class MT implements Runnable{

	@Override
	public void run() {
		while(true){
			if(MulThread.list.size() == 99){				
				break;
			} else {
				System.out.println(MulThread.list.size());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}	
}

class MT1 implements Runnable{

	@Override
	public void run() {
		for(int x=1; x<100; x++){
			MulThread.list.add(String.valueOf(x));
			System.out.println("in main method:"+x);
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("end");
	}	
}