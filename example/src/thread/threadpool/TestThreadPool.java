package thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//shutdown与shutdownNow的区别：
/*可以关闭 ExecutorService，这将导致其拒绝新任务。提供两个方法来关闭 ExecutorService。shutdown() 方法在终止前允许执行以前提交的任务，而 shutdownNow() 方法阻止等待任务启动并试图停止当前正在执行的任务。在终止时，执行程序没有任务在执行，也没有任务在等待执行，并且无法提交新任务。应该关闭未使用的 ExecutorService 以允许回收其资源。 

下列方法分两个阶段关闭 ExecutorService。第一阶段调用 shutdown 拒绝传入任务，然后调用 shutdownNow（如有必要）取消所有遗留的任务： 

   void shutdownAndAwaitTermination(ExecutorService pool) {  
     pool.shutdown(); // Disable new tasks from being submitted  
     try {  
       // Wait a while for existing tasks to terminate  
       if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {  
         pool.shutdownNow(); // Cancel currently executing tasks  
         // Wait a while for tasks to respond to being cancelled  
         if (!pool.awaitTermination(60, TimeUnit.SECONDS))  
             System.err.println("Pool did not terminate");  
       }  
     } catch (InterruptedException ie) {  
       // (Re-)Cancel if current thread also interrupted  
       pool.shutdownNow();  
       // Preserve interrupt status  
       Thread.currentThread().interrupt();  
     }  
   }  



shutdown调用后，不可以再submit新的task，已经submit的将继续执行。

shutdownNow试图停止当前正执行的task，并返回尚未执行的task的list*/


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
					//通过调用Future的get方法，可以获取线程的返回值，如果线程在执行过程中出现异常，此时可以获取线程中的异常。
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