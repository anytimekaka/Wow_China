package com.wowchina.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangguisheng on 16/8/5.
 */
public class TimeUtils {

    static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm");

    public static String getCurrentTime(){
        return SIMPLE_DATE_FORMAT.format(new Date());
    }
}
