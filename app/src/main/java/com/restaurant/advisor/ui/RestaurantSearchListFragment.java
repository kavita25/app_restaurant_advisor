package com.restaurant.advisor.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.restaurant.advisor.R;
import com.restaurant.advisor.interfaces.RestaurantListListner;
import com.restaurant.advisor.model.Response;
import com.restaurant.advisor.model.Restaurant;
import com.restaurant.advisor.viewmodel.RestaurantNetworkManager;

import static android.Manifest.permission.CALL_PHONE;

public class RestaurantSearchListFragment extends Fragment implements RestaurantListListner {
    private String mQuery;
    private String mOrder;
    private ListAdapter mListAdapter;
    private RecyclerView mRView;
    private TextView mEmptyView;
    private RestaurantNetworkManager networkManager;
    private MutableLiveData<com.restaurant.advisor.model.Response> mLiveData;

    public static RestaurantSearchListFragment newInstance(String query, String keyword) {
        RestaurantSearchListFragment gitSearchListFragment = new RestaurantSearchListFragment();
        Bundle b = new Bundle();
        b.putString("zipcode", query);
        b.putString("keyword", keyword);
        gitSearchListFragment.setArguments(b);
        return gitSearchListFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            mQuery = getArguments().getString("pincode");
            mOrder = getArguments().getString("keyword");
        }
        networkManager = new RestaurantNetworkManager(getActivity(), this);
        networkManager.sendQuery(mQuery, mOrder);
        setRetainInstance(true);
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //request data from internet

        View view = inflater.inflate(R.layout.activity_search_result_screen_fragment, container, false);
        mRView = view.findViewById(R.id.rview);

        mListAdapter = new ListAdapter(getContext(), itemClickListner);
        mRView.setAdapter(mListAdapter);
        mEmptyView = view.findViewById(R.id.text_empty);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRView.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mRView.getContext(),
                mLayoutManager.getOrientation());
        mRView.addItemDecoration(mDividerItemDecoration);
        return view;
    }

    ListAdapter.RItemClickListner itemClickListner = new ListAdapter.RItemClickListner() {
        @Override
        public void onItemClick(Restaurant result) {
            if (result != null) {
                Intent detailActivity = new Intent(RestaurantSearchListFragment.this.getContext(), RestaurantDetailActivity.class);
                String searchResult = new Gson().toJson(result);
                detailActivity.putExtra("search_result", searchResult);
                startActivity(detailActivity);
            }
        }

        @Override
        public void onCallPressed(String phoneNumber) {
            String uri = "tel:" + phoneNumber;
            callIntent.setData(Uri.parse(uri));

            if (ContextCompat.checkSelfPermission(getContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            } else {
                //Toast.makeText(mContext,"No Sim to call on "+getItemAt(position).getPhone(),Toast.LENGTH_LONG).show();
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
    };
    private Intent callIntent = new Intent(Intent.ACTION_CALL);
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResponseReceived(Response response) {
        if (response != null && response.getRestaurants().size() > 0) {
            mListAdapter.setData(response.getRestaurants());
            mListAdapter.notifyDataSetChanged();
            //mViewModel.insert(searchResultsDataModel.getRestaurants());

            mRView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        } else {
            mRView.setVisibility(View.INVISIBLE);
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRestauranDataReceived(Restaurant restaurant) {
    //Nothing to do
    }

    @Override
    public void onError(Exception e) {
        mRView.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==1) if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        } else {
            Toast.makeText(getContext(), "Call Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
}
