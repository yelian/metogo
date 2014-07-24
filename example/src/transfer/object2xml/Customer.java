package transfer.object2xml;
import java.util.List;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class Customer {
 
	String name;
	int age;
	int id;
	List<HashMap<String, String>> hw;
	HashMap<String, String> HashMap;
 
	public String getName() {
		return name;
	}
 
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
 
	public int getAge() {
		return age;
	}
 
	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}
 
	public int getId() {
		return id;
	}
 
	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}
	
	//@XmlElement()
	public void setHw(List<HashMap<String, String>> h){
		this.hw = h;
	}
	
	public List<HashMap<String, String>> getHw(){
		return this.hw;
	}
	
	//@XmlElement()
	public void setHashMap(HashMap<String, String> h){
		this.HashMap = h;
	}
	
	public HashMap<String, String> getHashMap(){
		return this.HashMap;
	}
	
	@Override
	public String toString(){
		
		return "age:" + this.age + "\tid:"+this.id+"\tname:"+this.name;
	}
}