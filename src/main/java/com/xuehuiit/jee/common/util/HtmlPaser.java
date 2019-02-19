package com.xuehuiit.jee.common.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author 边缘孤客 edgeloner@yahoo.com.cn
 * @since 2004-11-27
 */
public class HtmlPaser {
    private final static String REGEX_IMG = "<\\s*img\\s+[^>]*src\\s*=\\s*[\"']?([^\"'>\\s]+\\.[a-zA-Z]{3,4})[\"']?[^>]*>";

    public static String[] getImgUrls(String html) {
        String[] result = null;
        Pattern p = Pattern.compile(REGEX_IMG,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        ArrayList<String> tmp = new ArrayList<String>();

        while (m.find()) {
            String match = m.group(1);

            if (notLocal(match)) {
                tmp.add(match);
            }
        }

        result = new String[tmp.size()];
        result = (String[]) tmp.subList(0, tmp.size()).toArray(result);

        return result;
    }

	/**
	 * @param match
	 * @return
	 */
	private static boolean notLocal(String match) {
		return (match.indexOf("pages/upload") == -1);
	}
}
