package com.mobewash.mobewash;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.stripe.android.model.Card;

public class MainActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener,
        DetailsFragment.OnDetailsFragmentInteractionListener,
        PaymentFragment.OnPaymentFragmentInteractionListener,
        BookedFragment.OnBookedFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        DetailsFragment detailsFragment = new DetailsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout_main_fragment_container, detailsFragment).commit();
    }

    @Override
    public void onBackStackChanged() {
        attemptDisplayHomeButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        attemptDisplayHomeButton();
        return true;
    }

    /**
     * Only displays up button if parent exists.
     */
    private void attemptDisplayHomeButton() {
        boolean canBack = getSupportFragmentManager().getBackStackEntryCount() > 0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(canBack);
    }

    /**
     * Handles user log out
     */
    private void logout() {
        LoginManager.getInstance().logOut();

        // TODO clear local user data

        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    @Override
    public void onDetailsFragmentInteraction() {
        PaymentFragment paymentFragment = new PaymentFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_main_fragment_container, paymentFragment)
                .addToBackStack("payment").commit();
    }

    @Override
    public void onBookPressed(Card card) {
        BookedFragment bookedFragment = new BookedFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_main_fragment_container, bookedFragment)
                .addToBackStack("booked").commit();
    }

    @Override
    public void onBookedFragmentInteraction() {

    }

    @Override
    public void onLogoutPressed() {
        logout();
    }
}
