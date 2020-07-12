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
import com.example.Bean.PublicBean;
import com.example.zhonghe.R;

import java.util.ArrayList;

/**
 * Created by 文龙 on 2020/1/9.
 */

public class PublicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<PublicBean.DataBean.DatasBean> list;

    public PublicAdapter(Context context, ArrayList<PublicBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rv1, null);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTvRv1.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getEnvelopePic()).into(viewHolder.mIvRv1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View view;
        ImageView mIvRv1;
        TextView mTvRv1;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            this.mIvRv1 = (ImageView) view.findViewById(R.id.iv_rv1);
            this.mTvRv1 = (TextView) view.findViewById(R.id.tv_rv1);
        }
    }
}
