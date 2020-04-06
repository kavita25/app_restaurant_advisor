package com.restaurant.advisor.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.restaurant.advisor.R;
import com.restaurant.advisor.model.Schedule;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShowPhotos extends AppCompatActivity {
    ArrayList<String> mPhotos ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photos);
        Bundle b = getIntent().getExtras();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();
        String json = b.getString("photos");
        mPhotos = new Gson().fromJson(json,type);
        showPhotos(mPhotos);
    }

    private void showPhotos(ArrayList<String> photos) {
        ViewPager viewPager = findViewById(R.id.view_pager);
        MyAdapter myAdapter = new MyAdapter();
        viewPager.setAdapter(myAdapter);

    }
    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPhotos.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object view) {
            container.removeView((View) view);
            // super.destroyItem(container, position, view);
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(ShowPhotos.this).inflate(R.layout.inc_pager_item,null);
            ImageView imageView = view.findViewById(R.id.imageview);
            Glide.with(ShowPhotos.this).load(mPhotos.get(position)).into(imageView);
            container.addView(view);
            return view;
        }
    }
}
