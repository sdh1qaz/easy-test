package com.easytestall.pojo;

import java.util.List;

/**
 * @ClassName： ListParamPojo
 * @Author: dhSu
 * @Description:
 * @Date:Created in 2018年3月22日
 */

public class ListParamPojo {
	
    private List<ParamPojo> listParamPojo;//接口信息列表
    private String info;//从excel中获取接口信息列表中成功或失败的信息
    
	public List<ParamPojo> getListParamPojo() {
		return listParamPojo;
	}
	public void setListParamPojo(List<ParamPojo> listParamPojo) {
		this.listParamPojo = listParamPojo;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    
    
}
