package com.xuehuiit.jee.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author 边缘孤客 edgeloner@yahoo.com.cn
 * @since 2004-11-30
 */
public class EncodingConvert {
    private static Log log = LogFactory.getLog(EncodingConvert.class);

    public static String gb2iso(String str) {
        if (MyUtils.isBlank(str)) {
            return "";
        }

        String result = "";

        try {
            byte[] tmp = str.getBytes("GBK");
            result = new String(tmp, "ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            log.error("convert gb2iso error: ", e);
        }

        return result;
    }

    public static String gb2utf(String str) {
        if (MyUtils.isBlank(str)) {
            return "";
        }

        String result = "";

        try {
            byte[] tmp = str.getBytes("GBK");
            result = new String(tmp, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("convert gb2utf error: ", e);
        }

        return result;
    }

    public static String iso2gb(String str) {
        if (MyUtils.isBlank(str)) {
            return "";
        }

        String result = "";

        try {
            byte[] tmp = str.getBytes("ISO8859_1");
            result = new String(tmp, "GBK");
        } catch (UnsupportedEncodingException e) {
            log.error("convert iso2gb error: ", e);
        }

        return result;
    }

    public static String iso2utf(String str) {
        if (MyUtils.isBlank(str)) {
            return "";
        }

        String result = "";

        try {
            byte[] tmp = str.getBytes("ISO8859_1");
            result = new String(tmp, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("convert iso2utf error: ", e);
        }

        return result;
    }

    public static String utf2gb(String str) {
        if (MyUtils.isBlank(str)) {
            return "";
        }

        String result = "";

        try {
            byte[] tmp = str.getBytes("UTF-8");
            result = new String(tmp, "GBK");
        } catch (UnsupportedEncodingException e) {
            log.error("convert utf2gb error: ", e);
        }

        return result;
    }

    public static String utf2iso(String str) {
        if (MyUtils.isBlank(str)) {
            return "";
        }

        String result = "";

        try {
            byte[] tmp = str.getBytes("UTF-8");
            result = new String(tmp, "ISO8859_1");
        } catch (UnsupportedEncodingException e) {
            log.error("convert utf2iso error: ", e);
        }

        return result;
    }
}
