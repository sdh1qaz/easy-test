package com.easytestall.demo;

public class Person {
	private String name;
	private String amount;//去重后字符串的的个数
	
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Person() {
		super();
	}
	public Person(String name, String amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
