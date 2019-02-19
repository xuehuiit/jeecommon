/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.test;

import java.util.Date;
import java.util.List;

import com.xuehuiit.jee.common.db.DbUtils4Mysql;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DBUtilsTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//testSave();
		//testSave4int();
		//testSave4Bean();
		
		//System.out.println( testSave4Beanr() );
		
		//delete();
		//deletep();
		
		//update();
		
		//updatep();
		
		//get();
		
		//search();
		saveChild();
	}
	
	
	public static void saveChild(){
		/*
		Webappday d = new Webappday();
		
		d.setAppid("ddd");
		d.setDatestr("20120930");
		
		DbUtils4Mysql.save(d);*/
		
		
	}
	
	
	
	public static void testSave(){
		
		 String sql = "insert into test(ints,longs,varchars,datetimes) values (?,?,?,?)";
		 Object params[] = { Integer.valueOf(10),Long.valueOf(11),"adsfdsf",new java.util.Date() };
		 DbUtils4Mysql.save(sql, params);
		 
		
	}
	
	
	
	public static void testSave4int(){
		
		 String sql = "insert into test(ints,longs,varchars,datetimes) values (?,?,?,?)";
		 Object params[] = { Integer.valueOf(10),Long.valueOf(11),"adsfdsf",new java.util.Date() };
		 System.out.println( DbUtils4Mysql.save4ReturnId(sql, params));
		 
		
	}
	

	
	public static void testSave4Bean(){
		
		 TestBean bean = new TestBean();
		 bean.setInts(1);
		 bean.setLongs(Long.valueOf(1111));
		 bean.setVarchars("我是bean");
		 bean.setDatetimes(new Date());
		
		 DbUtils4Mysql.save(bean);
		 
		
	}
	
	
	
	public static long testSave4Beanr(){
		
		 TestBean bean = new TestBean();
		 bean.setInts(1);
		 bean.setLongs(Long.valueOf(1111));
		 bean.setVarchars("我是bean");
		 bean.setDatetimes(new Date());
		
		 return DbUtils4Mysql.save4ReturnId(bean);
		 
		
	}
	
	
	
	public static void delete(){
		
		DbUtils4Mysql.update("delete from test where id = 1");
	}

	
	
	public static void deletep(){
		Object p[] = {Integer.valueOf(3),Integer.valueOf(8)};
		DbUtils4Mysql.update("delete from test where id >=? and id <=?",p);
	}
	
	
	public static void update(){
		
		DbUtils4Mysql.update("update test set varchars = '我的修改' where id = 2");
		
	}
	
	
	public static void updatep(){
		
		Object p[] = {"哈哈哈哈哈哈哈",Integer.valueOf(2)};
		DbUtils4Mysql.update("update test set varchars = ? where id = ? ",p);
		
	}
	
	
	
	public static void get(){
		TestBean b = DbUtils4Mysql.get(TestBean.class, "select * from test where id = 2");
		System.out.println(b.toString());
	}
	
	
	public static void search(){
		
		List<TestBean> d = DbUtils4Mysql.search(TestBean.class, "select * from test");
		
		for (TestBean testBean : d) {
			System.out.println( testBean.toString() );
		}
		
	}
	
	
	
	
}
