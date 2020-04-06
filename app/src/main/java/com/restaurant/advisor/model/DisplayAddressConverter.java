package com.restaurant.advisor.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DisplayAddressConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static ArrayList<String> StringToDisplayAddressList(String str) {

        //  JsonObject jsonObject = new Gson().fromJson(str, JsonObject.class);
        //JsonArray jsonArray = jsonObject.getAsJsonArray("display_address");
        ArrayList<String> lstName = new ArrayList<>();
        if (str != null) {
            String[] arrName = new Gson().fromJson(str, String[].class);
            lstName = new ArrayList<>(Arrays.asList(arrName));
       /* Type type = new TypeToken<List<String>>() {
        }.getType();
        List<String> list = gson.fromJson(str, type);*/
        }
        return lstName;
    }

    @TypeConverter
    public static String DisplayAddressListToString(List<String> list) {
        return list == null ? null :gson.toJson(list);
    }
}
