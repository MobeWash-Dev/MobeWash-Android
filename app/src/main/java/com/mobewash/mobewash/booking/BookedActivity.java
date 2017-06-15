package com.mobewash.mobewash.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobewash.mobewash.LandingActivity;
import com.mobewash.mobewash.R;

public class BookedActivity extends AppCompatActivity implements BookedFragment.OnBookedFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);
    }

    @Override
    public void onReturnPressed() {
        Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
