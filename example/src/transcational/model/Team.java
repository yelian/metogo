package transcational.model;

import java.util.Date;

import transcational.annotation.Column;
import transcational.annotation.Table;

@Table(name="team")
public class Team {

	@Column(name="id")
	private String id;
	
	@Column(name="employeetime")
	private Date employeeTime;
	
	@Column(name="workage")
	private int workage;
	
	@Column(name="name")
	private String name;
	
	@Column(name="count")
	private int count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getEmployeeTime() {
		return employeeTime;
	}

	public void setEmployeeTime(Date employeeTime) {
		this.employeeTime = employeeTime;
	}

	public int getWorkage() {
		return workage;
	}

	public void setWorkage(int workage) {
		this.workage = workage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
