package com.mobewash.mobewash;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CompanyActivity extends AppCompatActivity implements CompanyFragment.OnCompanyFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
    }

    @Override
    public void onCompanySelected() {
        Intent serviceIntent = new Intent(CompanyActivity.this, SelectServiceActivity.class);
        startActivity(serviceIntent);
    }
}
