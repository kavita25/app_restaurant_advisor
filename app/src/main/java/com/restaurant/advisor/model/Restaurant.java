package com.restaurant.advisor.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * id
 * alias
 * name
 * image_url
 * is_closed
 * url
 * review_count
 * Categories[]
 * rating
 * coordinates_latitude
 * coordinates_Longitude
 * Location
 * "phone": "+14084772773",
 * "display_phone": "(408) 477-2773",
 * "distance": 7867.236798310457
 */
@Entity(tableName = "restaurant")
public class Restaurant {

    @NonNull
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private String mId;

    @SerializedName("alias")
    @ColumnInfo(name = "alias")
    private String mAlias;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String mName;

    @SerializedName("image_url")
    @ColumnInfo(name = "image_url")
    private String mImage_url;

    @SerializedName("is_closed")
    @ColumnInfo(name = "updated_at")
    private String mIs_closed;

    @SerializedName("url")
    @ColumnInfo(name = "url")
    private String mUrl;

    @SerializedName("review_count")
    @ColumnInfo(name = "review_count")
    private String mReview_count;

    @SerializedName("categories")
    @ColumnInfo(name = "categories")
    @TypeConverters(CatagoriesConverter.class)
    private List<Categories> mCategories;

    @SerializedName("rating")
    @ColumnInfo(name = "rating")
    private String mRating;

    @SerializedName("coordinates")
    @ColumnInfo(name = "coordinates")
    @TypeConverters(CoordinateConverter.class)
    private Coordinates mCoordinates;

    @SerializedName("location")
    @ColumnInfo(name = "location")
    @TypeConverters(LocationConverter.class)
    private Location mLocation;

    @SerializedName("phone")
    @ColumnInfo(name = "phone")
    private String mPhone;

    @SerializedName("display_phone")
    @ColumnInfo(name = "display_phone")
    private String mDisplay_phone;

    @SerializedName("distance")
    @ColumnInfo(name = "distance")
    private String mDistance;

    public ArrayList<String> getPhotos() {
        return mPhotos;
    }

    public void setPhotos(ArrayList<String> mPhotos) {
        this.mPhotos = mPhotos;
    }

    public ArrayList<Schedule> getHours() {
        return mHours;
    }

    public void setHours(ArrayList<Schedule> mHours) {
        this.mHours = mHours;
    }

    @SerializedName("photos")
    @ColumnInfo(name = "photos")
    private ArrayList<String> mPhotos;

    @SerializedName("hours")
    @ColumnInfo(name = "hours")
    private ArrayList<Schedule> mHours;

    @SerializedName("transactions")
    @ColumnInfo(name = "transactions")
    private ArrayList<String> mTransactions;



    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getAlias() {
        return mAlias;
    }

    public void setAlias(String mAlias) {
        this.mAlias = mAlias;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getImage_url() {
        return mImage_url;
    }

    public void setImage_url(String mImage_url) {
        this.mImage_url = mImage_url;
    }

    public String getIs_closed() {
        return mIs_closed;
    }

    public void setIs_closed(String mIs_closed) {
        this.mIs_closed = mIs_closed;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getReview_count() {
        return mReview_count;
    }

    public void setReview_count(String mReview_count) {
        this.mReview_count = mReview_count;
    }

    public List<Categories>  getCategories() {
        return mCategories;
    }

    public void setCategories(List<Categories> mCategories) {
        this.mCategories = mCategories;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String mRating) {
        this.mRating = mRating;
    }

    public Coordinates getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(Coordinates mCoordinates) {
        this.mCoordinates = mCoordinates;
    }

    public Location getLocation() {
        return mLocation;
    }

    public void setLocation(Location mLocation) {
        this.mLocation = mLocation;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getDisplay_phone() {
        return mDisplay_phone;
    }

    public void setDisplay_phone(String mDisplay_phone) {
        this.mDisplay_phone = mDisplay_phone;
    }

    public String getDistance() {
        return mDistance;
    }

    public void setDistance(String mDistance) {
        this.mDistance = mDistance;
    }
    public ArrayList<String> getTransactions() {
        return mTransactions;
    }

    public void setTransactions(ArrayList<String> mTransactions) {
        this.mTransactions = mTransactions;
    }

}
