package com.easytestall.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.easytestall.pojo.ParamPojo;

/**
 * @ClassName： RuntimeData
 * @Author: dhSu
 * @Description: 程序运行时数据，存放从excel文件中读取的ParamPojo数据
 * @Date:Created in 2018年3月12日
 */

public class RuntimeData {
	
	public RuntimeData() {}
	
	public static int dataIsOk = 0;
	public static String notOkReason= "加载成功";
	public static final List<ParamPojo> listParamPojo = new ArrayList<ParamPojo>();//存放接口信息列表，对应excel
	public static final Map<String, ParamPojo> mapParamPojo = new HashMap<String,ParamPojo>();//批次_业务名_接口名 与接口信息的映射
	
	
	public static List<ParamPojo> getListparampojo() {
		return listParamPojo;
	}


	public static String getNotOkReason() {
		return notOkReason;
	}


	public static void setNotOkReason(String notOkReason) {
		RuntimeData.notOkReason = notOkReason;
	}


	public static Map<String, ParamPojo> getMapparampojo() {
		return mapParamPojo;
	}


	public static int getDataIsOk() {
		return dataIsOk;
	}

	public static void setDataIsOk(int dataIsOk) {
		RuntimeData.dataIsOk = dataIsOk;
	}

}
