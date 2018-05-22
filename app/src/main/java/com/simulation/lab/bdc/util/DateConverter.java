package com.simulation.lab.bdc.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将其他类型的对象转换成 Date 类型
 */
public class DateConverter {
    private static final String TAG = "DateConverter";

    /**
     * 将 yyyy-MM-dd 格式的字符串转换成Date类型
     * @param source
     * @return
     */
    public static Date convert(String source) {
        Date date = null;
        if(source != null){
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = df.parse(source);
            } catch (ParseException e) {
                Log.e(TAG, "convert: ",e);
            }
        }
        return date;
    }
}


