package com.atguigu.myssm.util;

/**
 * ClassName: StringUtil
 * Package: com.atguigu.myssm.util
 * Description:
 *    String的工具类
 * @Author ysyhl97
 * @Create 2023/2/3 9:37
 * @Version 1.0
 */

public class StringUtil {

   /**
    * 判断字符串是否为空或""
    * @param str
    * @return 为空返回true
    */
   public static boolean isEmpty(String str){
      return str == null || "".equals(str);
   }

   public static boolean isNotEmpty(String str ){
      return !isEmpty(str);
   }
}
