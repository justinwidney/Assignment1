package com.example.justin.jwidney__habittracker;

import java.util.ArrayList;

/**
 * Created by justin on 27/09/16.
 */
public class Habit {

    private ArrayList<String> Habits = new ArrayList();
    private String name;


    public Habit(String name) {
        this.name = name;

    }

    public String toString(){
        return name;
    }

    public void add(String name) {
        this.name = name;
        Habits.add(this.name);
    }
}
