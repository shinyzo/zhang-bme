package com.zhang.bme.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2019/11/1.
 */
public class PatternUtil {

         /**
     * 正则表达式匹配两个指定字符串中间的内容
     * @param soap
     * @return
     */
        public static List<String> getRgexTextList(String soap, String rgex){
            List<String> list = new ArrayList<String>();
            Pattern pattern = Pattern.compile(rgex);// 匹配的模式
            Matcher m = pattern.matcher(soap);
            while (m.find()) {
                int i = 1;
                list.add(m.group(i));
                i++;
            }
             return list;
        }

        /**
      * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
      * @param soap
      * @param rgex
      * @return
      */
        public static String getRgexText(String soap,String rgex){
            Pattern pattern = Pattern.compile(rgex);// 匹配的模式
            Matcher m = pattern.matcher(soap);
            while(m.find()){
                return m.group(1);
            }
            return "";
        }


        /**
      * 测试
      * @param args
      */
        public static void main(String[] args) {

        }

}
