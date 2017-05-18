package com.mobewash.mobewash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class DateTimeActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {


    private EditText dateEditText;
    private EditText timeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                DateTimeActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        int[] enabledDays = {2,3};
        Calendar[] enabledDates = new Calendar[enabledDays.length*100];
        Calendar firstDate = addEnabledDates(enabledDates,enabledDays);
        dpd.setSelectableDays(enabledDates);


        EditText dateEditText = (EditText)findViewById(R.id.dateEditText);
        //EditText timeEditText = (EditText)findViewById(R.id.timeEditText);
        this.dateEditText = dateEditText;
        //this.timeEditText = timeEditText;
        dateEditText.setText("" + firstDate.get(Calendar.DATE) + "/" + (firstDate.get(Calendar.MONTH) + 1) + "/" + firstDate.get(Calendar.YEAR));

        dpd.show(getFragmentManager(),"Date Picker");
        dateEditText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(),"Date Picker");
            }
        });

        populateTimes();

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        dateEditText.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
        populateTimes();
    }

    private void populateTimes() {
        //Retrive times from script and format into strings

        String[] myTimes = { "10:00am", "12:00pm", "6:00pm" };
        ListAdapter timeAdapter= new customTimeAdapter(this,myTimes);
        ListView timeListView = (ListView)findViewById(R.id.timeListView);
        timeListView.setAdapter(timeAdapter);
    }

    private Calendar addEnabledDates(Calendar[] enabledDates, int[] enabledDays) {

        //our format of JSON file has 0-6 for Monday to Sunday which isn't desirable
        boolean foundFirstDate = false;
        Calendar firstDate = Calendar.getInstance();
        boolean[] formattedEnabledDays = new boolean[7];
        for(int i = 0; i < enabledDays.length ; i++) {
            if(enabledDays[i] == 6)
                formattedEnabledDays[0] = true;
            else
                formattedEnabledDays[enabledDays[i]+1] = true;
        }//set formattedEnabledDays to be true
        for(int i = 0; i < 7; i++) {
            Log.d("myTag","" + i + " " + formattedEnabledDays[i]);
        }
        int counter = 0;
        for(int i = 0; i < 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,i);
            if(formattedEnabledDays[cal.get(Calendar.DAY_OF_WEEK)-1]) {
                if(!foundFirstDate) {
                    foundFirstDate = true;
                    firstDate = cal;
                }
                for (int j = 0; j < 100; j++) {
                    Calendar calToAdd = Calendar.getInstance();
                    calToAdd.add(Calendar.DATE,i);
                    calToAdd.add(Calendar.DATE,j*7);
                    enabledDates[counter] = calToAdd;
                    counter++;
                }
            }
        }
        return firstDate;
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        String ampm = "am";
        if( hourOfDay >= 12 ){
            hourOfDay = hourOfDay-12;
            ampm = "pm";
        }
        if (hourOfDay == 12)
            hourOfDay+=12;
        timeEditText.setText("" + hourOfDay + ":" + minute + "/" + ampm);
    }
}

