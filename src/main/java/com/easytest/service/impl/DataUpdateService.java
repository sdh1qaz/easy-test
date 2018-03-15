package com.easytest.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.easytestall.constant.RuntimeData;
import com.easytestall.pojo.ParamPojo;
import com.easytestall.util.ExcelUtil;

/**
 * @ClassName： DataUpdateService
 * @Author: dhSu
 * @Description:定时任务，每10秒钟从excel读取数据，更新接口信息
 * @Date:Created in 2018年3月15日
 */

@Component
@Configurable
@EnableScheduling
public class DataUpdateService {
	
	// 每5分钟执行一次
	@Scheduled(cron = "0/10 * * * * ? ")
	public void updateData() {
		//加载接口信息列表
		System.out.println("开始执行定时任务。。。。。。。。。。");
		try {
			RuntimeData.getListparampojo().clear();//清空原信息列表
			RuntimeData.getListparampojo().addAll(ExcelUtil.getParamPojoList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//加载批次_业务名_接口名 与接口信息的映射
		for(ParamPojo paramPojo:RuntimeData.getListparampojo()) {
			RuntimeData.getMapparampojo().put(paramPojo.getBatchNum()+"_"+paramPojo.getBusinessName()+"_"+paramPojo.getLuaName(), paramPojo);
		}
		System.out.println("定时任务执行完毕。。。。。。。。。。");
	}
}
