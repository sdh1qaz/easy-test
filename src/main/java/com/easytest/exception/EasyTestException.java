package com.easytest.exception;
/**
 * @ClassName： EasyTestException
 * @Author: dhSu
 * @Description: 小工具自定义异常类
 * @Date:Created in 2018年3月26日
 */

public class EasyTestException extends Exception{

	private static final long serialVersionUID = 1L;
	private int exceptionCode;
	//exceptionCode = "1001"--找不到initial_tps.xls
	//exceptionCode = "1002"--读取initial_tps.xls文件出错
	//1003--读取第" + (i+1) + "行出错，该行单元格有纯数字！！！！
	//1004--"读取第" + (i+1) + "行出错，该行有空值！！！
	//构造一个新的异常与指定的详细信息和原因。
	
	public EasyTestException(String msg, Throwable cause,int exceptionCode) {
        super(msg, cause);
        this.exceptionCode = exceptionCode;
    }

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	
	
}
