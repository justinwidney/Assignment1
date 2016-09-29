package com.example.justin.jwidney__habittracker;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    Intent addHabitintent = getIntent();
    String theName;
    private static final String FILENAME = "file.sav";
    private ArrayList<Habit> habitList = new ArrayList<>();
    private ArrayList<String> testList = new ArrayList<>();
    private ArrayAdapter<Habit> adapter;
    private ListView habitLayout;


    //http://stackoverflow.com/questions/2942857/how-to-convert-current-date-into-string-in-java
    String date = new SimpleDateFormat("MM--dd--yyyy").format(new Date());
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //testList.add("item1");
        //testList.add("item2");


        adapter = new ArrayAdapter<Habit>(this, R.layout.list_item, habitList);
        habitLayout = (ListView) findViewById(R.id.habit_listview);
        habitLayout.setAdapter(adapter);
        loadFromFile();
    }


    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add_task:
                addHabbitMenu();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }





    public void addHabbitMenu() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText habitName = new EditText(this);
        builder.setTitle("Add/Delete Habit");
        builder.setMessage(date);
        builder.setView(habitName);


        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {
                setResult(RESULT_OK);
                String thename = habitName.getText().toString();
                Habit newHabit = new Habit(thename);

                habitList.add(newHabit);
                saveInFile();
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });

        builder.show();
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList,writer);
            writer.flush();

            //fos.write(new String(date + " | " + text).getBytes());
            //fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void loadFromFile() {
        ArrayList<String> habits = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            //Code Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            habitList = gson.fromJson(in,new TypeToken<ArrayList<Habit>>(){}.getType());

            /*String line = in.readLine();
            while (line != null) {
                habits.add(line);
                line = in.readLine();
            }*/

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
        //return habits.toArray(new String[habits.size()]);
    }









}
