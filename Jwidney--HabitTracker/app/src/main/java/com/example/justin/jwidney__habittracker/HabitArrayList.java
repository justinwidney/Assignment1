package com.example.justin.jwidney__habittracker;

import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by justin on 29/09/16.
 */
public class HabitArrayList extends AppCompatActivity{

    private ArrayList<Habit> habitList = new ArrayList<>();


    public HabitArrayList() {
    }


    public void updateHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }


    public ArrayList<Habit> getHabitList() {
        return this.habitList;
    }


}
