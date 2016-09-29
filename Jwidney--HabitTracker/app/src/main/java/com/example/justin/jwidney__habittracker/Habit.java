package com.example.justin.jwidney__habittracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by justin on 27/09/16.
 */
public class Habit {

    private ArrayList<String> Habits = new ArrayList();
    private String name;
    private int count =0;
    private boolean[] days;
    private String date;

    public Habit(String name) {
        this.name = name;
        String date = new SimpleDateFormat("MM--dd--yyyy").format(new Date());
        this.days = new boolean[7];

    }
    



    public String toString(){
        return name;
    }

    public void add() {
        count = count+1;
    }
}
