package com.easytestall.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.ParseException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easytestall.constant.RuntimeData;
import com.easytestall.pojo.ParamPojo;
import com.easytestall.util.ExcelUtil;
import com.easytestall.util.HashMapUtil;
import com.easytestall.util.HttpClientUtil;

/**
 * @ClassName： EasyTestController
 * @Author: dhSu
 * @Description:
 * @Date:Created in 2018年3月14日
 */
@RestController
public class EasyTestController {
	private Logger logger = Logger.getLogger(EasyTestController.class);
    
	
	
    //加载节点数据
	@RequestMapping("init/getNodes")
	String getTreeNodes() throws IOException {
		return ExcelUtil.getNodesStr();
	}
	
	//返回单个接口的请求入参
	@RequestMapping("requestDto/getBody")
	String getRequBody(String interfaceName) throws IOException {
		return RuntimeData.getMapparampojo().get(interfaceName).getParams();
	}
	
	//把新添加到excel中的接口数据更新到内存中
	@RequestMapping("ztree/updateNodes")
	String updateTreeNodes() {
		String status="从initial_tps.xls中更新数据成功";
		logger.info("从initial_tps.xls中读取最新接口信息。。。。。。。。。。");
		RuntimeData.getListparampojo().clear();//清空原信息列表
		RuntimeData.getListparampojo().addAll(ExcelUtil.getParamPojoList());
		//加载批次_业务名_接口名 与接口信息的映射
		for(ParamPojo paramPojo:RuntimeData.getListparampojo()) {
			RuntimeData.getMapparampojo().put(paramPojo.getBatchNum()+"_"+paramPojo.getBusinessName()+"_"+paramPojo.getLuaName(), paramPojo);
		}
		logger.info("从initial_tps.xls中读取最新接口信息完毕。。。。。。。。。。");
		/*logger.info("遍历Mapparampojo。。。。。。。。。。");
		HashMapUtil.goThoughHashMap((HashMap<String, ParamPojo>)RuntimeData.getMapparampojo(), true);
		logger.info("遍历Mapparampojo完毕。。。。。。。。。。");*/
		return status;
	}
	
	//更新某一行的params列单元格内容（请求入参）
	@RequestMapping("requestDto/updateBody")
	String updateParam(String params,int rowNum,String attrKey) {
		RuntimeData.getMapparampojo().get(attrKey).setParams(params);//将新的请求参数更新到内存中
		boolean result = ExcelUtil.setCell(rowNum, 4, params);
		if(result)
			return  "请求参数(第" + (rowNum+1) + "行第5列)修改成功!";
		else
			return "修改失败，找不到文件或者文件正在被其他程序使用！";
	}
	
	//批量测试接口
	@RequestMapping("test/testBatchApi")
	String testApiByBatch(String param) throws ParseException, IOException {
		StringBuffer bufReturn = new StringBuffer();
		ParamPojo paramPojo = RuntimeData.mapParamPojo.get(param);
		
		String params = paramPojo.getParams();
		String url = paramPojo.getApiUrl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		bufReturn.append(df.format(new Date()) + "\n");
		bufReturn.append(param + "  开始测试。。。。。\n"+"接口地址： " + url  + "\n请求参数:\n" + params +"\n" );
		long start = System.currentTimeMillis();
		bufReturn.append("返回报文:\n" + HttpClientUtil.sendPost(url, params) + "\n");
		String timeConsume = String.valueOf((System.currentTimeMillis()-start)/1000);
		bufReturn.append("\n-------->>>用时 " + timeConsume + "秒\n\n" );
		logger.info(bufReturn.toString());
		return bufReturn.toString();
	}
}
