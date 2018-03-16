package com.easytestall.util;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName： operation
 * @Author: dhSu
 * @Description:
 * @Date:Created in 2018年3月7日
 */


public class TextOperation {
    
	//字符串數组去重
	public static List<String> quchong(String str){
		List<String> list = null;
		if(str==null ||"".equals(str)) 
			return list;
		String[] arr = str.split("\\s{1}|\\n{1}"); 
		list = StringComomnUtil.remDup(arr);
		Collections.sort(list);
		return list;
	}
	
	//去重钱文本个数（以一个或多个空格或者换行隔开）
	public static int getAmount(String text) {
		int count = 0;
		String[] arr = text.split("\\s{1}|\\n{1}"); 
		for(String string:arr) {
			if(!"".equals(string))
				count ++;
			else
				continue;
		}
		return count; 

	}
}
