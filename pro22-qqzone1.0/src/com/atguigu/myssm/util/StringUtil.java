package com.atguigu.myssm.util;

/**
 * ClassName: StringUtil
 * Package: com.atguigu.myssm.util
 * Description:
 *
 * @Author ysyhl97
 * @Create 2023/3/5 16:31
 * @Version 1.0
 */

public class StringUtil {

    // 判断字符串为null和""
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
