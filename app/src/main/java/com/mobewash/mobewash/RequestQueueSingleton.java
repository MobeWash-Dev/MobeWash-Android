package com.mobewash.mobewash;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by sidney on 5/14/17.
 */

public class RequestQueueSingleton {
    private static RequestQueueSingleton INSTANCE = null;
    private RequestQueue requestQueue;

    private RequestQueueSingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueueSingleton getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RequestQueueSingleton(context);
        }
        return INSTANCE;
    }

    public void addToRequestQueue(JsonObjectRequest jsObjRequest) {
        requestQueue.add(jsObjRequest);
    }

    public void addToRequestQueue(JsonArrayRequest jsonArrayRequest) {
        requestQueue.add(jsonArrayRequest);
    }
}
