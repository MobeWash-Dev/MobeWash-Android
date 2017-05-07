package com.mobewash.mobewash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectService extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_service);
        mListView = (ListView) findViewById(R.id.service_list_view);
        Service mobewash = new Service("Mobe", "The full exterior car wash service 55min", "24");
        Service mobewashPlus = new Service("MobePlus", "The full exterior and interior car wash service", "35");
        ArrayList<Service> services = new ArrayList<Service>();
        services.add(mobewash);
        services.add(mobewashPlus);
        ServiceListAdapter serviceListAdapter = new ServiceListAdapter(this, services);
        mListView.setAdapter(serviceListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Service result = (Service) parent.getItemAtPosition(position);
                Intent mainIntent = new Intent(SelectService.this, MainActivity.class);
                startActivity(mainIntent);
            }
        });



    }
}
