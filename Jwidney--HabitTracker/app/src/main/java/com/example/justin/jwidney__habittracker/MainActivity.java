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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Habit> habitList = new ArrayList<>();
    private final CharSequence[] days = {"S","M","T","W","Th","F","S",};

    private static final String FILENAME = "HabitsFile.sav";
    private MyCustomAdapter adapter;
    private Calendar myCalendar = Calendar.getInstance();
    private ListView habitLayout;

    String date = new SimpleDateFormat("yyyy--MM--dd").format(new Date());

    int dayOfWeek = myCalendar.get(Calendar.DAY_OF_WEEK)-1;
    public HabitArrayList habitArrayList = new HabitArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        habitLayout = (ListView) findViewById(R.id.habit_listview);
        loadFromFile();

        adapter = new MyCustomAdapter(habitList, dayOfWeek, this);
        habitLayout.setAdapter(adapter);


        Button completionBtn = (Button) findViewById(R.id.startCompletionsBtn);



       completionBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, CompletionsActivity.class);
                    finish();
                    habitArrayList.updateHabitList(habitList);
                    intent.putExtra("dayOfWeek",dayOfWeek);
                    MainActivity.this.startActivity(intent);


            }
        });


    }



    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }


    // Create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handle When the (+) button is clicked
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


    @Override
    protected void onResume(){
        super.onResume();
        habitList = habitArrayList.getHabitList();
        adapter.notifyDataSetChanged();



    }





    @Override
    protected void onPause() {
        super.onPause();
        saveInFile();
        habitArrayList.updateHabitList(habitList);
    }


    // Display Dialog of Days of Week
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


                changeDateMenu(newHabit,thename);
                dialog.dismiss();



            }
        })


        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //  Your code when user clicked on Cancel

            }
        });

        builder.show();
    }

    //http://www.learn-android-easily.com/2013/01/adding-check-boxes-in-dialog.html
    public void secondHabitMenu(Habit newHabit, final String thename) {
        final Habit habit = newHabit;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set the days of the week");
        builder.setMultiChoiceItems(days, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,
                                        boolean isChecked) {
                        if (isChecked) {
                            habit.setDay(indexSelected);
                        }

                    }
    })

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        habitList.add(habit);
                        adapter.notifyDataList(thename);
                        saveInFile();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //  Your code when user clicked on Cancel

                    }
                });
        builder.show();
    }


   public void changeDateMenu(Habit newHabit, final String thename){
       final Habit habit = newHabit;
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setTitle("Change Date?");
       builder.setMessage("format yyyy-mm-dd");
       final EditText differentDate = new EditText(this);
       builder.setView(differentDate);
               builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {

                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       setResult(RESULT_OK);
                       String newDate = differentDate.getText().toString();
                       habit.setNewDate(newDate);


                       secondHabitMenu(habit,thename);
                       dialog.dismiss();



                   }
               })


                       .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int id) {
                               //  Your code when user clicked on Cancel

                           }
                       });

       builder.show();
   }


   private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList, writer);
            writer.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }



    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            //Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt Sept.22,2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listType);
            habitArrayList.updateHabitList(habitList);


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }








}
