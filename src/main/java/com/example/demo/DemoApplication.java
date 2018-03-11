package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.RequestDtoConstant;
import com.demo.util.HttpClientUtil;
import com.demo.util.Operation;
import com.demo.util.StringComomnUtil;


@RestController
@SpringBootApplication
public class DemoApplication {
    

	
	//返回请求体 requestDTO
	@RequestMapping("requestDto/getBody")
	String getRequestDto(String key,String phoneNumber) {
		String phoneNum = "18878722354";
		if(!StringComomnUtil.isNullOrEmpry(phoneNumber))
			phoneNum = phoneNumber;
		String rd = RequestDtoConstant.operate(key).replaceAll("@@@", phoneNum);
		return rd;
	}
	
	//响应测试按钮ajax请求
	@RequestMapping("test/testApi")
	String testApi(String params) throws ParseException, IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("http://");
		//buffer.append("172.20.118.74:20601");
		buffer.append("localhost:18080");
		buffer.append("/api/business/gx/");
		if(params.indexOf("gx_query")!=-1)
			buffer.append("businessQuery");
		else
			buffer.append("businessHandle");
		System.out.println(buffer.toString());
		
		return HttpClientUtil.sendPost(buffer.toString(), params);
	}
	
	//响应批量测试按钮ajax请求
		@RequestMapping("test/testApiByBatch")
		String testApiByBatch(String param,String phoneNumber) throws ParseException, IOException {
			StringBuffer bufReturn = new StringBuffer();
			String phoneNum = "18878722354";
			if(!StringComomnUtil.isNullOrEmpry(phoneNumber))
				phoneNum = phoneNumber;
			String params = RequestDtoConstant.operate(param).replaceAll("@@@", phoneNum);
			
			bufReturn.append(param + "  开始测试。。。。。\n请求参数:\n" + params +"\n" );
			StringBuffer buffer = new StringBuffer();
			buffer.append("http://");
			//buffer.append("172.20.118.74:20601");
			buffer.append("localhost:18080");
			buffer.append("/api/business/gx/");
			if(params.indexOf("gx_query")!=-1)
				buffer.append("businessQuery");
			else
				buffer.append("businessHandle");
			System.out.println(buffer.toString());
			bufReturn.append("返回报文:\n" + HttpClientUtil.sendPost(buffer.toString(), params) + "\n");
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
