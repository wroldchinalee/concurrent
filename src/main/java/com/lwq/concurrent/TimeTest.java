package com.lwq.concurrent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2019/5/22.
 */
public class TimeTest {
    public static void main(String[] args) {
        System.out.println(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(1555493228));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }
}
