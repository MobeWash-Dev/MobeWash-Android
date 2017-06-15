package com.mobewash.mobewash.adapters;

/**
 * Created by sahib grover on 5/18/17.
 */
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobewash.mobewash.R;
import com.mobewash.mobewash.models.Appointment;
import com.mobewash.mobewash.models.SlotTime;

import java.util.ArrayList;


public class CustomTimeAdapter extends ArrayAdapter<SlotTime>{


    public CustomTimeAdapter(@NonNull Context context, ArrayList<SlotTime> slots) {
        super(context, R.layout.custom_time_layout,slots);
    }
    private static final String[] TIMES = {"12:00am","1:00am","2:00am","3:00am","4:00am","5:00am","6:00am",
            "7:00am","8:00am","9:00am","10:00am","11:00am","12:00pm","1:00pm","2:00pm","3:00pm","4:00pm",
            "5:00pm","6:00pm","7:00pm","8:00pm","9:00pm","10:00pm","11:00pm"};
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater timeInflater = LayoutInflater.from(getContext());
        View customView = timeInflater.inflate(R.layout.custom_time_layout,parent,false);

        SlotTime slot = getItem(position);
        String timeItem = TIMES[slot.getTime()];
        TextView timeView = (TextView)customView.findViewById(R.id.textView3);
        timeView.setText(timeItem);
        TextView countView = (TextView) customView.findViewById(R.id.textview_slots);
        countView.setText("" + slot.getCount());
        return customView;
    }
}
