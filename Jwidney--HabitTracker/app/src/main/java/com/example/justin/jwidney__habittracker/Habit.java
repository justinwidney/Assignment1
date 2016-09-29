package com.example.justin.jwidney__habittracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by justin on 27/09/16.
 */
public class Habit {

    private ArrayList<String> Habits = new ArrayList();
    private String name;
    private int count;
    String dayofWeek;
    private boolean[] days;
    private Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.CANADA);


    public Habit(String name) {
        this.name = name;
        this.dayofWeek = dayFormat.format(calendar.getTime());  // Day of Week in String eg. "Monday"
        this.days = new boolean[7];                             // Days of Week in Boolean form
        this.count = 0;                                         // Amount Completed
    }

    public Habit(String name, String dayofWeek){
        this.name = name;
        this.dayofWeek = dayofWeek;
        this.days = new boolean[7];
        this.count = 0;
    }


    // Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDayofWeek(String dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    public void setDays(boolean[] days) {
        this.days = days;
    }


    // Add Count

    public void addCount() {
        count = count+1;
    }



    // getters

    public String getName(){
        return this.name;
    }

    public String getDayofWeek(){
        return this.dayofWeek;
    }

    public int getCount(){
        return this.count;
    }

    public boolean[] getDays(){
        return this.days;
    }



}
