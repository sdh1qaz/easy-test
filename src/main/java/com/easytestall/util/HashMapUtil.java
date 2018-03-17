package com.easytestall.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.easytestall.pojo.ParamPojo;

/**
 * @ClassName： HashMapUtil
 * @Author: dhSu
 * @Description:
 * @Date:Created in 2018年3月17日
 */

public class HashMapUtil {
     private static final Logger logger = Logger.getLogger(HashMapUtil.class);
	 /**
     * 遍历接口信息hashmap
     * @param ParamPojo
     * @return Boolean 是否日志记录
	 */
	public static void goThoughHashMap(HashMap<String,ParamPojo> hashMap,boolean isRecord) {
		   Iterator<Entry<String, ParamPojo>> iter = hashMap.entrySet().iterator();
		   while (iter.hasNext()) {
			   Map.Entry<String, ParamPojo> entry = (Map.Entry<String, ParamPojo>) iter.next();
	           Object key = entry.getKey();
	           Object val = entry.getValue();
	           if(isRecord)
	        	   logger.info(key + " -- " + val);
		   }
	}
}
