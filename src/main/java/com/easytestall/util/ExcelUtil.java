package com.easytestall.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.easytest.exception.EasyTestException;
import com.easytestall.constant.RuntimeData;
import com.easytestall.pojo.ParamPojo;
import com.easytestall.pojo.TreeNode;

/**
 * @ClassName： ExcelUtil
 * @Author: dhSu
 * @Description:操作excel
 * @Date:Created in 2018年3月12日
 */

public class ExcelUtil {
	private static final Logger logger = Logger.getLogger(ExcelUtil.class);
     //读取initial。clx，将其每一行放入对象ParamPojo中，整体的excel内容变成一个list<ParamPojo> 放于内存中
	 public static List<ParamPojo> getParamPojoList() throws EasyTestException {
		List<ParamPojo> listParamPojo = new ArrayList<ParamPojo>();
		 
		 //File file = ResourceUtils.getFile("classpath:initail_tps.xls");
		int i = 0;
		try {
			  File file;
			  file = ResourceUtils.getFile("D:\\initial_tps.xls");
			  InputStream inputStream = new FileInputStream(file);
			  HSSFWorkbook book = new HSSFWorkbook(inputStream);
			  HSSFSheet sheet = book.getSheetAt(0);

			  for(i=1; i<sheet.getLastRowNum()+1; i++) {
			        HSSFRow row = sheet.getRow(i);
			        if(isEmptyRow(row))
			        	continue;
			        String batchNum = row.getCell(0).getStringCellValue(); //批次
			        String businessName = row.getCell(1).getStringCellValue(); //业务名称
			        String luaName = row.getCell(2).getStringCellValue();//接口名称
			        String apiUrl = row.getCell(3).getStringCellValue();//接口url
			        String params = row.getCell(4).getStringCellValue();//API URL
			        String retFieldDesp = row.getCell(5).getStringCellValue();//返回报文字段含义说明
			        int rowNum = row.getRowNum();
			        listParamPojo.add(new ParamPojo(batchNum, businessName, luaName, apiUrl, params,retFieldDesp,rowNum));
			  }
			 book.close();
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			logger.info("initial_tps.xls找不到。。。" + e.toString());
			//return listParamPojo;
			throw new EasyTestException("找不到initial_tps.xls",e,1001);
		}
		catch (IOException e) {
			logger.info("读取initial_tps.xls文件出错。。。。。" + e.toString());
			//return listParamPojo;
			throw new EasyTestException("读取initial_tps.xls文件出错",e,1002);
		}
		catch(IllegalStateException e) {
			logger.info("读取initial_tps.xls文件第" + (i+1) + "行出错，该行单元格有纯数字！！！！" + e.toString());
			//return listParamPojo;
			throw new EasyTestException("读取initial_tps.xls文件第" + (i+1) + "行出错，该行单元格有纯数字！！！！",e,1003);
		}
		catch(NullPointerException e) {
			logger.info("读取initial_tps.xls文件第" + (i+1) + "行出错，该行有空值！！！" + e.toString());
			throw new EasyTestException("读取initial_tps.xls文件第" + (i+1) + "行出错，该行有空值！！！",e,1004);
		}
		 
		 return listParamPojo;
	 }
	
	 /**
     * 判断是不是空行
     * @param HSSFRow
     * @return boolean
	 */
	public static boolean isEmptyRow(HSSFRow row) {
		for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
			Cell cell = row.getCell(c);
			if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK)
				return false;
		}
		return true;
	}

	 /**
     * 生成不重复的批次级、业务级、接口级节点集合
     * @param List<ParamPojo>
     * @return HashSet<TreeNode>
     * @throws 
	 */
	 public static LinkedHashSet<TreeNode> getTreeNodeSet(List<ParamPojo> listParamPojo){
		 LinkedHashSet<TreeNode> linkedHashSet = new LinkedHashSet<TreeNode>();
		 for(int i = 0;i < listParamPojo.size();i ++) {
			 String batchNum = listParamPojo.get(i).getBatchNum();//批次
			 String businessName = listParamPojo.get(i).getBusinessName();//业务
			 String luaName = listParamPojo.get(i).getLuaName();//接口
			 String attr = batchNum+"_"+businessName+"_"+luaName;
			 int rowNum = listParamPojo.get(i).getRowNum();
			 TreeNode treeNodeBatch = new TreeNode(batchNum, "0", batchNum,true,"",0);//批次级节点
			 TreeNode treeNodeBusine = new TreeNode(businessName, batchNum, businessName,true,"",0);//业务级节点
			 TreeNode treeNodelua = new TreeNode(luaName, businessName, luaName,true,attr,rowNum);//接口级节点
			 linkedHashSet.add(treeNodeBatch);
			 linkedHashSet.add(treeNodeBusine);
			 linkedHashSet.add(treeNodelua);
		 }
		 /*int count = 0;
		 //遍历linkedHashSet，pid=0的头两个父节点open=true
		 for(TreeNode treeNode:linkedHashSet) {
			 if("0".equals(treeNode.getPid())) {
				 treeNode.setOpen(true);
				 count = count + 1;
				 if(count > 1)
					 break;
			 }
		 }*/
		 return linkedHashSet;
	 }
	 
	 /**
     * set变json数组
     * @param Set<TreeNode>
     * @return jsonArray
     * @throws 
	 */
     public static JSONArray getJsonArray(Set<TreeNode> linkedHashSet) {
    	 List<TreeNode> list = new ArrayList<TreeNode>();
    	 list.addAll(linkedHashSet);//将不重复的有序的TreeNode对象放到list中
    	 JSONArray jsonArray = new JSONArray();
    	 for (TreeNode treeNode:list) {
    		 JSONObject jsonObject = (JSONObject)JSON.toJSON(treeNode);
    		 jsonArray.add(jsonObject);
    	 }
    	 return jsonArray;
     } 
     
     /**
      * 生成ztree节点jsonzi字符串
      * @param HashSet<TreeNode>
      * @return String
      * @throws IOException 
 	 */
	 public static String getNodesStr() {
		 if(RuntimeData.dataIsOk != 0) {
			 String json = "{\"info\":\"初始化失败！错误编码 :" + RuntimeData.dataIsOk +  ", " + RuntimeData.notOkReason +",请修改后点击更新按钮\"}";
			 JSONObject jsonObject = JSON.parseObject(json);//json字符串转换成jsonobject对象
			 return jsonObject.toJSONString();
		 }
		 else
			 return getJsonArray(getTreeNodeSet(RuntimeData.listParamPojo)).toJSONString();
	 }
	 
	 /**
      * 设置单元格内容
      * @param int rowNum
      * @param int colNum
      * @return String
      * @throws IOException 
 	 */
	 public static boolean setCell(int rowNum,int colNum,String cont) {
		boolean status = false;
		String fileName = "D:\\initial_tps.xls";
		try {
			FileInputStream io = new FileInputStream(new File(fileName));
			HSSFWorkbook book = new HSSFWorkbook(io); // 读取的文件
			HSSFSheet  sheet = book.getSheetAt(0);
			HSSFRow row = sheet.getRow(rowNum);
			Cell cell = row.createCell(colNum);
			cell.setCellValue(cont);
			FileOutputStream fo = new FileOutputStream(fileName); // 输出到文件
			book.write(fo);
			book.close();
			fo.close();
			io.close();
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("设置单元格内容时出错:\n" + e.toString() );
			return status;
		}
		return status;
	 }
	 
	 public static void main(String[] args) throws EasyTestException {
		 List<ParamPojo> listParamPojo = getParamPojoList();
		 for (ParamPojo paramPojo:listParamPojo) {
			 System.out.println(paramPojo.toString());
		 }
	}
}
