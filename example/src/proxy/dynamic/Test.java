package proxy.dynamic;


import proxy.dynamic.impl.PerformanceMonitor;
import proxy.dynamic.inf.Monitor;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//no dynamic proxy
//		Monitor noProxyMonitor = new PerformanceMonitor();
//		noProxyMonitor.monitor();
		
		Monitor proxyMonitor = (Monitor) new DynamicProxyMonitor(PerformanceMonitor.class).getProxyInstance();
		proxyMonitor.monitor();
	}

}
