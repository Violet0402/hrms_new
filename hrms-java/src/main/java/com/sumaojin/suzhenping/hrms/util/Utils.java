package com.sumaojin.suzhenping.hrms.util;

import cn.hutool.crypto.digest.MD5;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Utils {
    public static String timeToString(Timestamp timestamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(timestamp);
        return format;
    }

    public static Date stringToDate(String timeStr){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(timeStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Timestamp stringToTimestap(String timeStr){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(timeStr);
            return new Timestamp(date.getTime()+86400000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
