package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import annotation.model.Student;

public class TestChangeValue {

	public static void main(String[] args) {
		Student student = new Student();
		student.setAge(10);
		student.setGender("1");
		student.setName("eclipse");
		Map<Object, Object> map = new TestChangeValue().simpleObject2Map(student);
		Iterator<Object> it = map.keySet().iterator();
		while(it.hasNext()){
			Object k = it.next();
			Object v = map.get(k);
			System.out.println(k + ":" + v);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Object,Object> simpleObject2Map(Object o){
		Class clazz = o.getClass();
		Field[] fields = clazz.getDeclaredFields();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for(Field field:fields){
			String fieldName = field.getName();
			column col = field.getAnnotation(column.class);
			if(col == null){
				continue;
			}
			String annotationName = col.name();
			if(annotationName == null || "".equals(annotationName.trim())){
				continue;
			}
			char[] c = fieldName.toCharArray();
			if(c[0]>='a' && c[0]<='z'){
				c[0] += 'A' - 'a';
			}
			String methodName = "get"+new String(c);
			Method method = null;
			Object v = null;
			try {
				method = clazz.getMethod(methodName, null);
				v = method.invoke(o, null);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			map.put(annotationName, v);
		}
		return map;
	}
}
