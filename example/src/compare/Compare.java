package compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


public class Compare {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<TargetObject> tos = new ArrayList<TargetObject>();
		for(int x=0; x<6; x++){
			TargetObject to = new TargetObject();
			to.setAge((int) (Math.random()*100));
			to.setName(UUID.randomUUID().toString());
			tos.add(to);
			System.out.println(to+"\n-----------------------------");
		}
		
		System.out.println("---------------beforcompare----------------");
		TargetObject[] toss = new TargetObject[tos.size()];
		tos.toArray(toss);
		Arrays.sort(toss, new Comparator<TargetObject>(){

			@Override
			public int compare(TargetObject o1, TargetObject o2) {
				return o1.getAge()>=o2.getAge()?1:0;
			}
			
		});
		
		for(TargetObject t:toss){
			System.out.println(t+"\n-----------------------------");
		}
		
	}

}

class TargetObject{
	
	private int age;
	private String name;
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
	
	public String toString(){
		return this.name + ":" + this.age;
	}
}