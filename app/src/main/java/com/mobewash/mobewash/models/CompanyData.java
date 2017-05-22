package com.mobewash.mobewash.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sidney on 5/14/17.
 */

public class CompanyData {
    private int id;
    private String name;
    private String address;
    private Setting setting;

    private CompanyData(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getInt("id");
        this.name = jsonObject.getString("name");
        this.address = jsonObject.getString("address");
        JSONObject settingsJson = jsonObject.getJSONObject("setting");
        this.setting = new Setting(settingsJson);
    }

    public int getid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getaddress() {
        return address;
    }

    public Setting getSetting() {
        return setting;
    }

    @Override
    public String toString() {
        return "{\n"
                + "id: " + getid() + "\n"
                + "name: "  + getName() + "\n"
                + "address: " + getaddress() + "\n"
                + "setting: " + getSetting() + "\n}";
    }

    private class Setting {
        private int[] washDays;
        private int slotCap;

        Setting(JSONObject jsonObject) throws JSONException {
            JSONArray washDaysArray = jsonObject.getJSONArray("washDays");
            this.washDays = new int[washDaysArray.length()];
            for (int i = 0; i < washDaysArray.length(); i++) {
                this.washDays[i] = washDaysArray.getInt(i);
            }
            this.slotCap = jsonObject.getInt("slotCap");

        }

        public int[] getwashDays() {
            return washDays;
        }

        public int getslotCap() {
            return slotCap;
        }


        @Override
        public String toString() {
            String washDaysString = "[" + getwashDays()[0];
            for (int i = 1; i < getwashDays().length; i++) {
                washDaysString += ", " + getwashDays()[i];
            }
            washDaysString += "]";

            return "{\n"
                    + "washDays: " + washDaysString + "\n"
                    + "slotCap: " + getslotCap() + "\n";
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