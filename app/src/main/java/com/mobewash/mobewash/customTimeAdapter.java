package com.mobewash.mobewash;

/**
 * Created by sahib grover on 5/18/17.
 */
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class customTimeAdapter extends ArrayAdapter<String>{


    public customTimeAdapter(@NonNull Context context, String[] times) {
        super(context, R.layout.custom_time_layout,times);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater timeInflater = LayoutInflater.from(getContext());
        View customView = timeInflater.inflate(R.layout.custom_time_layout,parent,false);

        String timeItem = getItem(position);
        TextView timeView = (TextView)customView.findViewById(R.id.textView3);

        timeView.setText(timeItem);
        return customView;
    }
}
