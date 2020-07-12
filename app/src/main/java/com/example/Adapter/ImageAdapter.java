package com.example.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Bean.ImageBean;
import com.example.zhonghe.R;

import java.util.ArrayList;

/**
 * Created by 文龙 on 2020/1/12.
 */

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ImageBean> list;

    public ImageAdapter(Context context, ArrayList<ImageBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rlv_1, null);

        ViewHolder viewHolde  = new ViewHolder(inflate);

        return viewHolde;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.mTvImageName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.mImageRlv1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageRlv1;
        TextView mTvImageName;

        ViewHolder(View view) {
            super(view);
            this.mImageRlv1 = (ImageView) view.findViewById(R.id.image_rlv1);
            this.mTvImageName = (TextView) view.findViewById(R.id.tv_image_name);
        }
    }
}
