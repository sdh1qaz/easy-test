package com.easytestall.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.easytestall.pojo.TextReduplicationPojo;
import com.easytestall.util.TextOperation;

/**
 * @ClassName： TextReduController
 * @Author: dhSu
 * @Description:
 * @Date:Created in 2018年3月14日
 */
@RestController
public class TextReduController {
	
    private static final Logger logger = Logger.getLogger(TextReduController.class);
    
	//文本去重
	@RequestMapping(value="redu/reduText",produces={MediaType.APPLICATION_JSON_VALUE})
	public TextReduplicationPojo reduText(String text){
		List<String> list = TextOperation.quchong(text);
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		for(String str : list) {
			if(!"".equals(str)) {
				stringBuffer.append(str + "\n");
				count = count + 1;
			}
		}	
		int countBefore = TextOperation.getAmount(text);
		logger.info("文本去重。输入文本：" + text + "去重后文本：" + stringBuffer.toString() + "去重前的文本个数： " + countBefore + ",  去重后的文本个数： " + count);
		return new TextReduplicationPojo(stringBuffer.toString(),String.valueOf(count),String.valueOf(countBefore));
	}
}
