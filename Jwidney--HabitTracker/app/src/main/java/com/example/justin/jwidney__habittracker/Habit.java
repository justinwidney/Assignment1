package com.example.justin.jwidney__habittracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by justin on 27/09/16.
 */
public class Habit implements Serializable{

    private static final long serialVersionUID = 1L;

    private ArrayList<String> Habits = new ArrayList();
    private ArrayList<Date> completedRecord;


    private String name;
    private int count;
    String dayofWeek;
    private boolean[] days;
    private Calendar calendar = Calendar.getInstance();
    private Date date;

    public Habit(String name) {
        this.name = name;
        this.days = new boolean[7];                                 // Days of Week in Boolean form
        this.count = 0;                                             // Amount Completed
        this.completedRecord = new ArrayList<>();
        this.date = new Date();

    }

    public Habit(String name, Date date){
        this.name = name;
        this.dayofWeek = dayofWeek;
        this.days = new boolean[7];
        this.count = 0;
        this.date = date;
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

    public void setday(int position) {
        days[position] = true;
    }

    public void addCompletion() {
        this.completedRecord.add(new Date());
    }

    public void setCompletedRecord(ArrayList<Date> date){
        completedRecord = date;
    }

    // Add Count

    public void addCount() {
        count = count+1;
    }

    // remove completions

    public void removeCompletion(int position) {
        completedRecord.remove(position);
    }

    // getters

    public ArrayList<Date> getCompletedRecord() {
        return this.completedRecord;
    }



    public String getName(){
        return this.name;
    }

    public boolean getDayOfWeek(int position){

        return days[position];
    }

    public int getCount(){
        return this.count;
    }

    public boolean[] getDays(){
        return this.days;
    }


}
