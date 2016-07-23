package com.wowchina.util;

import java.security.MessageDigest;

/**
 * Created by wangguisheng on 16/6/23.
 */
public class Parse2MD5 {

    /**
     * 32位小写md5
     * @param src
     * @return
     */
    public static String parseStrToMd5L32(String src){
        String returnMD5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(src.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes){
                int bt = b&0xff;
                if (bt < 16){
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            returnMD5 = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnMD5;
    }
}
