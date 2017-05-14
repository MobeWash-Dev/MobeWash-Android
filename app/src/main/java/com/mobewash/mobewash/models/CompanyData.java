package com.mobewash.mobewash.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sidney on 5/14/17.
 */

public class CompanyData {
    private int uid;
    private String name;
    private String location;
    private Setting setting;

    private CompanyData(JSONObject jsonObject) throws JSONException {
        this.uid = jsonObject.getInt("uid");
        this.name = jsonObject.getString("name");
        this.location = jsonObject.getString("location");
        this.setting = new Setting(jsonObject);
    }

    private class Setting {
        private Range opDays;
        private int washCap;
        private Range range;

        Setting(JSONObject jsonObject) throws JSONException {
            JSONArray opDaysArray = jsonObject.getJSONArray("opDays");
            this.opDays = new Range(opDaysArray);
            this.washCap = jsonObject.getInt("washCap");
            JSONArray rangeArray = jsonObject.getJSONArray("range");
            this.range = new Range(rangeArray);
        }
    }

    private class Range {
        private int start;
        private int end;

        Range(JSONArray jsonArray) throws JSONException {
            this.start = jsonArray.getInt(0);
            this.end = jsonArray.getInt(1);
        }
    }

    public static ArrayList<CompanyData> parseCompanyDataList(JSONArray jsonArray) {
        ArrayList<CompanyData> companyDataList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                companyDataList.add(new CompanyData(object));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  companyDataList;
    }
}
