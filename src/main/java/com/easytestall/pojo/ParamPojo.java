package com.easytestall.pojo;
/**
 * @ClassName： ParamPojo
 * @Author: dhSu
 * @Description:存放excel中每一行的初始化参数值
 * @Date:Created in 2018年3月12日
 */

public class ParamPojo {
	
    private String batchNum;//批次
    private String businessName;//业务名称
    private String luaName;//Lua接口名称
    private String apiUrl;//接口访问路径
    private String params;//入参
    private String retFieldDesp;//返回报文字段解释
    private int rowNum;//对应的initial_tps.xls中的行
    
	

	public ParamPojo(String batchNum, String businessName, String luaName, String apiUrl, String params,String retFieldDesp, int rowNum) {
		super();
		this.batchNum = batchNum;
		this.businessName = businessName;
		this.luaName = luaName;
		this.apiUrl = apiUrl;
		this.params = params;
		this.retFieldDesp = retFieldDesp;
		this.rowNum = rowNum;
	}

	
	public int getRowNum() {
		return rowNum;
	}


	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}


	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getLuaName() {
		return luaName;
	}

	public void setLuaName(String luaName) {
		this.luaName = luaName;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	
	public String getRetFieldDesp() {
		return retFieldDesp;
	}


	public void setRetFieldDesp(String retFieldDesp) {
		this.retFieldDesp = retFieldDesp;
	}


	@Override
	public String toString() {
		return "ParamPojo [batchNum=" + batchNum + ", businessName=" + businessName + ", luaName=" + luaName
				+ ", apiUrl=" + apiUrl + ", params=" + params + ", retFieldDesp=" + retFieldDesp + ", rowNum=" + rowNum
				+ "]";
	}

}
