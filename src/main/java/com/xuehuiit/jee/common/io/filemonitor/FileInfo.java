/**
 * Copyright (c) xueduo 2009-2015 corporation.  All rights reserved
 */
package com.xuehuiit.jee.common.io.filemonitor;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author <a href="fx19800215@163.com">robert.feng</a> 
 *
 */
public class FileInfo {

	private String fileType; // txt binary 
	private String txtContent; // 文本文件内容
	private byte[] binaryContent; // 二进制文件内容
	private Date createDate;//创建日期
	private long lastModify;
	private String fullPath;
	private String optType = "0"; // 操作类型
	
	/**
	 * @return optType
	 */
	public String getOptType() {
		return optType;
	}
	/**
	 * @param optType 要设置的 optType
	 */
	public void setOptType(String optType) {
		this.optType = optType;
	}
	/**
	 * @return fullPath
	 */
	public String getFullPath() {
		return fullPath;
	}
	/**
	 * @param fullPath 要设置的 fullPath
	 */
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	/**
	 * @return lastModify
	 */
	public long getLastModify() {
		return lastModify;
	}
	/**
	 * @param lastModify 要设置的 lastModify
	 */
	public void setLastModify(long lastModify) {
		this.lastModify = lastModify;
	}
	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate 要设置的 createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return binaryContent
	 */
	public byte[] getBinaryContent() {
		return binaryContent;
	}
	/**
	 * @return fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @return txtContent
	 */
	public String getTxtContent() {
		return txtContent;
	}
	/**
	 * @param binaryContent 要设置的 binaryContent
	 */
	public void setBinaryContent(byte[] binaryContent) {
		this.binaryContent = binaryContent;
	}

	/**
	 * @param fileType 要设置的 fileType
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * @param txtContent 要设置的 txtContent
	 */
	public void setTxtContent(String txtContent) {
		this.txtContent = txtContent;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("fileType", this.fileType)
				.append("createDate", this.createDate).append("binaryContent",
						this.binaryContent).append("fullPath", this.fullPath)
				.append("txtContent", this.txtContent).append("lastModify",
						this.lastModify).toString();
	}
	
	
	
	
	
	
}
