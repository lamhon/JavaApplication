/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectjava.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author hoang
 */
public class FormatDate {
    public static String getDay(String datetime){
        String day = "";
        String[] array = datetime.split(" ");
        day = array[2].toString();
        return day;
    }
    
    public static String getMonth(String datetime){
        String month = "";
        String[] array = datetime.split(" ");
        switch(array[1].toString()){
            case "Jan":
                month = "01";
                break;
            case "Feb":
                month = "02";
                break;
            case "Mar":
                month = "03";
                break;
            case "Apr":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "Jun":
                month = "06";
                break;
            case "Jul":
                month = "07";
                break;
            case "Aug":
                month = "08";
                break;
            case "Sep":
                month = "09";
                break;
            case "Oct":
                month = "10";
                break;
            case "Nov":
                month = "11";
                break;
            case "Dec":
                month = "12";
                break;
        }
        return month;
    }
    
    public static String getYear(String datetime){
        String year = "";
        
        String[] array = datetime.split(" ");
        year = array[5].toString();
        return year;
    }
    
    public static String getYearFromDB(String datetime){
        String year = "";
        
        String array[] = datetime.split("/");
        year = array[0];
        return year;
    }
    
    public static String getMonthFromDB(String datetime){
        String month = "";
        
        String[] array = datetime.split("/");
        month = array[1];
        return month;
    }
    
    public static String getDayFromDB(String datetime){
        String day = "";
        
        String[] array = datetime.split("/");
        String arrayDayTime = array[2];
        String[] arrayDay = arrayDayTime.split(" ");
        day = arrayDay[0];
        
        return day;
    }
    
    public static String getTime(String datetime){
        String time;
        
        String[] array = datetime.split(" ");
        String[] arrayTime = array[1].split(".");
        time = arrayTime[0];
        
        return time;
    }
    
    public static String getYearByLocal(String datetime){
        String year = "";
        
        String[] array = datetime.split("-");
        year = array[0];
        return year;
    }
    
    public static String getMonthByLocal(String datetime){
        String month = "";
        
        String[] array = datetime.split("-");
        month = array[1];
        return month;
    }
    
    public static String getDayByLocal(String datetime){
        String day = "";
        
        String[] array = datetime.split("-");
        String arrayDayTime = array[2];
        String[] arrayDay = arrayDayTime.split("T");
        day = arrayDay[0];
        
        return day;
    }
    
    public static Date getDateFormat() throws ParseException{   // Lấy ngày hiện tại theo format: 2021/12/01
        Calendar ca = new GregorianCalendar();
        String day = ca.get(Calendar.DAY_OF_MONTH) + "";
        String month = ca.get(Calendar.MONTH) + 1 + "";
        String year = ca.get(Calendar.YEAR) + "";

        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }

        String dd = year + "-" + month + "-" + day;

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
        return date;
    }
    
    public static String returnDateToString(Date dateType) {
        String dateString;

        String day = FormatDate.getDay(dateType.toString());
        String month = FormatDate.getMonth(dateType.toString());
        String year = FormatDate.getYear(dateType.toString());

        dateString = year.concat(month).concat(day);

        return dateString;
    }
    
    public static String returnDateFromDB(String dateType) {
        String dateString;

        String day = FormatDate.getDayFromDB(dateType);
        String month = FormatDate.getMonthFromDB(dateType);
        String year = FormatDate.getYearFromDB(dateType);

        dateString = year.concat(month).concat(day);
        return dateString;
    }
    
    public static String returnDateLocalDate(String dateType) {
        String dateString;

        String day = FormatDate.getDayByLocal(dateType);
        String month = FormatDate.getMonthByLocal(dateType);
        String year = FormatDate.getYearByLocal(dateType);

        dateString = year.concat(month).concat(day);
        return dateString;
    }
    
    public static String dateString(String dd, String MM, String yyyy) {    //FOrmat 01/12/2021
        return dd.concat("/").concat(MM).concat("/").concat(yyyy);
    }
    
    public static int dayOfMonth(String month, String year) {
        int day = 0;
        switch (month) {
            case "01":
                day = 31;
                break;
            case "02":
                if (checkLeapYear(year)) {
                    day = 29;
                } else {
                    day = 28;
                }
                break;
            case "03":
                day = 31;
                break;
            case "04":
                day = 30;
                break;
            case "05":
                day = 31;
                break;
            case "06":
                day = 30;
                break;
            case "07":
                day = 31;
                break;
            case "08":
                day = 31;
                break;
            case "09":
                day = 30;
                break;
            case "10":
                day = 31;
                break;
            case "11":
                day = 30;
                break;
            case "12":
                day = 31;
                break;
        }
        return day;
    }
    
    public static boolean checkLeapYear(String year) {
        return ((Integer.parseInt(year)% 4 == 0 && Integer.parseInt(year) % 100 != 0) || Integer.parseInt(year) % 400 == 0);
    }
}
