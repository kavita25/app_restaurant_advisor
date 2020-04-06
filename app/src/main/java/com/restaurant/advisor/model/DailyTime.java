package com.restaurant.advisor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class DailyTime {
    @ColumnInfo(name = "is_overnight")
    @SerializedName("is_overnight")
    private String mIsOvernight;

    @ColumnInfo(name = "start")
    @SerializedName("start")
    private String mStart;

    @ColumnInfo(name = "end")
    @SerializedName("end")
    private String mEnd;

    @NonNull
    @ColumnInfo(name = "day")
    @SerializedName("day")
    private String mDay;
    public String getIsOvernight() {
        return mIsOvernight;
    }

    public void setIsOvernight(String mIsOvernight) {
        this.mIsOvernight = mIsOvernight;
    }

    public String getStart() {
        return mStart;
    }

    public void setStart(String mStart) {
        this.mStart = mStart;
    }

    public String getEnd() {
        return mEnd;
    }

    public void setEnd(String mEnd) {
        this.mEnd = mEnd;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String mDay) {
        this.mDay = mDay;
    }
}
