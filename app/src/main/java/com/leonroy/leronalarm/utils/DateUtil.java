package com.leonroy.leronalarm.utils;

import java.text.SimpleDateFormat;

/**
 * Created by arng on 2015/5/27.
 */
public class DateUtil {
    public static final String YEAR = "yyyy";
    public static final String DAY = "yyyy/MM/dd";

    public static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }
}
