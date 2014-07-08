package annotation.model;

import annotation.column;

public class Student {
	
	@column(name="student_name")
	private String name;
	
	@column(name="student_age")
	private int age;
	
	@column(name="student_gender")
	private String gender;
	
	private String love;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

}
