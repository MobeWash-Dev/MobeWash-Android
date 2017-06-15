package com.mobewash.mobewash.booking;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.mobewash.mobewash.R;

public class DetailsActivity extends AppCompatActivity implements DetailsFragment.OnDetailsFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @Override
    public void onDetailsFragmentInteraction() {
        Intent paymentIntent = new Intent(DetailsActivity.this, PaymentActivity.class);
        startActivity(paymentIntent);
    }

}
