package com.wowchina.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangguisheng on 16/8/5.
 */
public class TimeUtils {

    static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd");

    public static String getCurrentTime(){
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    public static String getDateString(Date date){
        return DATE_FORMAT.format(date);
    }

    public static Date getDateByString(String stringDate){
        Date result = null;
        try{
            result = DATE_FORMAT.parse(stringDate);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
