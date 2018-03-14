package com.easytestall.util;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName： operation
 * @Author: dhSu
 * @Description:
 * @Date:Created in 2018年3月7日
 */


public class Operation {
    
	//字符串數组去重
	public static List<String> quchong(String str){
		List<String> list = null;
		if(str==null ||"".equals(str)) 
			return list;
		String[] arr = str.split("\n"); 
		list = StringComomnUtil.remDup(arr);
		Collections.sort(list);
		return list;
	}
}
