package com.mobewash.mobewash.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sidney on 5/14/17.
 */

public class WashService {
    private String serviceName;
    private int paymentAmount;
    private String duration;
    private String description;
    private String additionalService;
    private String additionalPrice;
    private int appointmentTypeID;

    public WashService(JSONObject jsonObject) throws JSONException {
        this.serviceName = jsonObject.getString("serviceName");
        this.duration = jsonObject.getString("duration");
        this.paymentAmount = jsonObject.getInt("paymentAmount");
        this.description = jsonObject.getString("description");
        if (jsonObject.has("additionalService")) {
            this.additionalService = jsonObject.getString("additionalService");
        }
        this.additionalPrice = jsonObject.getString("additionalPrice");
        if (serviceName.equals("MobePlus")) {
            this.appointmentTypeID = 2944155;
        } else {
            this.appointmentTypeID = 2925879;
        }
    }

//    public Service toService(){
//
//        return
//    }

    @Override
    public String toString() {
        return "{\n"
                + "serviceName: " + getServiceName() + "\n"
                + "duration: " + getDuration() + "\n"
                + "paymentAmount: " + getPaymentAmount() + "\n"
                + "description: " + getDescription() + "\n"
                + "additionalService: " + getAdditionalService() + "\n"
                + "additionalPrice: " + getAdditionalPrice() + "\n"
                + "appointmentTypeID: " + getAppointmentTypeID() + "}";
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

    public String getServiceName() {
        return serviceName;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public String getDuration() {
        return duration;
    }


    public String getDescription() {
        return description;
    }

    public String getAdditionalService() {
        return additionalService;
    }

    public String getAdditionalPrice() {
        return additionalPrice;
    }

    public int getAppointmentTypeID() {
        return appointmentTypeID;
    }
}