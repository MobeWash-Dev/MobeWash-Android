package com.mobewash.mobewash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SelectServiceActivity extends AppCompatActivity implements SelectServiceFragment.OnSelectServiceFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
    }

    @Override
    public void onServiceSelect() {
        Intent calendarIntent = new Intent(SelectServiceActivity.this, DateTimeActivity.class);
        startActivity(calendarIntent);
    }
}
