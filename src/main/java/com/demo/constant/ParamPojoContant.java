package com.demo.constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.pojo.ParamPojo;
import com.demo.util.ExcelUtil;

/**
 * @ClassName： ParamPojoContant
 * @Author: dhSu
 * @Description: 存放从excel文件中读取的ParamPojo数据
 * @Date:Created in 2018年3月12日
 */

public class ParamPojoContant {
	
	public ParamPojoContant() {}
	
	public static final List<ParamPojo> listParamPojo = new ArrayList<ParamPojo>();//存放接口列表，对应excel
	public static final Map<String, ParamPojo> mapParamPojo = new HashMap<String,ParamPojo>();//
	
	static {
		try {
			listParamPojo.addAll(ExcelUtil.getParamPojoList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(ParamPojo paramPojo:listParamPojo) {
			mapParamPojo.put(paramPojo.getBatchNum()+"_"+paramPojo.getBusinessName()+"_"+paramPojo.getLuaName(), paramPojo);
		}
	}
}
