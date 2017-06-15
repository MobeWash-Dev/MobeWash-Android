package com.mobewash.mobewash.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mobewash.mobewash.R;
import com.stripe.android.model.Card;

public class PaymentActivity extends AppCompatActivity
        implements PaymentFragment.OnPaymentFragmentInteractionListener {

    // Set up tag for logging
    public static final String TAG = "Payment Activity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @Override
    public void onBookPressed(Card card) {
        Intent bookedIntent = new Intent(PaymentActivity.this, BookedActivity.class);
        bookedIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(bookedIntent);
    }
}
