package com.restaurant.advisor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * "address1": "1628 Hostetter Rd",
 * "address2": "Ste F",
 * "address3": "",
 * "city": "San Jose",
 * "zip_code": "95131",
 *  "country": "US",
 * "state": "CA",
 */
@Entity(tableName = "location",primaryKeys = "zip_code")
public class Location {


    @ColumnInfo(name = "address1")
    @SerializedName("address1")
    private String mAddress1;

    @ColumnInfo(name = "address2")
    @SerializedName("address2")
    private String mAddress2;

    @ColumnInfo(name = "address3")
    @SerializedName("address3")
    private String mAddress3;

    @ColumnInfo(name = "city")
    @SerializedName("city")
    private String mCity;

    @NonNull
    @ColumnInfo(name = "zip_code")
    @SerializedName("zip_code")
    private String mZipCode;

    @ColumnInfo(name = "country")
    @SerializedName("country")
    private String mCountry;

    @ColumnInfo(name = "state")
    @SerializedName("state")
    private String mState;

    @ColumnInfo(name = "display_address")
    @SerializedName("display_address")
    private ArrayList<String> mDisplayAddress;

    public String getAddress1() {
        return mAddress1;
    }

    public void setAddress1(String mAddress1) {
        this.mAddress1 = mAddress1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public void setAddress2(String mAddress2) {
        this.mAddress2 = mAddress2;
    }

    public String getAddress3() {
        return mAddress3;
    }

    public void setAddress3(String mAddress3) {
        this.mAddress3 = mAddress3;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String mCity) {
        this.mCity = mCity;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String mZipCode) {
        this.mZipCode = mZipCode;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getState() {
        return mState;
    }

    public void setState(String mState) {
        this.mState = mState;
    }

    public ArrayList<String> getDisplayAddress() {
        return mDisplayAddress;
    }

    public void setDisplayAddress(ArrayList<String> mDisplayAddress) {
        this.mDisplayAddress = mDisplayAddress;
    }
}
