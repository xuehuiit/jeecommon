/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.db.codegen;


/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class DbBeanGenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		String packages = "net.dingyong.webreptile.test.dbbean";
		String outdir = "/project/javaworkspace/ylf_demain_webreptile/src/net/dingyong/webreptile/test/dbbean";
		String db = "eth_token";
		
		
		DbBeanCodeGen d = new DbBeanCodeGen();
		d.CodeGenAllTables(packages, db, outdir, "coinbase,markets,coin_market", "/project/javaworkspace/ylf_demain_webreptile/src_framework/com.xuehuiit.jee.common/db/codegen/Bean.vm");
		
		
	}

}
