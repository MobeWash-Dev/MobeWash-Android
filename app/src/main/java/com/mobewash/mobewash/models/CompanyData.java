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
                + "name: "  + getName() + "\n"
                + "location: " + getLocation() + "\n"
                + "setting: " + getSetting() + "\n}";
    }

    private class Setting {
        private int[] opDays;
        private int washCap;
        private int[] range;

        Setting(JSONObject jsonObject) throws JSONException {
            JSONArray opDaysArray = jsonObject.getJSONArray("opDays");
            this.opDays = new int[opDaysArray.length()];
            for (int i = 0; i < opDaysArray.length(); i++) {
                this.opDays[i] = opDaysArray.getInt(i);
            }
            this.washCap = jsonObject.getInt("washCap");
            JSONArray rangeArray = jsonObject.getJSONArray("range");
            this.range = new int[rangeArray.length()];
            for (int i = 0; i < rangeArray.length(); i++) {
                this.range[i] = rangeArray.getInt(i);
            }
        }

        public int[] getOpDays() {
            return opDays;
        }

        public int getWashCap() {
            return washCap;
        }

        public int[] getRange() {
            return range;
        }

        @Override
        public String toString() {
            String opDaysString = "[" + getOpDays()[0];
            for (int i = 1; i < getOpDays().length; i++) {
                opDaysString += ", " + getOpDays()[i];
            }
            opDaysString += "]";
            String rangeString = "[" + getRange()[0];
            for (int i = 1; i < getRange().length; i++) {
                rangeString += ", " + getRange()[i];
            }
            rangeString += "]";
            return "{\n"
                    + "opDays: " + opDaysString + "\n"
                    + "washCap: " + getWashCap() + "\n"
                    + "range: " + rangeString + "\n}";
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
