package listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ObjectListener {
	
	public static void main(String[] args){
		TestPropertyChange tpc = new TestPropertyChange();
		tpc.setAge(12);
	}
}

class TestPropertyChange implements PropertyChangeListener{
	private int age = 0;
	private String name = "jk";
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
		this.name = name;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		System.out.println("property name: " + evt.getPropertyName() + "\tproperty old value: " + evt.getOldValue() + "\tproerty new value: " + evt.getNewValue());
	}
}
