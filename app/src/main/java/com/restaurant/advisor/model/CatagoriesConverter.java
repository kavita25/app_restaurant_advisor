package com.restaurant.advisor.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CatagoriesConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Categories> StringToCategoriesList(String str) {
        Type type = new TypeToken<List<Categories>>() {
        }.getType();
        List<Categories> list = gson.fromJson(str, type);
        return list;
    }
    @TypeConverter
    public static String CategoriesListToString(List<Categories> list) {
        return gson.toJson(list);
    }
}
