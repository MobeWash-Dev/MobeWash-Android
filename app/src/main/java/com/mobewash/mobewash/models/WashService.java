package com.mobewash.mobewash.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by sidney on 5/14/17.
 */

public class WashService {
    private int uid;
    private int appointmentTypeID;
    private String name;
    private int cost;
    private int extraCost;
    private int length;
    private int extraLength;
    private String[] info;

    public WashService(JSONObject jsonObject) throws JSONException {
        this.uid = jsonObject.getInt("uid");
        this.appointmentTypeID = jsonObject.getInt("appointmentTypeID");
        this.name = jsonObject.getString("name");
        this.cost = jsonObject.getInt("cost");
        this.extraCost = jsonObject.getInt("extraCost");
        this.length = jsonObject.getInt("length");
        this.extraLength = jsonObject.getInt("extraLength");
        JSONArray infoArray = jsonObject.getJSONArray("info");
        this.info = new String[infoArray.length()];
        for (int i = 0; i < infoArray.length(); i++) {
            this.info[i] = infoArray.getString(i);
        }
    }

    @Override
    public String toString() {
        String infoString = "[" + getInfo()[0];
        for (int i = 0; i < getInfo().length; i++) {
            infoString += ", " + getInfo()[i];
        }
        infoString += "]";
        return "{\n"
                + "uid: " + getUid() + "\n"
                + "appointmentTypeID: " + getAppointmentTypeID() + "\n"
                + "name: " + getName() + "\n"
                + "cost: " + getCost() + "\n"
                + "extraCost: " + getExtraCost() + "\n"
                + "length: " + getLength() + "\n"
                + "extraLength: " + getExtraLength() + "\n"
                + "info: " + infoString + "\n}";
    }

    public static ArrayList<WashService> parseWashServiceList(JSONArray jsonArray) {
        ArrayList<WashService> washServiceList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                washServiceList.add(new WashService(object));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return washServiceList;
    }

    public int getUid() {
        return uid;
    }

    public int getAppointmentTypeID() {
        return appointmentTypeID;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getExtraCost() {
        return extraCost;
    }

    public int getLength() {
        return length;
    }

    public int getExtraLength() {
        return extraLength;
    }

    public String[] getInfo() {
        return info;
    }
}