package com.example.justin.jwidney__habittracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CompletionsActivity extends MainActivity{

    private ListView listView;
    private ArrayList<String> listviewarray = new ArrayList<String>();
    private CompletionAdapter adapter;
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private DateFormat df = new SimpleDateFormat("MM/dd HH:mm:ss");         // format Date objects


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        dayOfWeek = intent.getIntExtra("dayOfWeek",0);
        habitList = habitArrayList.getHabitList();

        setContentView(R.layout.activity_completions2);

        for(Habit habit : habitList) {
                listviewarray.add(habit.getName());
        }

        listView = (ListView) findViewById(R.id.completionsListView);
        adapter = new CompletionAdapter(CompletionsActivity.this,listviewarray,habitList);
        listView.setAdapter(adapter);

        Button goToMainBtn = (Button) findViewById(R.id.gotoMainBtn);



        goToMainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                habitArrayList.updateHabitList(habitList);
                Intent i = new Intent(CompletionsActivity.this,MainActivity.class);
                finish();
                startActivity(i);


            }
        });

        }


    @Override
    protected void onPause() {
        super.onPause();
        habitArrayList.updateHabitList(habitList);


    }




    public void displayStats(final int position){


        final List<String> listItems = new ArrayList<String>();



            final Habit habit = habitList.get(position);

                final ArrayList<Date> completionDates = habit.getCompletedRecord();
                for (Date date : completionDates) {
                    String tempDate = df.format(date);
                listItems.add(tempDate);
            }

        final Integer[] intArray = new Integer[listItems.size()];
            ArrayList<Date> completedRecordDate = habit.getCompletedRecord();

            final ArrayList selectedItems = new ArrayList<>();

            //http://stackoverflow.com/questions/7063831/android-how-to-populate-a-charsequence-array-dynamically-not-initializing
            CharSequence[] completedRecord = listItems.toArray(new CharSequence[listItems.size()]);

            AlertDialog builder = new AlertDialog.Builder(this)
            .setTitle("Past Completions")
            .setMultiChoiceItems(completedRecord,null, new DialogInterface.OnMultiChoiceClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                    if (isChecked) {

                        selectedItems.add(indexSelected);

                    }
                    else if(selectedItems.contains(indexSelected)) {
                        //http://stackoverflow.com/questions/21795376/java-how-to-remove-an-integer-item-in-an-arraylist
                        selectedItems.removeAll(Arrays.asList(indexSelected));
                    }

                }
            }).setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int id) {


                            /*if(selectedItems.size() == completionDates.size()) {
                                habitList.remove(habit);
                            }*/

                            //http://stackoverflow.com/questions/13171459/convert-object-arraylist-object-to-integer
                            if(!selectedItems.isEmpty()) {
                                for (int i = 0; i < selectedItems.size(); i++) {

                                    Integer temp = (Integer) selectedItems.get(i);
                                    listItems.remove(temp);

                                   while (temp >= completionDates.size()) {
                                        temp = temp -1;
                                    }


                                    // remove completion from string list and habit
                                    // set count to be one less
                                    completionDates.remove(temp);
                                    habit.setCount(habit.getCount() - 1);
                                    habit.removeCompletion(temp);

                                    adapter.notifyDataSetChanged();

                                }
                            }
                            habit.setCompletedRecord(completionDates);
                            habitArrayList.updateHabitList(habitList);
                            adapter.notifyDataSetChanged();
                        }



                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            // Do nothing

                        }
                    }).create();

                    builder.show();
    }



    // Disable Menu For Second Activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
