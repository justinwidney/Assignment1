package com.example.justin.jwidney__habittracker;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.ArrayList;
import java.util.Date;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    //test get count
    public void testGetCount(){
        Habit habit = new Habit("test");
        habit.addCount();
        habit.addCount();

        assertEquals(habit.getCount(),2);

    }
    //test if completions words
    public void testaddCompletion(){
        Habit habit = new Habit("test");
        habit.addCompletion();
        Date date = new Date();
        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(date);

        assertEquals(habit.getCompletedRecord(), dates);
    }

    // test if can get name
    public void testgetName() {
        Habit habit = new Habit("test");
        String name = habit.getName();
        assertEquals(name,"test");
    }

    // test if can set day
    public void testsetDay() {
        Habit habit = new Habit("test");
        habit.setDay(1);

        assertEquals(habit.getDayOfWeek(1), true);
    }


    //test if completions are removed
    public void testremoveCompletion() {
        Date date = new Date();
        Habit habit = new Habit("test");
        habit.addCompletion();
        habit.addCompletion();
        habit.addCompletion();
        habit.removeCompletion(2);
        habit.removeCompletion(1);
        habit.removeCompletion(0);
        assertTrue((habit.getCompletedRecord().isEmpty()));

    }

    //test if we can change name
    public void testsetName(){
        Habit habit = new Habit("test");
        habit.setName("test2");
        assertEquals(habit.getName(),"test2");
    }

    // test if record can be retrieved
    public void testgetCompletedRecord() {
        Habit habit = new Habit("test");
        Date date = new Date();
        ArrayList<Date> dates = new ArrayList<Date>();
        dates.add(date);
        habit.setCompletedRecord(dates);
        assertEquals(habit.getCompletedRecord(),dates);
    }


}