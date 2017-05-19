package com.mobewash.mobewash;

import android.net.Uri;
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

public class DateTimeActivity extends AppCompatActivity implements DateTimeFragment.OnFragmentInteractionListener{


    private EditText dateEditText;
    private EditText timeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

