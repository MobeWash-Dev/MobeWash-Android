package com.mobewash.mobewash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SelectServiceActivity extends AppCompatActivity implements SelectServiceFragment.OnSelectServiceFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
    }

    @Override
    public void onServiceSelect() {
        Intent calendarIntent = new Intent(SelectServiceActivity.this, DateTimeActivity2.class);
        startActivity(calendarIntent);
    }
}
