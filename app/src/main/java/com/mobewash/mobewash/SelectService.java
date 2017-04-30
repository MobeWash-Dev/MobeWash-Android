package com.mobewash.mobewash;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;

public class SelectService extends ListActivity {

    private String[] descriptionContent = {
            "The Full Exterior Car Wash Service 55 mins",
            "The Full Exterior Car Wash Service 35 mins"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_service);
        this.setListAdapter(new ArrayAdapter<String>(this, R.layout.service_list, R.id.service_name, descriptionContent));
    }

}
