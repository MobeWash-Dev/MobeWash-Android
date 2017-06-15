package com.mobewash.mobewash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.facebook.login.LoginManager;
import com.mobewash.mobewash.booking.BookedFragment;
import com.mobewash.mobewash.booking.CompanyFragment;
import com.mobewash.mobewash.booking.DetailsFragment;
import com.mobewash.mobewash.booking.PaymentFragment;
import com.mobewash.mobewash.booking.SelectServiceFragment;
import com.mobewash.mobewash.login.LoginActivity;
import com.stripe.android.model.Card;

public class MainActivity extends AppCompatActivity
        implements FragmentManager.OnBackStackChangedListener,
        DetailsFragment.OnDetailsFragmentInteractionListener,
        PaymentFragment.OnPaymentFragmentInteractionListener,
        BookedFragment.OnBookedFragmentInteractionListener,
        SelectServiceFragment.OnSelectServiceFragmentInteractionListener,
        CompanyFragment.OnCompanyFragmentInteractionListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(this);

        CompanyFragment companyFragment = new CompanyFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.framelayout_main_fragment_container, companyFragment).commit();
    }

    @Override
    public void onBackStackChanged() {
        
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
        return true;
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
    public void onReturnPressed() {
        Intent landingIntent = new Intent(MainActivity.this, LandingActivity.class);
        startActivity(landingIntent);
        finish();
    }

    @Override
    public void onServiceSelect() {
        DetailsFragment detailsFragment = new DetailsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_main_fragment_container, detailsFragment)
                .addToBackStack("details").commit();

    }

    @Override
    public void onCompanySelected() {
        SelectServiceFragment selectServiceFragment = new SelectServiceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_main_fragment_container, selectServiceFragment)
                .addToBackStack("selectservice").commit();
    }
}
