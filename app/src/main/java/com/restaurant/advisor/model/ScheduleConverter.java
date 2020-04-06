package com.restaurant.advisor.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ScheduleConverter {
    Gson mGson = new Gson();

    @TypeConverter
    public ArrayList<Schedule> getScheduleListFromJson(String json) {

        ArrayList<Schedule> schedule = new ArrayList<>();
        JsonElement element = mGson.fromJson(json, JsonElement.class);
        if (element.isJsonObject()) {
            /*JsonObject jsonObject =(JsonObject) mGson.fromJson(json, JsonObject.class);*/
            JsonArray jsonArray =((JsonObject)element).getAsJsonArray("open");
            Type type = new TypeToken<ArrayList<Schedule>>() {
            }.getType();
            schedule = mGson.fromJson(jsonArray, type);
        }
        return schedule;
    }

    @TypeConverter
    public String getJsonFromList(ArrayList<Schedule> schedule) {
        return mGson.toJson(schedule);
    }
}
