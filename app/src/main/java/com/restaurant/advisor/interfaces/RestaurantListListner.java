package com.restaurant.advisor.interfaces;

import com.restaurant.advisor.model.Restaurant;

public interface RestaurantListListner {

    void onResponseReceived(com.restaurant.advisor.model.Response response );
    void onRestauranDataReceived(Restaurant restaurant);
    void onError(Exception e);

}

