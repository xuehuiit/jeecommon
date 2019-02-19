/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.util.ip;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class Ip4TaobaoDataBean {

	
	/**
	 * 
	 * {"ip":"210.75.225.254","country":"\u4e2d\u56fd","area":"\u534e\u5317",
10
"region":"\u5317\u4eac\u5e02","city":"\u5317\u4eac\u5e02","county":"","isp":"\u7535\u4fe1",
11
"country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000",
12
"county_id":"-1","isp_id":"100017"}
	 * 
	 */
	
	
	private String ip;
	private String country;
	private String area;
	private String region;
	private String city;
	private String county;
	private String isp;
	private String country_id;
	private String area_id;
	private String region_id;
	private String city_id;
	private String county_id;
	private String isp_id;
	
	
	public int flag = 0;
	public String svelue;
	public String value;
	
	
	public String getIp() {
		return ip;
	}
	public String getCountry() {
		return country;
	}
	public String getArea() {
		return area;
	}
	public String getRegion() {
		return region;
	}
	public String getCity() {
		return city;
	}
	public String getCounty() {
		return county;
	}
	public String getIsp() {
		return isp;
	}
	public String getCountry_id() {
		return country_id;
	}
	public String getArea_id() {
		return area_id;
	}
	public String getRegion_id() {
		return region_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public String getCounty_id() {
		return county_id;
	}
	public String getIsp_id() {
		return isp_id;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public void setIsp(String isp) {
		this.isp = isp;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public void setCounty_id(String county_id) {
		this.county_id = county_id;
	}
	public void setIsp_id(String isp_id) {
		this.isp_id = isp_id;
	}
	public String toString() {
		return new ToStringBuilder(this).append("ip", ip).append("country",
				country).append("area", area).append("region", region).append(
				"city", city).append("county", county).append("isp", isp)
				.append("country_id", country_id).append("area_id", area_id)
				.append("region_id", region_id).append("city_id", city_id)
				.append("county_id", county_id).append("isp_id", isp_id)
				.toString();
	}
	
	
	
	
}
