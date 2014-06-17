package thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreadPool {

	/**
	 * @param args
	 */
	private static ExecutorService es = Executors.newFixedThreadPool(4);
	public static void main(String[] args) {
		List<Task> list = new ArrayList<Task>();
		for(int x=0; x<5; x++){
			list.add(new Task(x));
		}
		List<Future<Result>> rs = null;
		try {
			rs = es.invokeAll(list);
			for(Future<Result> r:rs){
				try {
					Result res = r.get();
					System.out.println(res);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if(!es.isShutdown()){
				es.shutdown();
			}
		}
	}
}

class Task implements Callable<Result>{

	private int x;
	public Task(int x){
		this.x = x;
	} 
	
	@Override
	public Result call() throws Exception{
		if(x == 2){
			throw new Exception("something wrong!");
		} else {
			String name = Thread.currentThread().getName();
			int id = (int) Thread.currentThread().getId();
			System.out.println(name);
			return new Result(name, id);
		}
	}
}

class Result{
	public String thread_name;
	public int thread_id;
	public Result(){
		this.thread_name = null;
		this.thread_id = 0;
	}
	public Result(String name, int id){
		thread_name = name;
		thread_id = id;
	}
	public String getThread_name() {
		return thread_name;
	}
	public void setThread_name(String thread_name) {
		this.thread_name = thread_name;
	}
	public int getThread_id() {
		return thread_id;
	}
	public void setThread_id(int thread_id) {
		this.thread_id = thread_id;
	}
	
	@Override
	public String toString(){
		return thread_name + "\t" + thread_id;
	}
}