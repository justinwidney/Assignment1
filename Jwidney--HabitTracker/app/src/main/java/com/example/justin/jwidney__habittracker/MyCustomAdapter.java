package com.example.justin.jwidney__habittracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by justin on 28/09/16.
 */
public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list = new ArrayList<String>();
    private ArrayList<Habit> habitList = new ArrayList<>();
    private Context context;
    private CharSequence firsttime = "Habit Completed // First Time";
    private int duration = Toast.LENGTH_SHORT;
    private boolean[] days = new boolean[7];
    private int dayOfWeek;


    public MyCustomAdapter(ArrayList<Habit> habitlist, int dayOfWeek, Context context) {
        this.dayOfWeek = dayOfWeek;
        this.habitList = habitlist;
        for (Habit tempHabit: habitlist) {
            if (tempHabit.getDayOfWeek(dayOfWeek) == true) {
            if(!list.contains(tempHabit)) {
                list.add(tempHabit.getName());
             }
            }

        }
        this.context = context;
    }




    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }


    // http://stackoverflow.com/questions/17525886/listview-with-add-and-delete-buttons-in-each-row-in-android
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.habit_list_layout, parent, false);

        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));


        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        Button addBtn = (Button)view.findViewById(R.id.increment_btn);



        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               String removedName = list.remove(position); //remove from view

               for (Habit habit : habitList) {              //remove from habit
                    if(habit.getName() == removedName) {
                        habitList.remove(habit);
                    }
                }
                notifyDataSetChanged();

            }
        });
        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                for (Habit habit : habitList) {
                    if(list.get(position)== habit.getName() ) {

                        int clickcount = habit.getCount();
                        if(clickcount==0) {
                            Toast completedtoast = Toast.makeText(context, firsttime, duration);
                            completedtoast.show();
                        }
                        else {
                            Toast incrementedtoast = Toast.makeText(context,"Completed, Completion count =" + (habit.getCount() +1),duration);
                            incrementedtoast.show();
                        }

                        habit.addCount();
                        habit.addCompletion();


                    }
                }

                //habit.addCount();
                // habit.addCompletion();
                notifyDataSetChanged();
            }
        });

        return view;
    }


    // Add the habit to the list instantly

    public void notifyDataList(String habit) {
        super.notifyDataSetChanged();
        list.add(habit);
    }

}
