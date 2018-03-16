package com.easytestall.pojo;

public class TextReduplicationPojo {
	private String name;
	private String amountBefore;//去重前文本的个数
	private String amount;//去重后文本的的个数
	
	
	
	public String getAmountBefore() {
		return amountBefore;
	}
	public void setAmountBefore(String amountBefore) {
		this.amountBefore = amountBefore;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public TextReduplicationPojo() {
		super();
	}
	public TextReduplicationPojo(String name, String amount,String amountBefore) {
		super();
		this.name = name;
		this.amount = amount;
		this.amountBefore = amountBefore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
