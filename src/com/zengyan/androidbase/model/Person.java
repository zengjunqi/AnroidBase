package com.zengyan.androidbase.model;

public class Person {

	private  int id;
	private String name;
	private String phoneno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person(int id, String name, String phoneno) {
		super();
		this.id = id;
		this.name = name;
		this.phoneno = phoneno;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phoneno=" + phoneno
				+ "]";
	}
	
	
}
