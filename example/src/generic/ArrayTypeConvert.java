package generic;

import generic.bean.ExmpBean;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ArrayTypeConvert {

	@Test
	public void test() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<String> l1 = new ArrayList<String>();
		l1.add("123");
		l1.add("www");
		List<String> l2 = new ArrayList<String>();
		l2.add("456");
		l2.add("ttt");
		Object[][] obj = new Object[2][1];
		obj[0][0] = l1;
		obj[1][0] = l2;
		
		Class<?> cls = ExmpBean.class;
		for(Field field: cls.getDeclaredFields()){
			Type clazz = field.getGenericType();
			convert(clazz, obj);
		}
	}
	
	public void convert(Type type, Object obj) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> clazz = null;
		while(true){
			if(type instanceof GenericArrayType){
				type = ((GenericArrayType) type).getGenericComponentType();
			} else if (type instanceof ParameterizedType){
				clazz = (Class<?>)((ParameterizedType) type).getRawType();
				break;
			}
		}
		System.out.println(clazz.toString());
		
		List<Integer> l = new ArrayList<Integer>();
		Object t = obj;
		while(true){
			Class<?> cls = obj.getClass();
			if(cls.isArray()){
				l.add(Array.getLength(obj));
				obj = Array.get(obj, 0);
			} else {
				break;
			}			
		}
		int[] dim = new int[l.size()];
		for(int x=0; x<l.size(); x++){
			dim[x] = l.get(x);
		}
		
		Object o = Array.newInstance(clazz, dim);
		
		for(int x=0; x<dim[0]; x++){
			Object inner = Array.get(o, x);
			for(int y=0; y<dim[1]; y++){
				Array.set(inner, y, Array.get(Array.get(t, x), y));
			}
		}
		for(int x=0; x<dim.length; x++){
			Object inner = Array.get(o, x);
			for(int y=0; y<dim[x]; y++){
				Array.set(inner, y, Array.get(Array.get(t, x), y));
			}
		}
		ExmpBean bean = ExmpBean.class.newInstance();
		Method[] methods = ExmpBean.class.getMethods();
		for(Method method: methods){
			if(method.getName().equals("setNames")){
				method.invoke(bean, o);
			}
		}
		
		System.out.println(o);
	}	
}
