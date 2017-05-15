package com.mobewash.mobewash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mobewash.mobewash.models.CompanyData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonTestActivity extends AppCompatActivity {
    private static String TAG = "JsonTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_test);
        RestRequester restRequester = new RestRequester(JsonTestActivity.this);
        restRequester.get("http://10.0.2.2:3000/", new RestRequester.OnRequestCompleteListener() {
            @Override
            public void onRequestComplete(Exception err, JSONObject jsonObject) {
                if (err != null) {
                    Log.e(TAG, "REQUEST ERROR", err);
                } else {
                    Log.d(TAG, jsonObject.toString());
                }
            }
        });

        restRequester.getArray("http://10.0.2.2:3000/companydata", new RestRequester.OnArrayRequestCompleteListener() {
            @Override
            public void onArrayRequestComplete(Exception err, JSONArray jsonArray) {
                if (err != null) {
                    Log.e(TAG, "ARRAY REQUEST ERROR", err);
                } else {
                    Log.d(TAG, jsonArray.toString());
                    ArrayList<CompanyData> companyDataList = JSONParser.parseCompanyData(jsonArray);
                    Log.d(TAG, companyDataList.toString());
                }
            }
        });
    }
}
