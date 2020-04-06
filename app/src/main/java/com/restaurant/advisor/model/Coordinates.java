package com.restaurant.advisor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "coordinates",primaryKeys ={"latitude","longitude"})
public class Coordinates {

    @NonNull
    @ColumnInfo(name = "latitude")
    @SerializedName("latitude")
    private String mLatitude;

    @NonNull
    @ColumnInfo(name = "longitude")
    @SerializedName("longitude")
    private String mLngitude;

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }

    public String getLngitude() {
        return mLngitude;
    }

    public void setLngitude(String mLngitude) {
        this.mLngitude = mLngitude;
    }
}
