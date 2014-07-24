package proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyMonitor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Class<?> clazz = null;
	public DynamicProxyMonitor(){
		
	}
	
	public DynamicProxyMonitor(Class<?> clazz){
		DynamicProxyMonitor.clazz = clazz;
	}
	
	public Object getProxyInstance(){
		return Proxy.newProxyInstance(DynamicProxyMonitor.clazz.getClassLoader(), 
				DynamicProxyMonitor.clazz.getInterfaces(), 
				new InvocationHandler(){
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				
				//System.out.println(proxy+" is a proxy object : "+Proxy.isProxyClass(proxy.getClass()));
				if("monitor".equals(method.getName())){
					long startTime = System.currentTimeMillis();
					System.out.println(startTime);
					Object o = DynamicProxyMonitor.clazz.getConstructor(null).newInstance(null);
					method.invoke(o, args);
					long endTime = System.currentTimeMillis();
					System.out.println(endTime + "\ntime cost:" + (endTime-startTime));
				}
				return null;
			};
		});
	}
}
