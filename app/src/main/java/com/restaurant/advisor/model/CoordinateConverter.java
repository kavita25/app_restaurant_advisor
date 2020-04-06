package com.restaurant.advisor.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CoordinateConverter {
    public static Gson gson = new Gson();

    @TypeConverter
    public static Coordinates StringTocoordinatesList(String str){

        Type type = new TypeToken<Coordinates>(){}.getType();
        Coordinates list = gson.fromJson(str,type);
        return list;
    }
    @TypeConverter
    public static String coordinateListToString(Coordinates list){
        return gson.toJson(list);
    }
}
