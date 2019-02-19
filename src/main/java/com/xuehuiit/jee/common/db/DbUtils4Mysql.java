/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.ArrayUtils;
/**
 * 
 * 基于DBUtils的mysql操作类
 * 
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DbUtils4Mysql {

	
	private final static QueryRunner _g_runner = new QueryRunner();
	
	/**
	 * 获取数据库连接
	 * @return
	 */
	public static Connection getConnection() {
		try{
			return DBManager.getConnection();
		}catch(SQLException e){
			throw new DBException(e);
		}
	}
	
	
	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection(){
		DBManager.closeConnection();
	}
	
	
	/**
	 * 
	 * 保存对象
	 * 
	 * @param sql
	 * @param params
	 * 
	 */
	public static void save(BaseDbBean bean){
		try {
			_g_runner.update(getConnection(), bean.getInsertsql(), bean.getInsertParms());
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
	}
	
	
	
	/**
	 * 
	 * 保存对象
	 * 
	 * @param sql
	 * @param params
	 * 
	 */
	public static void save(String sql, Object...params){
		
		try {
			_g_runner.update(getConnection(), sql, params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
	}
	
	/**
	 * 
	 * 保存对象，并且返回自增长主键
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * 
	 */
	public static long save4ReturnId(String sql, Object...params){
		
		 long netval = 0; 
		
		 try {
			 
			 _g_runner.update(getConnection(), sql, params);
			 Number d =  _g_runner.query(getConnection(), "SELECT LAST_INSERT_ID()", new ScalarHandler<BigInteger>());
			 //System.out.println(d.longValue());
			 netval = d.longValue();
			 
			} catch (SQLException e) {
				throw new DBException(e);
			}
				
		
		
		return netval;
		
	}
	
	
	/**
	 * 
	 * 保存对象，并且返回自增长主键
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * 
	 */
	public static long save4ReturnId( BaseDbBean bean ){
		
		 long netval = 0; 
			
		 try {
			 
			 _g_runner.update( getConnection() , bean.getInsertsql() , bean.getInsertParms() );
			 Number d =  _g_runner.query( getConnection() , "SELECT LAST_INSERT_ID()" , new ScalarHandler<BigInteger>() );
			 netval = d.longValue();
			 
			} catch (SQLException e) {
				throw new DBException(e);
			}
				
		
		
		return netval;
		
	}
	
	
	/**
	 * 
	 * 执行UPDATE , DELETE 等操作语句
	 * 
	 * @param sql
	 * @param params
	 * 
	 */
	public static void update(String sql, Object...params){
		
		 try {
			_g_runner.update(getConnection(), sql, params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		
	}
	
	
	
	
	/**
	 * 
	 * 执行UPDATE , DELETE 等操作语句
	 * 
	 * @param sql
	 * @param params
	 * 
	 */
	public static void update(String sql){
		
		 try {
				_g_runner.update(getConnection(), sql);
			} catch (SQLException e) {
				throw new DBException(e);
			}
			
	}
	
	
	private final static List<Class<?>> PrimitiveClasses = new ArrayList<Class<?>>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = -7295254160698625968L;

	{
		add(Long.class);
		add(Integer.class);
		add(String.class);
		add(java.util.Date.class);
		add(java.sql.Date.class);
		add(java.sql.Timestamp.class);
	}};
	
	
	private final static boolean _IsPrimitive(Class<?> cls) {
		return cls.isPrimitive() || PrimitiveClasses.contains(cls) ;
	}
	
	/**
	 * 
	 * 查询单个对象
	 * 
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static  <T> T  get( Class<T> beanClass , String sql , Object...params ){
		
		
		try {
			return (T)_g_runner.query(getConnection(), sql, new BeanHandler<T>(beanClass), params);
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		//return null;
	}
	
	/**
	 * 
	 * 查询单个对象
	 * 
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static  <T> T  get( Class<T> beanClass , String sql ){
		
		
		try {
			return (T)_g_runner.query(getConnection(), sql, new BeanHandler<T>(beanClass));
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		//return null;
	}
	
	
	/**
	 * 查询多个对象
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> search(Class<T> beanClass, String sql, Object...params){
		
		try {
			ResultSetHandler<List<T>> handler = new BeanListHandler<T>(beanClass);
	        List<T> persons;
			persons = _g_runner.query(getConnection(), sql, handler,params);
			return persons;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	
		
	}
	
	/**
	 * 查询多个对象
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> search(Class<T> beanClass, String sql){
		
		try {
			ResultSetHandler<List<T>> handler = new BeanListHandler<T>(beanClass);
	        List<T> persons;
			persons = _g_runner.query(getConnection(), sql, handler);
			return persons;
		} catch (SQLException e) {
			throw new DBException(e);
		}
	
		
	}
	
	
	
	/**
	 * 
	 * 带有分页的查询
	 * 
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param page
	 * @param count
	 * @param params
	 * @return
	 * 
	 * 
	 */
	public static <T> List<T> search(Class<T> beanClass, String sql,int page, int count, Object...params){
		
		if(page < 0 || count < 0) 
			throw new IllegalArgumentException("Illegal parameter of 'page' or 'count', Must be positive.");
		int from = (page - 1) * count;
		count = (count > 0) ? count : Integer.MAX_VALUE;
		return search(beanClass, sql + " LIMIT ?,?", ArrayUtils.addAll(params, new Integer[]{from,count}));		
	}
	
	
	/**
	 * 
	 * 带有分页的查询
	 * 
	 * @param <T>
	 * @param beanClass
	 * @param sql
	 * @param page
	 * @param count
	 * @param params
	 * @return
	 * 
	 * 
	 */
	public static <T> List<T> search(Class<T> beanClass, String sql,int page, int count){
		
		if(page < 0 || count < 0) 
			throw new IllegalArgumentException("Illegal parameter of 'page' or 'count', Must be positive.");
		int from = (page - 1) * count;
		count = (count > 0) ? count : Integer.MAX_VALUE;
		return search(beanClass, sql + " LIMIT ?,?", new Object[]{from,count});
		
	}
	
	
	/**
	 * 
	 * 获取单个值的汇总查询
	 * @param sql
	 * @param params
	 * @return
	 * 
	 */
	public static long stat(String sql, Object...params) {
		
		long returnvalue = 0;
	
		try {
			
			Number d;
			d = _g_runner.query(getConnection(), sql, new ScalarHandler<BigInteger>(),params);
			returnvalue = d.longValue();
			
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		
		return returnvalue;
	}
	
	
	/**
	 * 
	 * 获取单个值的汇总查询
	 * @param sql
	 * @param params
	 * @return
	 * 
	 */
	public static long stat(String sql) {
		
		long returnvalue = 0;
	
		try {
			
			Number d;
			d = _g_runner.query(getConnection(), sql, new ScalarHandler<BigInteger>());
			returnvalue = d.longValue();
			
		} catch (SQLException e) {
			throw new DBException(e);
		}
		
		
		return returnvalue;
	}
	
	
}
