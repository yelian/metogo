package thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

public class TestThreadLocal {

	private static Map<String, ThreadLocal<O>> map = new HashMap<String, ThreadLocal<O>>();
	public static void main(String[] args) {
		
		//方式1：
		ThreadLocal<O> threadLocal = new ThreadLocal<O>();
		
		/*
		 * threadLocal设置线程变量的时候，只有通过ThreadLocal的匿名内部类覆盖initialValue方法。
		 * 这样设置ThreadLocal变量值才可以被其余的线程得到。否则采用方式1的方法其余的线程无法获取线程局部变量值。
		 * */
		
		//方式2：
		/*ThreadLocal<O> threadLocal = new ThreadLocal<O>(){
			
			public O initialValue(){
				return new O();
			}
		};*/
		O o = new O();
		threadLocal.set(o);
		map.put("main", threadLocal);
		System.out.println(o.toString());
		
		/*测试的时候需要注意，需要创建多个线程thread，只有通过thread才可以看到设置效果。
		 * */
		for(int x=0; x<4; x++){
			new ThreadChangeValue(map).start();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadChangeValue extends Thread{
	
	private ThreadLocal<O> threadLocal;
	public ThreadChangeValue(Map<String, ThreadLocal<O>> map){
		threadLocal = map.get("main");
	}
	
	@Override
	public void run(){
		O o = this.threadLocal.get();
		System.out.println("--------------------thread start----------------------");
		if(o == null){
			System.out.println("i cannot get the value set main thread!");
			o = new O();
			threadLocal.set(o);
		} else {
			System.out.println(o.toString());
			o.set_int((int)Thread.currentThread().getId());
			o.set_String(Thread.currentThread().getName());
			threadLocal.set(o);
			System.out.println(o.toString());
		}
		System.out.println("--------------------thread end----------------------\n");
	}
}

class O{
	public int _int = 20140615;
	public String _String = "20140615";
	public int get_int() {
		return _int;
	}
	public void set_int(int _int) {
		this._int = _int;
	}
	public String get_String() {
		return _String;
	}
	public void set_String(String _String) {
		this._String = _String;
	}
	
	@Override
	public String toString(){
		return "String value is: " + _String + "\t int value is: " + _int;
	}
}