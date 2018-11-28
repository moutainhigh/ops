package com.jyblife.logic.wl.ops.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;

public class StrUtils {

	private static final Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
	
    /**
     * 数字num不够N位前面左补0并返回String
     */
    public static String getfixedNum(int num, int N) {
        String s = String.valueOf(num);

        if (s.length() < N) {
            int n = N - s.length();
            for (int i = 0; i < n; i++) {
                s = "0" + s;
            }
        }

        return s;
    }

    /**
     * 获取唯一的uuid
     */
    public static String getRandomUUID() {
        // 1、创建时间戳
        java.util.Date dateNow = new java.util.Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateNowStr = dateFormat.format(dateNow);
        StringBuffer sb = new StringBuffer(dateNowStr);

        // 2、创建随机对象
        Random rd = new Random();

        // 3、产生4位随机数
        String n = "";
        int rdGet; // 取得随机数
        do {
            rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
            // rdGet=Math.abs(rd.nextInt())%26+97; //产生97到122的随机数(a-z的键位值)
            char num1 = (char) rdGet;
            String dd = Character.toString(num1);
            n += dd;
        } while (n.length() < 4);// 假如长度小于4
        sb.append(n);
        // 4、返回唯一码
        return sb.toString();
    }

    public static Integer str2Int(String str) {
        if (NumberUtils.isNumber(str)) {
            return Integer.parseInt(str);
        }
        return null;
    }

    public static String int2Str(Integer num) {
        if (num != null) {
            return String.valueOf(num);
        }
        return null;
    }

    public static Byte int2Byte(Integer num) {
        if (num != null) {
            return (byte) num.intValue();
        }
        return null;
    }

    public static Integer byte2Int(Byte num) {
        if (num != null) {
            return num.intValue();
        }
        return null;
    }

    public static String getRandomStr() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }

    /**
     * 随机产生指定长度的小写字母与数字组合
     *
     * @param length
     * @return
     */
    public static String getRandomStr(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int mode = random.nextInt(2) % 2;
            if (mode == 0) {
                sb.append((char) (random.nextInt(26) + 97));
            } else {
                sb.append(random.nextInt(10));
            }
        }
        return sb.toString();
    }

    /**
     * 获取数字随机数
     * @param length
     * @return
     */
    public static String getRandomNumStr(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 生成随机文件名
     *
     * @param baseId
     * @param type
     * @param randomLength
     * @param suffixName 后缀名
     * @return
     */
    public static String getRandomFileName(int baseId, int type, int randomLength, String suffixName) {
        StringBuffer sb = new StringBuffer();
        sb.append(baseId).append("_")
                .append(type).append("_")
                .append(System.currentTimeMillis() / 1000).append("_")
                .append(getRandomStr(randomLength));
        if (StringUtils.isNotBlank(suffixName)) {
            char c = suffixName.charAt(0);
            if (c == '.') {
                sb.append(suffixName);
            } else {
                sb.append(".").append(suffixName);
            }
        }
        return sb.toString();
    }

    public static String camel2Underline(String source){
        if(StringUtils.isBlank(source)){
            return source;
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<source.length();i++){
            char c = source.charAt(i);
            if(Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }
    
    /**
     * 是否纯数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        return pattern.matcher(str).matches();  
    }
    
}
