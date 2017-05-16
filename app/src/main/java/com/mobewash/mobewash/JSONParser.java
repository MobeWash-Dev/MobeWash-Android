package com.mobewash.mobewash;

import com.mobewash.mobewash.models.BookingData;
import com.mobewash.mobewash.models.CompanyData;
import com.mobewash.mobewash.models.WashService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by sidney on 5/14/17.
 */

public class JSONParser {
    public static ArrayList<CompanyData> parseCompanyData(JSONArray jsonArray) {
        return CompanyData.parseCompanyDataList(jsonArray);
    }

    public static ArrayList<WashService> parseWashService(JSONArray jsonArray) {
        return WashService.parseWashServiceList(jsonArray);
    }
}
