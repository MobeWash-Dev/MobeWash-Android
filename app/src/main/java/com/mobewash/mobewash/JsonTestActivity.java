package com.mobewash.mobewash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONObject;

public class JsonTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);
        RestRequester restRequester = new RestRequester(JsonTestActivity.this);
        restRequester.get("http://10.0.2.2:3000/", new RestRequester.OnRequestCompleteListener() {
            @Override
            public void onRequestComplete(Exception err, JSONObject jsonObject) {

            }
        });
    }
}
