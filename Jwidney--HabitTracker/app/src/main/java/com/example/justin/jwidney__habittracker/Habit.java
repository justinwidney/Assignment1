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


    private ArrayList<String> Habits = new ArrayList();
    private ArrayList<Date> completedRecord;


    private String name;
    private int count;
    private String newDate;
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

    public Habit(String name, String newDate){
        this.name = name;
        this.newDate = newDate;
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

    public void setNewDate(String newDate){
        this.newDate = newDate;
    }

    public void setDay(int position) {
        days[position] = true;
    }

    public void addCompletion() {
        this.completedRecord.add(new Date());
    }

    public void setCompletedRecord(ArrayList<Date> dates){
        completedRecord = dates;
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


}
