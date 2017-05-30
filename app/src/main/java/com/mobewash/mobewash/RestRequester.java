package com.mobewash.mobewash;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sidney on 5/14/17.
 */

public class RestRequester {
    private RequestQueueSingleton mRequestQueue;

    public RestRequester(Context context) {
        mRequestQueue = new RequestQueueSingleton(context);
    }

    public DataSingletonClass sharedData = DataSingletonClass.getInstance();

    public void get(String url, final OnRequestCompleteListener listener) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onRequestComplete(null, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestComplete(error, null);
            }
        });
        mRequestQueue.addToRequestQueue(jsonObjectRequest);
    }

    public void getArray(String url, final OnArrayRequestCompleteListener listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                listener.onArrayRequestComplete(null, response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onArrayRequestComplete(error, null);
            }
        });
        mRequestQueue.addToRequestQueue(jsonArrayRequest);
    }

    public void postPayment(String url, final OnRequestCompleteListener listener) {
        JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestComplete(null, response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onRequestComplete(error, null);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("card", sharedData.getToken().getId());
                params.put("currency","usd");
                params.put("description", sharedData.getSelectedService().getTitle());
                params.put("amount", sharedData.getCharge());
                return params;
            }

        };
        mRequestQueue.addToRequestQueue(putRequest);
    }

    interface OnRequestCompleteListener {
        void onRequestComplete(Exception err, JSONObject jsonObject);
    }

    interface OnArrayRequestCompleteListener {
        void onArrayRequestComplete(Exception err, JSONArray jsonArray);
    }

}
