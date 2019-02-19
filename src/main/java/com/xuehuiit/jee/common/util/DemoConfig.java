package com.xuehuiit.jee.common.util;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author 边缘孤客 edgeloner@yahoo.com.cn
 * @since 2004-12-13
 */
public class DemoConfig {
    private static Log log = LogFactory.getLog(DemoConfig.class);
    private final static String DEFAULT_UPLOADFILE_PATH = "pages/upload/";
    private final static String DEFAULT_SECURITYCONFIG_PATH = "WEB-INF/security/acl-config.xml";
    private final static String DEFAULT_INDEX_PATH = "WEB-INF/index/";
    private final static String CLASS_REAL_PATH;

    static {   
        CLASS_REAL_PATH = getClassRealPath();
    }

    private String uploadFilePath;
    private String securityConfigPath;
    private String indexPath;
    private String rootPath;

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    private static String getClassRealPath() {
        String result;
        URI uri = null;

        try {
            uri = new URI(DemoConfig.class.getResource("/").toString());
        } catch (URISyntaxException e) {
            log.error(e);
        }

        File file = new File(uri);
        result = file.toString();
        result = result.endsWith(File.separator) ? result
                                                 : (result + File.separator);
        result += (".." + File.separator + ".." + File.separator);

        return result;
    }

    public String getIndexPath() {
        if (indexPath == null) {
            return getDefaultRealPath(DEFAULT_INDEX_PATH);
        }

        return getRealPath(indexPath);
    }

    public String getSecurityConfigPath() {
        if (securityConfigPath == null) {
            securityConfigPath = getDefaultRealPath(DEFAULT_SECURITYCONFIG_PATH);
        }

        return getRealPath(securityConfigPath);
    }

    public String getUploadFilePath(boolean isReal) {
        if (isReal) {
            if (uploadFilePath == null) {
                return getDefaultRealPath(DEFAULT_UPLOADFILE_PATH);
            }

            return getRealPath(uploadFilePath);
        } else {
            if (uploadFilePath == null) {
                return DEFAULT_UPLOADFILE_PATH;
            }

            return uploadFilePath;
        }
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public void setSecurityConfigPath(String securityConfigPath) {
        this.securityConfigPath = securityConfigPath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    private String getRealPath(String path) {
        return getRootPath() + path;
    }

    private String getDefaultRealPath(String path) {
        return CLASS_REAL_PATH + path;
    }
}
