package com.easytestall.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.easytestall.util.StringComomnUtil;
import com.easytestall.pojo.TextCompareResultPojo;
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
		logger.info("文本去重。\n输入文本：\n" + text + "\n去重后文本：\n" + stringBuffer.toString() + "去重前的文本个数： " + countBefore + ",  去重后的文本个数： " + count);
		return new TextReduplicationPojo(stringBuffer.toString(),String.valueOf(count),String.valueOf(countBefore));
	}
	
	//文本比较，左边有右边没有
	@RequestMapping(value="redu/leftHasRightNot",produces={MediaType.APPLICATION_JSON_VALUE})
	public TextCompareResultPojo leftHasRightNot(String textLeft,String textRight ){
		List<String> list = StringComomnUtil.getStrA(textLeft, textRight);
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		for(String str : list) {
			if(!"".equals(str)) {
				stringBuffer.append(str + "\n");
				count = count + 1;
			}
		}	
		logger.info("文本比较，左边文本域中有右边文本域中没有的字符串有  " + count + " 个：\n" + stringBuffer.toString());
		return new TextCompareResultPojo(stringBuffer.toString(),"",count);
	}
	
	//文本比较，左边无右边有
	@RequestMapping(value="redu/leftNotRightHas",produces={MediaType.APPLICATION_JSON_VALUE})
	public TextCompareResultPojo leftNotRightHas(String textLeft,String textRight ){
		//字符串变为字符串数组
		List<String> list = StringComomnUtil.getStrA(textRight,textLeft);
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		for(String str : list) {
			if(!"".equals(str)) {
				stringBuffer.append(str + "\n");
				count = count + 1;
			}
		}	
		logger.info("文本比较，左边文本域中无右边文本域中有的字符串有  " + count + " 个：\n" + stringBuffer.toString());
		return new TextCompareResultPojo("",stringBuffer.toString(),count);
	}
}
