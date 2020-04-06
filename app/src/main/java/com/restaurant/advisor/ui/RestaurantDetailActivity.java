package com.restaurant.advisor.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.restaurant.advisor.R;
import com.restaurant.advisor.components.menu.MenuActivity;
import com.restaurant.advisor.interfaces.RestaurantListListner;
import com.restaurant.advisor.model.DailyTime;
import com.restaurant.advisor.model.Response;
import com.restaurant.advisor.model.Restaurant;
import com.restaurant.advisor.viewmodel.RestaurantNetworkManager;

import java.text.DecimalFormat;


public class RestaurantDetailActivity extends AppCompatActivity implements RestaurantListListner {
    private Restaurant mResultData = null;
    private RestaurantNetworkManager networkManager;
    private CoordinatorLayout mDetailView;
    private RecyclerView mRecyclerView;
    private OpenCloseScheduleAdapter mOpenCloseScheduleAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDetailView = findViewById(R.id.detail_view);
        mRecyclerView = findViewById(R.id.openCloseSchedule);
        mLinearLayoutManager = new LinearLayoutManager(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String resultJson = getIntent().getStringExtra("search_result");
        mResultData = new Gson().fromJson(resultJson, Restaurant.class);
        networkManager = new RestaurantNetworkManager(this, this);
        networkManager.getAdditionalData(mResultData.getId());
        setData(mResultData);
    }


    private void setData(final Restaurant restaurant) {
        ImageView toolbarImage = findViewById(R.id.toolbarImage);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        //TextView createdDate = findViewById(R.id.created_date);
        TextView updatedDate = findViewById(R.id.updated_date);
        TextView pushedDate = findViewById(R.id.pushed_date);
        TextView alise = findViewById(R.id.name);
        TextView stars = findViewById(R.id.stars);
        TextView description = findViewById(R.id.description);
        TextView type = findViewById(R.id.openclose);

        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (; i < restaurant.getLocation().getDisplayAddress().size() - 1; i++) {
            builder.append(restaurant.getLocation().getDisplayAddress().get(i) + ", ");
        }
        builder.append(restaurant.getLocation().getDisplayAddress().get(i) + ".");
        description.setText(builder.toString());
        String starsStr = "*****";
        stars.setText(restaurant.getRating() + starsStr.substring(0, (int) Math.ceil(Double.parseDouble(restaurant.getRating()))));
        alise.setText(restaurant.getCategories().get(0).getTitle() + " Cuisine");
        updatedDate.setText(restaurant.getDisplay_phone());
        DecimalFormat df2 = new DecimalFormat("#.#");
        if (restaurant.getDistance() != null) {
            double miles = Double.valueOf(restaurant.getDistance()) * 0.00062137119;
            pushedDate.setText(df2.format(miles) + " miles");
        }

        if (!Boolean.valueOf(restaurant.getIs_closed())) {
            type.setText("Open");
            type.setTextColor(getResources().getColor(R.color.green));
        } else {
            type.setText("Close now");
            type.setTextColor(Color.RED);

        }
        Glide.with(RestaurantDetailActivity.this).load(restaurant.getImage_url()).into(toolbarImage);
        toolbarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mResultData != null) {
                    Intent photos = new Intent(RestaurantDetailActivity.this, ShowPhotos.class);
                    photos.putExtra("photos", new Gson().toJson(mResultData.getPhotos()));
                    startActivity(photos);
                }

            }
        });
        collapsingToolbarLayout.setTitle(restaurant.getName());
        if (restaurant.getHours() != null && restaurant.getHours().get(0).getmDailyTime() != null && restaurant.getHours().get(0).getmDailyTime().size() > 0) {
            mRecyclerView.setLayoutManager(mLinearLayoutManager);
            mOpenCloseScheduleAdapter = new OpenCloseScheduleAdapter();
            mRecyclerView.setAdapter(mOpenCloseScheduleAdapter);
        }

    }


    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.weblinkimg:
            case R.id.weblintext: //Open link on webview
                WebView myWebView = (WebView) findViewById(R.id.webView);
                myWebView.setWebViewClient(new WebViewClient());
                myWebView.loadUrl(mResultData.getUrl());
                myWebView.getSettings().setJavaScriptEnabled(true);
                myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
                myWebView.setVisibility(View.VISIBLE);
                mDetailView.setVisibility(View.GONE);

                break;
            case R.id.callText:
            case R.id.callimg: //Open link on webview
            case R.id.l1:
                String uri = "tel:" + mResultData.getPhone();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                   // Toast.makeText(RestaurantDetailActivity.this, "No Sim to call on " + mResultData.getPhone(), Toast.LENGTH_LONG).show();

                    return;
                }
                startActivity(intent);
                break;

            case R.id.shareimg:
            case R.id.shareText: //Open sharing apps link on webview
                Intent share = new Intent();
                share.setAction(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, "Restaurant : " + mResultData.getName() + " Link :" + mResultData.getUrl());
                startActivity(Intent.createChooser(share, "Share"));
                break;
            case R.id.mapimg:
            case R.id.mapText: //Open link on webview
                String latitude = mResultData.getCoordinates().getLatitude();
                String longitude = mResultData.getCoordinates().getLngitude();

                Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?z=50&q=" + mResultData.getName() + "");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                break;
            case R.id.photosimg:
            case R.id.photoText: //Open link on webview
            case R.id.toolbarImage:
                if (mResultData != null && mResultData.getPhotos() != null && mResultData.getPhotos().size() > 0) {
                    showPhotosDialog();
                } else {
                    Toast.makeText(RestaurantDetailActivity.this, "No Photos available for this Restaurant", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.menu:
            case R.id.l2:
                Intent menu = new Intent(this, MenuActivity.class);
                menu.putExtra("mealType",mResultData.getCategories().get(0).getTitle());
                startActivity(menu);
                break;
            case R.id.openclose:
                TextView type = findViewById(R.id.openclose);
                if (mRecyclerView.getVisibility() == View.VISIBLE) {
                    mRecyclerView.setVisibility(View.GONE);
                    type.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.round_arrow_drop_down_black_24,0);
                } else {
                    mRecyclerView.setVisibility(View.VISIBLE);
                    type.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.round_arrow_drop_up_black_24,0);

                }


        }

    }

    private void showPhotosDialog() {
        Intent photosin = new Intent(RestaurantDetailActivity.this, ShowPhotos.class);
        photosin.putExtra("photos", new Gson().toJson(mResultData.getPhotos()));
        startActivity(photosin);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mResultData = null;
        networkManager = null;
        mDetailView = null;
        mRecyclerView = null;
        mOpenCloseScheduleAdapter = null;
        mLinearLayoutManager = null;
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    public void onResponseReceived(Response response) {

    }

    @Override
    public void onRestauranDataReceived(Restaurant restaurant) {
        if (restaurant != null) {
            setData(restaurant);
            mResultData = restaurant;
        }
    }

    @Override
    public void onError(Exception e) {
        //Show Error
    }

    class OpenCloseScheduleAdapter extends RecyclerView.Adapter<OpenCloseScheduleAdapter.CustomViewHolder> {

        @NonNull
        @Override
        public OpenCloseScheduleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(RestaurantDetailActivity.this).inflate(R.layout.time_table_row, parent, false);
            return new CustomViewHolder(v);

        }

        @Override
        public void onBindViewHolder(@NonNull OpenCloseScheduleAdapter.CustomViewHolder holder, int position) {
            holder.bindData(mResultData.getHours().get(0).getmDailyTime().get(position));
        }

        @Override
        public int getItemCount() {
            return mResultData.getHours().get(0).getmDailyTime().size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView timeTable;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.name);
                timeTable = itemView.findViewById(R.id.time);
            }

            public void bindData(DailyTime schedule) {
                name.setText(getDayName(schedule.getDay()));
                if (schedule.getStart() != null && schedule.getEnd() != null) {
                    if (Integer.parseInt(schedule.getStart().substring(0, 2)) > 12) {
                        int starttime = Integer.parseInt(schedule.getStart().substring(0, 2)) - 12;
                        String startString = (starttime < 10) ? "0" + starttime : starttime + "";
                        schedule.setStart(startString + schedule.getStart().substring(2) + " PM");
                    } else {
                        schedule.setStart(schedule.getStart() + " AM");
                    }
                    if (Integer.parseInt(schedule.getEnd().substring(0, 2)) > 12) {
                        int endtime = Integer.parseInt(schedule.getEnd().substring(0, 2)) - 12;
                        String endString = (endtime < 10) ? "0" + endtime : endtime + "";
                        schedule.setEnd(endString + schedule.getEnd().substring(2) + " PM");
                    } else {
                        schedule.setEnd(schedule.getEnd() + " AM");
                    }

                    timeTable.setText(schedule.getStart().substring(0, 2) + ":" + schedule.getStart().substring(2) + " - " + schedule.getEnd().substring(0, 2) + ":" + schedule.getEnd().substring(2));
                }
            }
        }

        private String getDayName(String day) {
            switch (day) {
                case "1":
                    return "Monday";
                case "2":
                    return "Tuesday";
                case "3":
                    return "Wednesday";
                case "4":
                    return "Thursday";
                case "5":
                    return "Friday";
                case "6":
                    return "Saturday";
                case "0":
                    return "Sunday";
                default:
                    return "";

            }
        }
    }


}
