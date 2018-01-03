package com.example.nadus.tutelage_unisys.DataModels;

import java.util.ArrayList;

/**
 * Created by msuba on 1/3/2018.
 */

public class TimeTable
{
    public TimeTable(){}
    public static ArrayList<String> Monday;
    public static ArrayList<String> Tuesday;
    public static ArrayList<String> Wednesday;
    public static ArrayList<String> Thursday;
    public static ArrayList<String> Friday;
    public static ArrayList<String> Saturday;
    public static ArrayList<String> Timings;

    public  ArrayList<String> getTimings() {
        return Timings;
    }

    public  void setTimings(ArrayList<String> timings) {
        Timings = timings;
    }

    public  ArrayList<String> getMonday() {
        return Monday;
    }

    public  void setMonday(ArrayList<String> monday) {
        Monday = monday;
    }

    public  ArrayList<String> getTuesday() {
        return Tuesday;
    }

    public  void setTuesday(ArrayList<String> tuesday) {
        Tuesday = tuesday;
    }

    public  ArrayList<String> getWednesday() {
        return Wednesday;
    }

    public  void setWednesday(ArrayList<String> wednesday) {
        Wednesday = wednesday;
    }

    public  ArrayList<String> getThursday() {
        return Thursday;
    }

    public  void setThursday(ArrayList<String> thursday) {
        Thursday = thursday;
    }

    public  ArrayList<String> getFriday() {
        return Friday;
    }

    public  void setFriday(ArrayList<String> friday) {
        Friday = friday;
    }

    public  ArrayList<String> getSaturday() {
        return Saturday;
    }

    public  void setSaturday(ArrayList<String> saturday) {
        Saturday = saturday;
    }
}
