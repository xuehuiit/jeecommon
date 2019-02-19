/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.codegen;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.velocity.VelocityContext;

import com.xuehuiit.jee.common.db.DbUtils4Mysql;
import com.xuehuiit.jee.common.tools.codegen.VelocityCenter;
import com.xuehuiit.jee.common.util.FileUtils;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DbBeanCodeGen {

	
	
	
	private String DB_NAME;
	
	private String PACKAGE = "com.xuehuiit.jee.common.db.codegen";
	private String OUTPUT_FIX = "F:/wingfeng/project/nearmobile/common_statistics_ww/src_framework/com.xuehuiit.jee.common/db/codegen";
	
	
	private String sql1 ;
	private String sql_alltables;
	
	private String vm = "F:/wingfeng/project/nearmobile/common_statistics_ww/src_framework/com.xuehuiit.jee.common/db/codegen/Bean.vm";

	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		
	}
	
	
	/**
	 * 生产数据库的所有代码
	 * 
	 * @param packagename  生产代码的包名
	 * @param database     数据库名
	 * @param outputdir    代码输出的路径
	 * @param genflag      生产标记  
	 * @param tablesname   生产的表
	 */
	public void CodeGenAllDB(String packagename , String database , String outputdir , String vm ){
		this.PACKAGE = packagename;
		this.DB_NAME = database;
		this.OUTPUT_FIX = outputdir;
		
		this.sql1 = "select COLUMN_NAME as columns ,COLUMN_COMMENT as commit ,data_type as datetype from information_schema.COLUMNS where TABLE_SCHEMA='"+database+"' and ";
		this.sql_alltables = "select  TABLE_NAME as tablename from information_schema.TABLES where TABLE_SCHEMA='"+database+"'";
		
		if( null != vm && vm.length()>0   )
		   this.vm = vm;
			 
		genalltable();
		
	}
	
	
	
	/**
	 * 
	 * 生成指定表的数据bean
	 * 
	 * @param packagename   包名
	 * @param database      数据库名
	 * @param outputdir     输出路径
	 * @param tablestr      表名字
	 * @param vm            模板路径
	 * 
	 */
	public void CodeGenAllTables(String packagename , String database , String outputdir , String tablestr , String vm ){
		
		this.PACKAGE = packagename;
		this.DB_NAME = database;
		this.OUTPUT_FIX = outputdir;
		
		this.sql1 = "select COLUMN_NAME as columns ,COLUMN_COMMENT as commit ,data_type as datetype from information_schema.COLUMNS where TABLE_SCHEMA='"+database+"' and ";
		this.sql_alltables = "select  TABLE_NAME as tablename from information_schema.TABLES where TABLE_SCHEMA='"+database+"'";
		
		
		if( null != vm && vm.length()>0   )
			   this.vm = vm;
				 
		
		String[] tablesarr = tablestr.split(",");
		String t = "";
		
		int i = 1;
		for (String string : tablesarr) {
			
			t = t+"'"+string+"'";
			
			if( i < tablesarr.length )
				t = t+",";
			
			i++;
		}
		
		
		String sql = "select  TABLE_NAME as tablename from information_schema.TABLES where TABLE_SCHEMA='"+DB_NAME+"' and TABLE_NAME in("+t+")";
		List<Tables> list = DbUtils4Mysql.search(Tables.class, sql);
		
		for (Tables tables : list) {
			
			String tname = tables.getTablename();
			System.out.println("ALTER TABLE `"+tname+"` ADD INDEX `"+tname+"_ind` (`cmp_id`);");
			
			genTableBean(tables.getTablename());
			
		}
		
	}
	
	
	
	public  void genalltable(){
		
		
		List<Tables> list = DbUtils4Mysql.search(Tables.class, sql_alltables);
		for (Tables tables : list) {
		
			System.out.println(tables.getTablename());
			
			genTableBean(tables.getTablename());
			
		}
		
		
	}
	
	
	public  void genTableBean(String tableName){
		
		
		CodeGenUtil gen = new CodeGenUtil();
		
		String sqltable = sql1+"TABLE_NAME = '"+tableName+"'";
		String classname = gen.getClassName4Table(tableName);
		
		
		String tplString = FileUtils.readFile2String4selfslty(vm,null);
		VelocityContext vc = new VelocityContext();
		
		List<Column> list = DbUtils4Mysql.search(Column.class, sqltable);
		
		String columns = "";
		String columns_ww = "";
		
		int i = 1;
		
		for (Column column : list) {
			
			if( column.getColumns().equals("id") || column.getColumns().equals("Id") )
				continue;
			
			columns  = columns + column.getColumns();
			columns_ww = columns_ww+"?";
			
			
			if( i < list.size()-1 ){
				columns = columns +",";
				columns_ww = columns_ww +",";
			}
			
			i++;
		}
		
		vc.put("tablename", tableName);
		vc.put("classname", classname);
		vc.put("codegen", gen);
		vc.put("package", PACKAGE);
		vc.put("columns",list);
		vc.put("columnst",columns);
		vc.put("columns_ww", columns_ww);
		
		
		String conttent = VelocityCenter.getContest4StringTpl( tplString , vc);
		
		File file = new File(OUTPUT_FIX+"/"+classname+".java");
		
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(file, conttent, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	

}
