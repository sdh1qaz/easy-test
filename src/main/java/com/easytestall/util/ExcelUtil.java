package com.easytestall.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easytestall.constant.ParamPojoContant;
import com.easytestall.pojo.ParamPojo;
import com.easytestall.pojo.TreeNode;

/**
 * @ClassName： ExcelUtil
 * @Author: dhSu
 * @Description:操作excel
 * @Date:Created in 2018年3月12日
 */


public class ExcelUtil {
     //读取initial。clx，将其每一行放入对象ParamPojo中，整体的excel内容变成一个list<ParamPojo> 放于内存中
	 public static List<ParamPojo> getParamPojoList() throws IOException{
		 List<ParamPojo> listParamPojo = new ArrayList<ParamPojo>();
		 
		 //File file = ResourceUtils.getFile("classpath:initail_tps.xls");
		 File file = ResourceUtils.getFile("D:\\initail_tps.xls");
		 InputStream inputStream = new FileInputStream(file);
		 //HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:web-info.xls")));
		 HSSFWorkbook book = new HSSFWorkbook(inputStream);
		 HSSFSheet sheet = book.getSheetAt(0);

		 for(int i=1; i<sheet.getLastRowNum()+1; i++) {
	        HSSFRow row = sheet.getRow(i);
	        String batchNum = row.getCell(0).getStringCellValue(); //批次
	        String businessName = row.getCell(1).getStringCellValue(); //业务名称
	        String luaName = row.getCell(2).getStringCellValue();//接口名称
	        String apiUrl = row.getCell(3).getStringCellValue();//接口url
	        String params = row.getCell(4).getStringCellValue();//API URL
	        listParamPojo.add(new ParamPojo(batchNum, businessName, luaName, apiUrl, params));
		 }
		 book.close();
		 return listParamPojo;
	 }
	 
	 /**
     * 生成不重复的批次级、业务级、接口级节点集合
     * @param List<ParamPojo>
     * @return HashSet<TreeNode>
     * @throws 
	 */
	 public static HashSet<TreeNode> getTreeNodeSet(List<ParamPojo> listParamPojo){
		 HashSet<TreeNode> hashSet = new HashSet<TreeNode>();
		 for(int i = 0;i < listParamPojo.size();i ++) {
			 String batchNum = listParamPojo.get(i).getBatchNum();//批次
			 String businessName = listParamPojo.get(i).getBusinessName();//业务
			 String luaName = listParamPojo.get(i).getLuaName();//接口
			 String attr = batchNum+"_"+businessName+"_"+luaName;
			 TreeNode treeNodeBatch = new TreeNode(batchNum, "0", batchNum,true,"");//批次级节点
			 TreeNode treeNodeBusine = new TreeNode(businessName, batchNum, businessName,false,"");//业务级节点
			 TreeNode treeNodelua = new TreeNode(luaName, businessName, luaName,false,attr);//业务级节点
			 hashSet.add(treeNodeBatch);
			 hashSet.add(treeNodeBusine);
			 hashSet.add(treeNodelua);
		 }
		 return hashSet;
	 }
	 
	 /**
     * 生成ztree节点json数据
     * @param HashSet<TreeNode>
     * @return jsonArray
     * @throws 
	 */
     public static JSONArray getJsonArray(HashSet<TreeNode> hashSet) {
    	 List<TreeNode> list = new ArrayList<TreeNode>();
    	 list.addAll(hashSet);
    	 JSONArray jsonArray = new JSONArray();
    	 for (TreeNode treeNode:list) {
    		 JSONObject jsonObject = (JSONObject)JSON.toJSON(treeNode);
    		 jsonArray.add(jsonObject);
    	 }
    	 return jsonArray;
     } 
     
     /**
     * @throws IOException 
     * @throws IOException 
      * 生成ztree节点jsonzi字符串
      * @param HashSet<TreeNode>
      * @return String
      * @throws 
 	 */
	 public static String getNodesStr() throws IOException {
		 return getJsonArray(getTreeNodeSet(ParamPojoContant.listParamPojo)).toJSONString();
	 }
	 
	 public static void main(String[] args) throws IOException {
		 List<ParamPojo> listParamPojo = getParamPojoList();
		 for (ParamPojo paramPojo:listParamPojo) {
			 System.out.println(paramPojo.toString());
		 }
		 
		// System.out.println(getJsonArray(getTreeNodeSet(getParamPojoList())));
	}
}