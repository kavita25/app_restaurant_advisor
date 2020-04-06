package com.restaurant.advisor.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.restaurant.advisor.R;
public class SearchResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_screen);
        String zipcode = getIntent().getStringExtra("zipcode");
        String keyword = getIntent().getStringExtra("keyword");
        if (getSupportFragmentManager().findFragmentById(R.id.fview) == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fview, RestaurantSearchListFragment.newInstance(zipcode, keyword)).commit();
        }
    }
}
