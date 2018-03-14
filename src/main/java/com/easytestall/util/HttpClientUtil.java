package com.easytestall.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @ClassName： HttpClientUtil
 * @Author: dhSu
 * @Description: 访问API地址，处理相应
 * @Date:Created in 2018年3月10日
 */

public class HttpClientUtil {
	public static  InetAddress addr;
	static {
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	/**
     * 模拟请求
     * 
     * @param url    资源地址
     * @param map    参数列表
     * @param encoding  编码
     * @return
     * @throws ParseException
     * @throws IOException
     */
	public static String sendPost(String url,String parmJson) {
        String body = "";
        try {
        	CloseableHttpClient httpclient = HttpClients.createDefault();//创建httpclient对象
            //创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            
            StringEntity se = new StringEntity(parmJson);
            
            httpPost.setEntity(se);//设置参数到请求对象中

            System.out.println("请求地址：" + url);
            System.out.println("请求参数：" + parmJson);
            
            //设置header信息
            //指定报文头【Content-type】、【User-Agent】
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
            
            //执行请求操作，并拿到结果（同步阻塞）
            CloseableHttpResponse response = httpclient.execute(httpPost);
            //获取结果实体
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                //按指定编码转换结果实体为String类型
                body = JsonFormatUtil.formatJson(EntityUtils.toString(entity, "UTF-8")) ;
            }
            EntityUtils.consume(entity);
            //释放链接
            response.close();
        }catch(Exception e) {
        	body = "请求接口出现了异常：\n" + e.toString();
        	e.printStackTrace();
        }
        finally {
        	
		}
        return body;
    }
    
    public static String getHostName() {
    	String hostName = "unkonwn";
    	hostName = addr.getHostName().toString();//获取计算机名
    	return hostName;
    }
    public static void main(String[] args) throws ParseException, IOException {
		/*String url = "http://localhost:18080/api/business/gx/businessQuery";
		String parmJson = "{\r\n" + 
				"\"callId\":\"string\",\r\n" + 
				"\"channelCode\": \"IVR\",\r\n" + 
				"\"ext\": {\"typeId\":\"1\"},\r\n" + 
				"\"regionCode\": \"GX\",\r\n" + 
				"\"telnum\": \"18878722354\",\r\n" + 
				"\"templateCode\": \"gx_query_0002\"\r\n" + 
				"}";
		
		System.out.println(sendPost(url,parmJson));*/
    	System.out.println(getHostName());
	}
}
