package com.restaurant.advisor.model;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

@Entity(tableName = "open_schedule" , primaryKeys = "day")
public class Schedule {

    @SerializedName("open")
    ArrayList<DailyTime> mDailyTime;

    @SerializedName("hours_type")
    String hoursType;

    @SerializedName("is_open_now")
    String  isOpenNow;

    public ArrayList<DailyTime> getmDailyTime() {
        return mDailyTime;
    }

    public void setmDailyTime(ArrayList<DailyTime> mDailyTime) {
        this.mDailyTime = mDailyTime;
    }

    public String getHoursType() {
        return hoursType;
    }

    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }

    public String getIsOpenNow() {
        return isOpenNow;
    }

    public void setIsOpenNow(String isOpenNow) {
        this.isOpenNow = isOpenNow;
    }

}
