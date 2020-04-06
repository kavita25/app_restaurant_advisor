package com.restaurant.advisor.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.restaurant.advisor.R;
import com.restaurant.advisor.model.Restaurant;

import java.text.DecimalFormat;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    interface RItemClickListner {
        void onItemClick(Restaurant result);
        void onCallPressed(String phoneNumber);
    }

    List<Restaurant> mData;
    Context mContext;
    ListAdapter.RItemClickListner mItemClickCallback;

    public ListAdapter(Context context, ListAdapter.RItemClickListner listener) {
        mItemClickCallback = listener;
        mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_row, parent, false);
        return new MyViewHolder(view, viewType);
    }

    private Restaurant getItemAt(int position) {
        return mData.get(position);
    }

    @Override
    public void onBindViewHolder(final @NonNull MyViewHolder holder, final int position) {
        holder.bindData(getItemAt(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickCallback.onItemClick(getItemAt(holder.getAdapterPosition()));
                //  mData.get(getAdapterPosition());
            }
        });

        holder.mCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickCallback.onCallPressed( getItemAt(position).getPhone());

            }
        });

        holder.mDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant rest = getItemAt(position);
                String latitude = rest.getCoordinates().getLatitude();
                String longitude = rest.getCoordinates().getLngitude();

                Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude + "?z=50&q="+rest.getName()+"");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                mContext.startActivity(mapIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
       /* if ("java".equalsIgnoreCase(mData.get(position).getmIs_closed()))
            return 1;*/
        return 0;
    }

    public void setData(List<Restaurant> data) {
        mData = data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mReviewCount;
        TextView mOpenClse;
        TextView mDescription;
        TextView mAlise;
        TextView mDistance;
        TextView mCall;
        TextView mDirection;
        ImageView mPhoto;

        public MyViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.name);
            mReviewCount = itemView.findViewById(R.id.review_count);
            mOpenClse = itemView.findViewById(R.id.open_close);
            mDescription = itemView.findViewById(R.id.description);
            mAlise = itemView.findViewById(R.id.alise);
            mDistance = itemView.findViewById(R.id.distance);
            mCall = itemView.findViewById(R.id.call);
            mDirection = itemView.findViewById(R.id.direction);
            mPhoto = itemView.findViewById(R.id.image);

            if (viewType != 0) {
                itemView.setBackgroundColor(Color.GREEN);
            }
        }

        void bindData(Restaurant searchResult) {
            final Restaurant rest = searchResult;
            mTitle.setText(searchResult.getName());
            String stars = "*****";
            mReviewCount.setText(searchResult.getRating() + stars.substring(0,(int)Math.ceil(Double.parseDouble(searchResult.getRating()))));
            if (!Boolean.valueOf(searchResult.getIs_closed())) {
                mOpenClse.setText("Open");
                mOpenClse.setTextColor(mContext.getColor(R.color.black));
            } else {
                mOpenClse.setText("Close now");
                mOpenClse.setTextColor(mContext.getColor(R.color.red));

            }

            mAlise.setText(searchResult.getCategories().get(0).getTitle());
            DecimalFormat df2 = new DecimalFormat("#.#");
            double miles = Double.valueOf(searchResult.getDistance()) * 0.00062137119;
            mDistance.setText(df2.format(miles) + " miles");
            StringBuilder builder = new StringBuilder();
            int i = 0;
            for (; i < searchResult.getLocation().getDisplayAddress().size() - 1; i++) {
                builder.append(searchResult.getLocation().getDisplayAddress().get(i) + ", ");
            }
            builder.append(searchResult.getLocation().getDisplayAddress().get(i) + ".");
            mDescription.setText(builder.toString());
            Glide.with(mContext).load(searchResult.getImage_url()).into(mPhoto);
        }
    }
}