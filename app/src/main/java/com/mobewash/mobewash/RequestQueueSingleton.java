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
    private static RequestQueue INSTANCE = null;

    public RequestQueueSingleton(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Volley.newRequestQueue(context);
        }
    }

    public void addToRequestQueue(JsonObjectRequest jsObjRequest) {
        INSTANCE.add(jsObjRequest);
    }

    public void addToRequestQueue(JsonArrayRequest jsonArrayRequest) {
        INSTANCE.add(jsonArrayRequest);
    }
}
