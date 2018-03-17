package com.easytest.service.impl;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

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
	//private static final Logger logger = Logger.getLogger(DataUpdateService.class);
	// 每5分钟执行一次
	/*@Scheduled(cron = "0/10 * * * * ? ")
	public void updateData() {
		//加载接口信息列表
		logger.info("从initial_tps.xls中读取最新接口信息。。。。。。。。。。");
		try {
			RuntimeData.getListparampojo().clear();//清空原信息列表
			RuntimeData.getListparampojo().addAll(ExcelUtil.getParamPojoList());
		} catch (IOException e) {
			logger.info("从initial_tps.xls中更新接口信息出错： " + e.toString());
			//e.printStackTrace();
		}
		//加载批次_业务名_接口名 与接口信息的映射
		for(ParamPojo paramPojo:RuntimeData.getListparampojo()) {
			RuntimeData.getMapparampojo().put(paramPojo.getBatchNum()+"_"+paramPojo.getBusinessName()+"_"+paramPojo.getLuaName(), paramPojo);
		}
		logger.info("从initial_tps.xls中读取最新接口信息完毕。。。。。。。。。。");
	}*/
}
