package com.mobewash.mobewash.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sidney on 6/3/17.
 */

public class Appointment {
    private String category;
    private String time;
    private String endTime;

    public Appointment(JSONObject jsonObject) throws JSONException {
        this.category = jsonObject.getString("category");
        this.time = jsonObject.getString("time");
        this.endTime = jsonObject.getString("endTime");
    }

    public static ArrayList<Appointment> parseAppointmentList(JSONArray jsonArray) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                appointments.add(new Appointment(object));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return appointments;
    }

    public String getCategory() {
        return category;
    }

    public String getTime() {
        return time;
    }

    public String getEndTime() {
        return endTime;
    }
}
