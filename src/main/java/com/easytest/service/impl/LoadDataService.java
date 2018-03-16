package com.easytest.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.easytestall.constant.RuntimeData;
import com.easytestall.pojo.ParamPojo;
import com.easytestall.util.ExcelUtil;

/**
 * @ClassName： LoadDataService
 * @Author: dhSu
 * @Description: 启动时加载接口信息列表和批次_业务名_接口名 与接口信息的映射数据
 * @Date:Created in 2018年3月15日
 */

@Service
public class LoadDataService implements CommandLineRunner{
	private static final Logger logger = Logger.getLogger(LoadDataService.class);
	@Override
    public void run(String... arg0) {
		logger.info("启动时加载数据..........");
		//加载接口信息列表
		try {
			RuntimeData.getListparampojo().addAll(ExcelUtil.getParamPojoList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//加载批次_业务名_接口名 与接口信息的映射
		for(ParamPojo paramPojo:RuntimeData.getListparampojo()) {
			RuntimeData.getMapparampojo().put(paramPojo.getBatchNum()+"_"+paramPojo.getBusinessName()+"_"+paramPojo.getLuaName(), paramPojo);
		}
		logger.info("启动时加载数据完毕..........");
	}
}
