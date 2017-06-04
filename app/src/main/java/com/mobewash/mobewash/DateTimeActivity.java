package com.mobewash.mobewash;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.EditText;

public class DateTimeActivity extends AppCompatActivity implements DateTimeFragment.OnDateTimeFragmentInteractionListener {


    private EditText dateEditText;
    private EditText timeEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);


    }

    @Override
    public void onDateTimeSelected() {

    }

    @Override
    public void onDateTimeAttach(DateTimeFragment dateTimeFragment) {

    }
}

