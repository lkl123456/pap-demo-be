package com.yonyou.base.java8.model;

import java.util.List;

public class OutstandingClass {
	private String name;
	private List<Student> students;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public OutstandingClass(String name, List<Student> students) {
		super();
		this.name = name;
		this.students = students;
	}
	public OutstandingClass() {
		super();
	}
	
}
