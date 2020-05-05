package com.restaurant.advisor.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

public class RestaurantNetworkManager extends AndroidViewModel {


    MutableLiveData<com.restaurant.advisor.model.Response> mData = new MutableLiveData<>();;
    MutableLiveData<com.restaurant.advisor.model.Restaurant> mRestaurant = new MutableLiveData<>();;
    Context mContext;

    public RestaurantNetworkManager(@NonNull Application application) {
        super(application);
        mContext = application;

    }

    public MutableLiveData<com.restaurant.advisor.model.Response> sendQuery(String pincode, String keyword) {

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
                        mData.setValue(m);
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly Error", error.toString());

                NetworkResponse networkResponse = error.networkResponse;
                if (networkResponse != null)
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


    public MutableLiveData<com.restaurant.advisor.model.Restaurant> getAdditionalData(String id) {
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
                        mRestaurant.setValue(m);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volly Error", error.toString());

                NetworkResponse networkResponse = error.networkResponse;
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
