package com.restaurant.advisor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "catagories",primaryKeys = "title")
public class Categories {

    @NonNull
    @ColumnInfo(name = "alise")
    @SerializedName("alise")
    private String mAlise;

    @NonNull
    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String mTitle;

    public String getAlise() {
        return mAlise;
    }

    public void setAlise(String mAlise) {
        this.mAlise = mAlise;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
