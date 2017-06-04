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

    public class Setting {
        private int[] washDays;
        private int slotCap;
        private int washCap;
        private int startTime;
        private int endTime;
        private int increment;

        Setting(JSONObject jsonObject) throws JSONException {
            JSONArray washDaysArray = jsonObject.getJSONArray("washDays");
            this.washDays = new int[washDaysArray.length()];
            for (int i = 0; i < washDaysArray.length(); i++) {
                this.washDays[i] = washDaysArray.getInt(i);
            }
            this.slotCap = jsonObject.getInt("slotCap");
            JSONObject slotSetting = jsonObject.getJSONObject("slotSetting");
            this.washCap = slotSetting.getInt("washCap");
            this.startTime = slotSetting.getInt("startTime");
            this.endTime = slotSetting.getInt("endTime");
            this.increment = slotSetting.getInt("increment");
        }

        public int[] getWashDays() {
            return washDays;
        }

        public int getslotCap() {
            return slotCap;
        }


        @Override
        public String toString() {
            String washDaysString = "[" + getWashDays()[0];
            for (int i = 1; i < getWashDays().length; i++) {
                washDaysString += ", " + getWashDays()[i];
            }
            washDaysString += "]";

            return "{\n"
                    + "washDays: " + washDaysString + "\n"
                    + "slotCap: " + getslotCap() + "\n";
        }

        public int getWashCap() {
            return washCap;
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public int getIncrement() {
            return increment;
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