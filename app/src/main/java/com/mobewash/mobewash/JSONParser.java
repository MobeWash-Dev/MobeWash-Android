package com.mobewash.mobewash;

import com.mobewash.mobewash.models.CompanyData;

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
}
