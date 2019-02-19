package com.xuehuiit.jee.common.util;

import java.io.InputStream;


/**
 * @author 边缘孤客 edgeloner@yahoo.com.cn
 * @since 2004-11-29
 * @version 1.0
 */
public class UploadFile {
    private String fileName;
    private InputStream inputStream;
    private String direct;

	public String getDirect() {
		return direct;
	}
	public void setDirect(String direct) {
		this.direct = direct;
	}
    public String getFileName() {
        return fileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
