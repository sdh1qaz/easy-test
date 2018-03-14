package com.easytestall.util;

import java.util.List;


public class ArrayListUtil {
    //遍历打印list
	public static void traverseAndprint(List<String> list){
		//超级for循环遍历
		for(String attribute : list) {
		  System.out.println(attribute);
		}
	}
}
