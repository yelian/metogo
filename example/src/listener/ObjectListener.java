package listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ObjectListener {
	
	public static void main(String[] args){
		//TestPropertyChange tpc = new TestPropertyChange();
		//tpc.setName("3333");
		
		/*TestPropertyChange t = (TestPropertyChange) Enhancer.create(TestPropertyChange.class, new MethodInterceptor(){

			@Override
			public Object intercept(Object arg0, Method arg1, Object[] arg2,
					MethodProxy arg3) throws Throwable {
				
				String methodName = arg1.getName();
				if()
				
				System.out.println(arg1.getName());
				System.out.println(arg3.getSuperName() + "||");
				if(arg1.getName().startsWith("set")){
										
				}
				arg3.invokeSuper(arg0, arg2);
				return null;
			}
			
		});*/
		Model model = SimpleObjectFactory.getInstance(Model.class);
		model.setNA("NA_V");
		model.setCorp("ddd");
		System.out.println(model.getNA());
	}
}

class SimpleObjectFactory{
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(final Class<T> clazz){
		return (T) Enhancer.create(clazz, new MethodInterceptor(){
			@Override
			public Object intercept(Object arg0, Method arg1, Object[] arg2,
					MethodProxy arg3) throws Throwable {
				
				String methodName = arg1.getName();
				if(methodName.startsWith("set")){
					PropertyChangeSupport support = new PropertyChangeSupport(arg0);
					support.addPropertyChangeListener(new PropertyChangeListener(){
						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							System.out.println("property name: " + evt.getPropertyName() + "\tproperty old value: " + evt.getOldValue() + "\tproerty new value: " + evt.getNewValue());
						}
					});
					char[] chars = arg1.getName().toCharArray();
					chars[0] = 'g';
					Method getMethod = clazz.getMethod(new String(chars), new Class<?>[]{});
					support.firePropertyChange(methodName, getMethod.invoke(arg0, new Object[]{}), arg2);
				}
				
				System.out.println(arg1.getName());
				System.out.println(arg3.getSuperName() + "||");
				return arg3.invokeSuper(arg0, arg2);
			}
			
		});
	}	
}

class Model{
	private String corp = "corp_0";
	private String NA = "NA_0";
	public String getCorp() {
		return corp;
	}
	public void setCorp(String corp) {
		this.corp = corp;
	}
	public String getNA() {
		return NA;
	}
	public void setNA(String nA) {
		NA = nA;
	}
}

class PropertyChangeHandler{
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	public PropertyChangeHandler(){
		support.addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("property name: " + evt.getPropertyName() + "\tproperty old value: " + evt.getOldValue() + "\tproerty new value: " + evt.getNewValue());
			}
		});
	}
}
/*
class TestPropertyChange{
	
	PropertyChangeSupport support = new PropertyChangeSupport(this);
	private int age = 0;
	private String name = "jk";
	public TestPropertyChange(){
		this.support.addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.println("property name: " + evt.getPropertyName() + "\tproperty old value: " + evt.getOldValue() + "\tproerty new value: " + evt.getNewValue());
			}
		});
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		String oldVal = this.name;
		this.name = name;
		support.firePropertyChange("name", oldVal , this.name);
	}
}*/
