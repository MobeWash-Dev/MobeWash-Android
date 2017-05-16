package com.mobewash.mobewash;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sidney on 5/14/17.
 */

public class RestRequester {
    private RequestQueueSingleton mRequestQueue;

    public RestRequester(Context context) {
        mRequestQueue = new RequestQueueSingleton(context);
    }

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

    interface OnRequestCompleteListener {
        void onRequestComplete(Exception err, JSONObject jsonObject);
    }

    interface OnArrayRequestCompleteListener {
        void onArrayRequestComplete(Exception err, JSONArray jsonArray);
    }
}
