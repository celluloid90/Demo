package com.shahin.demo.Adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shahin.demo.Data.DashboardDataModel;
import com.shahin.demo.R;

import java.util.ArrayList;

public class DashboardImageRecycleAdapter extends RecyclerView.Adapter<DashboardImageRecycleAdapter.DashboardViewHolder> {
    private ArrayList<DashboardDataModel> mDashboardDataList;
    private Context mContext;

    public DashboardImageRecycleAdapter(Context context, ArrayList<DashboardDataModel> Data) {
        this.mContext = context;
        mDashboardDataList = Data;
    }

    @Override
    public DashboardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_items, parent, false);
        DashboardViewHolder holder = new DashboardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final DashboardViewHolder holder, int position) {
        holder.titleTextView.setText(mDashboardDataList.get(position).getImageTitle());
        holder.coverImageView.setImageBitmap(mDashboardDataList.get(position).getImage());
        holder.coverImageView.setTag(mDashboardDataList.get(position).getImageDescription());
        holder.likeImageView.setTag(R.drawable.ic_like);
    }

    @Override
    public int getItemCount() {
        return mDashboardDataList.size();
    }

    class DashboardViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public ImageView likeImageView;
        public ImageView shareImageView;

        public DashboardViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            likeImageView = (ImageView) v.findViewById(R.id.likeImageView);
            shareImageView = (ImageView) v.findViewById(R.id.shareImageView);
            likeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = (int) likeImageView.getTag();
                    if (id == R.drawable.ic_like) {
                        likeImageView.setTag(R.drawable.ic_liked);
                        likeImageView.setImageResource(R.drawable.ic_liked);

                        Toast.makeText(mContext, titleTextView.getText() + " added to favourites", Toast.LENGTH_SHORT).show();

                    } else {

                        likeImageView.setTag(R.drawable.ic_like);
                        likeImageView.setImageResource(R.drawable.ic_like);
                        Toast.makeText(mContext, titleTextView.getText() + " removed from favourites", Toast.LENGTH_SHORT).show();
                    }

                }
            });


            shareImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
//                            "://" + mContext.getResources().getResourcePackageName(coverImageView.getId())
//                            + '/' + "drawable" + '/' + mContext.getResources().getResourceEntryName((int) coverImageView.getTag()));
//                    Intent shareIntent = new Intent();
//                    shareIntent.setAction(Intent.ACTION_SEND);
//                    shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//                    shareIntent.setType("image/jpeg");
//                    mContext.startActivity(Intent.createChooser(shareIntent, mContext.getResources().getText(R.string.send_to)));
                }
            });
        }
    }
}