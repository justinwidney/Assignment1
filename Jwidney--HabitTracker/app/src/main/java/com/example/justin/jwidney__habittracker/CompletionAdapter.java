package com.example.justin.jwidney__habittracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by justin on 01/10/16.
 */
public class CompletionAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list;
    private Context context;
    private ArrayList<Habit> habitList;
    private String removedName;

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


    public CompletionAdapter( Context context, ArrayList<String> dataItem,ArrayList<Habit> habitList) {
        this.list = dataItem;
        this.context = context;
        this.habitList = habitList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }


        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.completionsTextView);
        listItemText.setText(list.get(position));

        //Handle buttons
        final Button statsBtn = (Button)view.findViewById(R.id.stats_Btn);
        final Button deleteBtnC = (Button)view.findViewById(R.id.deletebutton);


        statsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Habit habit = habitList.get(position);
                notifyDataSetChanged();
                ((CompletionsActivity)context).displayStats(position);


            }
        });

        deleteBtnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                removedName = list.remove(position); //remove from view

                for (Habit habit : habitList) {
                    if(habit.getName() == removedName) {
                        habitList.remove(habit);
                    }
                }
                notifyDataSetChanged();


            }
        });
        return view;
    }

}