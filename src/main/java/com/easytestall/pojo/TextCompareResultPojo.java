package com.easytestall.pojo;
/**
 * @ClassName： TextCompareResult
 * @Author: dhSu
 * @Description: 左右文本域比较结果
 * @Date:Created in 2018年5月30日
 */

public class TextCompareResultPojo {
    private String textLeft;//左边有右边没有的文本
    private String textRight;//左边无右边有的文本
    private int number;//前两个文本中字符串的个数
    
    public TextCompareResultPojo(String textLeft,String textRight,int number) {
    	this.textLeft = textLeft;
    	this.textRight = textRight;
    	this.number = number;
    }
	public String getTextLeft() {
		return textLeft;
	}
	public void setTextLeft(String textLeft) {
		this.textLeft = textLeft;
	}
	public String getTextRight() {
		return textRight;
	}
	public void setTextRight(String textRight) {
		this.textRight = textRight;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
    
    
}
