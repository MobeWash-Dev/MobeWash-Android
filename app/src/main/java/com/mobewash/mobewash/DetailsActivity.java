package com.mobewash.mobewash;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

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
