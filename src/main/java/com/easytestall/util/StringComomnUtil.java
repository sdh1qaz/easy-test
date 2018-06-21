package com.easytestall.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




/**
 * @ClassName： StringComomnUtil
 * @Author: dhSu
 * @Description: 字符串/字符串数组工具类
 * @Date:Created in 2018年2月25日
 */

public class StringComomnUtil {
	
	/**  
	* @Description: TODO(字符串数组遍历打印)  
	* @param  String[]
	* @return String[]
	* @throws  
	*/ 
	public static void traverseAndprint(String[] str){
		  //方法1
          /*for (int i = 0; i < str.length; i++) {
              System.out.println(str[i]);
          }*/
          //方法2
          for (String string : str) {
              System.out.println(string);
          }
	}
    /**  
    * @Description: TODO(对字符串数组进行排序)  
    * @param  String[]
    * @return String[]
    * @throws  
    */ 
    public static String[] bubbleSort(String[] keys){
        
        for (int i = 0; i < keys.length - 1; i++) {
            for (int j = 0; j < keys.length - i -1; j++) {
                String pre = keys[j];
                String next = keys[j + 1];
                if(isMoreThan(pre, next)){
                    String temp = pre;
                    keys[j] = next;
                    keys[j+1] = temp;
                }
            }
        }
        return keys;
    }
	
    
    /**  
    * @Description: TODO(比较两个字符串的大小，按字母的unicode码比较)  
    * @param  String ，String
    * @return boolean
    * @throws  
    */ 
    private static boolean isMoreThan(String pre, String next){
        if(null == pre || null == next || "".equals(pre) || "".equals(next)){
            return false;
        }
        
        char[] c_pre = pre.toCharArray();
        char[] c_next = next.toCharArray();
        
        int minSize = Math.min(c_pre.length, c_next.length);
        
        for (int i = 0; i < minSize; i++) {
            if((int)c_pre[i] > (int)c_next[i]){
                return true;
            }else if((int)c_pre[i] < (int)c_next[i]){
                return false;
            }
        }
        if(c_pre.length > c_next.length){
            return true;
        }
        
        return false;
    }
   
    /**  
    * @Description: TODO(字符串数组A和字符串数组B比较，返回A有B没有的字符串列表)  
    * @param  String[]
    * @return  String[]
    * @throws  
    */ 
    public static List<String> getStrA(String strA,String strB ){
    	List<String> listA = TextOperation.quchong(strA);//字符串去重
    	List<String> listB = TextOperation.quchong(strB);
    	List<String> list = new ArrayList<String>();
    	for(int i=0;i<listA.size();i++){
    		if(!listB.contains(listA.get(i)))
    			list.add(listA.get(i));
    		/*else {
				System.out.println("两个字符串数组都有   " + strA[i]);
			}*/
    	}
    	/*if(list.size() > 0){
    		ArrayListUtil.traverseAndprint(list);
    		System.out.println("前者有后者没有： " + list.size());
    	}
    	else {
			System.out.println("后面的数组包含前一个数组************************");//A有的B全有
		}*/
    	return list;
    	
    }
    
   
  	/**  
  	* @Description: TODO(字符串数组去掉重复的字符串)  
  	* @param String[]
  	* @return String[]
  	* @throws  
  	*/ 
  	
  	public static List<String> remDup(String[] strArr){
  		List<String> list = new ArrayList<String>();
  		for(int i=0;i<strArr.length;i++){
    		if(!list.contains(strArr[i]))
    			list.add(strArr[i]);
    	}
  		return list;
  	}
  	
  	/**
     * 判断字符串是否为空
     * 包括是否为null，是否为空字符串，过滤完空格后是否为空字符串
     *
     * @param str 字符串参数
     * @return 是否为空
     */
    public static boolean isNullOrEmpry(String str) {
        if (str == null || "".equals(str)) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String[] args) {
		/*String[] strings  = {"苏登","苏登辉","苏登1","苏登2","苏登3","苏登0","苏登02"};
		String[] strings2 = {"苏登5","苏登辉","苏登1","苏登2","苏登3","苏登0"};
		traverseAndprint(bubbleSort(strings));
		char a = '苏';
		System.out.println((int)a);
		ArrayListUtil.traverseAndprint(getStrA(strings,strings2));*/
    	
    	String[] strArr = {"gx_query_0006",
    			"gx_handle_0003",
    			"gx_query_0007",
    			"gx_handle_0003",
    			"gx_handle_0002",
    			"gx_query_0019",
    			"gx_query_0020",
    			"gx_query_0019",
    			"gx_query_0021",
    			"gx_query_0022",
    			"gx_query_0019",
    			"gx_handle_0006",
    			"gx_query_0021",
    			"gx_query_0022",
    			"gx_query_0019",
    			"gx_handle_0006",
    			"gx_query_0021",
    			"gx_query_0022",
    			"gx_query_0019",
    			"gx_handle_0006",
    			"gx_query_0008",
    			"gx_query_0008",
    			"gx_query_0009",
    			"gx_handle_0003",
    			"gx_query_0008",
    			"gx_query_0009",
    			"gx_handle_0003",
    			"gx_query_0008",
    			"gx_query_0009",
    			"gx_handle_0003",
    			"gx_query_0008",
    			"gx_query_0009",
    			"gx_handle_0003"};
    	System.out.println("去重前有    " + strArr.length + "  个字符串！！！！！！");
    	List<String> list = remDup(strArr);
    	Collections.sort(list);
    	ArrayListUtil.traverseAndprint(list);
    	System.out.println("有      " + list.size() + " 个不同的字符串！！！！！！！");
    	
    	System.out.println("排序********************");
    	
	}
}
 