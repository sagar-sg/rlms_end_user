package com.rlms.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class StringUtils {

    public static String getConvertedDate(long dateInLong){

        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(dateInLong);
        Date d = (Date) c.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(d);//this variable time contains the time in the format of "day/month/year".

    }

}
