package com.warrantix.main.common.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static long mElapseDays, mElapseHours, mElapseMinutes, mElapseSeconds, mDifferent;

    public static String dateFarmatWithString(String inputDate){
        String resultDate = "";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date date = null;
        try {
            date = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        resultDate = timeFormat.format(date);

        return resultDate;
    }

    public static  void Differnece(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        mDifferent = different;

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        mElapseDays = elapsedDays;
        mElapseHours = elapsedHours;
        mElapseMinutes = elapsedMinutes;
        mElapseSeconds = elapsedSeconds;

    }

    public static long getDiffernet(){
        return mDifferent;
    }

    public static long getElapseDays (){
        return mElapseDays;
    }

    public static long getElapseHours (){
        return mElapseHours;
    }

    public static long getElapseMinutes (){
        return mElapseMinutes;
    }

    public static long getElapseSeconds (){
        return mElapseSeconds;
    }

    public static String getCurrentDate(){
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        return currentDateTimeString;
    }

    public static String formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {

        String parsedDate = "";
        try {
            Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
            parsedDate = formatter.format(initDate);

        } catch (ParseException e){

        }
        return parsedDate;
    }

}