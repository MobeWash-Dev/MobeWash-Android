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
        JSONObject settingsJson = jsonObject.getJSONObject("setting");
        this.setting = new Setting(settingsJson);
    }

    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Setting getSetting() {
        return setting;
    }

    @Override
    public String toString() {
        return "{\n"
                + "uid: " + getUid() + "\n"
                + "name:"  + getName() + "\n"
                + "location: " + getLocation() + "\n"
                + "setting: " + getSetting() + "\n}";
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

        public Range getOpDays() {
            return opDays;
        }

        public int getWashCap() {
            return washCap;
        }

        public Range getRange() {
            return range;
        }

        @Override
        public String toString() {
            return "{\n"
                    + "opDays: " + getOpDays() + "\n"
                    + "washCap: " + getWashCap() + "\n"
                    + "range: " + getRange() + "\n}";
        }
    }

    private class Range {
        private int start;
        private int end;

        Range(JSONArray jsonArray) throws JSONException {
            this.start = jsonArray.getInt(0);
            if (jsonArray.length() > 1) {
                this.end = jsonArray.getInt(1);
            } else {
                this.end = this.start;
            }
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return "[" + getStart() + ", " + getEnd() + "]";
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
