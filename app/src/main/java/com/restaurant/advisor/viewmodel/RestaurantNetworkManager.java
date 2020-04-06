package com.restaurant.advisor.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.Menu;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.restaurant.advisor.Config;
import com.restaurant.advisor.interfaces.RestaurantListListner;
import com.restaurant.advisor.model.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantNetworkManager {

    com.restaurant.advisor.model.Response mData;
    Restaurant mRestaurant;
    RestaurantListListner mListner;
    Context mContext;

    public RestaurantNetworkManager(Context application, RestaurantListListner listner) {
        mContext = application;
        mListner = listner;

    }

    public com.restaurant.advisor.model.Response sendQuery(String pincode, String keyword) {

        //
        // Check in db if not present then get from server
        String additionalParam = "";
        if (pincode != null && pincode.length() > 0) additionalParam = "location=" + pincode;
        else {
            additionalParam = "location=" + keyword;
        }

        System.out.println("FROM SERVER==============================");
        System.out.println(pincode + "  " + keyword + "  ");
        RequestQueue queue = Volley.newRequestQueue(mContext.getApplicationContext());
//        String url = "https://api.github.com/search/repositories?q=" + text + "+language:assembly&sort=stars";
        String url = Config.YelpURL_SEARCH + "&" + additionalParam;
        Log.i("API-req", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        com.restaurant.advisor.model.Response m = new Gson().fromJson(response, com.restaurant.advisor.model.Response.class);
                        mListner.onResponseReceived(m);
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly Error", error.toString());

                NetworkResponse networkResponse = error.networkResponse;
                mListner.onError(error);
                if(networkResponse != null)
                Log.e("Status code", String.valueOf(networkResponse.statusCode));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", Config.BEARER_API_Key);
                return param;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        // }
        return mData;
    }


    public com.restaurant.advisor.model.Restaurant getAdditionalData(String id) {
        System.out.println("FROM SERVER==============================");
        System.out.println("ID " + id);
        RequestQueue queue = Volley.newRequestQueue(mContext.getApplicationContext());
        String url = Config.YelpURL + id;
        Log.i("API-req", url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("com.restaurant.advisor.model.Response :: getAdditionalData " + response);
                        com.restaurant.advisor.model.Restaurant m = new Gson().fromJson(response, com.restaurant.advisor.model.Restaurant.class);

                        mListner.onRestauranDataReceived(m);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly Error", error.toString());

                NetworkResponse networkResponse = error.networkResponse;
                mListner.onError(error);
                Log.e("Status code", String.valueOf(networkResponse.statusCode));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("Authorization", Config.BEARER_API_Key);
                return param;
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        return mRestaurant;
    }


}
