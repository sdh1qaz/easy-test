package com.easytestall.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easytestall.constant.ParamPojoContant;
import com.easytestall.pojo.ParamPojo;
import com.easytestall.util.ExcelUtil;
import com.easytestall.util.HttpClientUtil;
import com.easytestall.util.Operation;


@RestController
@SpringBootApplication
public class DemoApplication {
    
    //相应请求加载节点数据
	@RequestMapping("init/getNodes")
	String getTreeNodes() throws IOException {
		return ExcelUtil.getNodesStr();
	}
	
	
	//响应批量测试按钮ajax请求
		@RequestMapping("test/testBatchApi")
		String testApiByBatch1(String param) throws ParseException, IOException {
			StringBuffer bufReturn = new StringBuffer();
			ParamPojo paramPojo = ParamPojoContant.mapParamPojo.get(param);
			
			String params = paramPojo.getParams();
			String url = paramPojo.getApiUrl();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			bufReturn.append(df.format(new Date()) + "\n");
			bufReturn.append(param + "  开始测试。。。。。\n"+"接口地址： " + url  + "\n请求参数:\n" + params +"\n" );
			
			bufReturn.append("返回报文:\n" + HttpClientUtil.sendPost(url, params) + "\n");
			return bufReturn.toString();
		}
	
		
	@RequestMapping(value="/search",produces={MediaType.APPLICATION_JSON_VALUE})
	public Person search(String personName){
		List<String> list = Operation.quchong(personName);
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		for(String str : list) {
			if(!"".equals(str)) {
				stringBuffer.append(str + "\n");
				count = count + 1;
			}
		}		
		return new Person(stringBuffer.toString(),String.valueOf(count));
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
