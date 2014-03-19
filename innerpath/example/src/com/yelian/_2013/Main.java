package com.yelian._2013;

public class Main {  
  
    public static void main(String[] args) {  
        Sync sync = new Sync();  
        for (int i = 0; i < 3; i++) {  
            Thread thread = new MyThread(sync);  
            thread.start();  
        }  
    }  
}

class MyThread extends Thread {  
	  
    private Sync sync;  
  
    public MyThread(Sync sync) {  
        this.sync = sync;  
    }  
  
    public void run() {  
        sync.test();  
    }  
}  
  
class Sync {  
	  
    public synchronized void test() {  
        System.out.println("test¿ªÊ¼..");  
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        System.out.println("test½áÊø..");  
    }  
} 