package com.restaurant.advisor.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LocationConverter {
   private static Gson gson = new Gson();

    @TypeConverter
    public static Location StringToLocationList(String data) {
      /*  if (data == null) {
            return Collections.emptyList();
        }*/

        Type listType = new TypeToken<Location>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String LocationtListToString(Location someObjects) {
        return gson.toJson(someObjects);
    }
}
