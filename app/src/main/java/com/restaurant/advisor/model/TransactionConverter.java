package com.restaurant.advisor.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TransactionConverter {
    Gson mGson = new Gson();

    @TypeConverter
    public ArrayList<String> getTransactionListFromJson(String json) {

        ArrayList<String> schedule = new ArrayList<>();
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("transactions");
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        schedule = mGson.fromJson(json, type);
        return schedule;
    }
    @TypeConverter
    public String getTransactionFromList(ArrayList<String> schedule) {
        return mGson.toJson(schedule);
    }
}
