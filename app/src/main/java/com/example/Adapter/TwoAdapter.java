package com.example.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.Bean.TwoRvBean;
import com.example.zhonghe.R;

import java.util.ArrayList;

/**
 * Created by 文龙 on 2020/1/9.
 */

public class TwoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<TwoRvBean.ResultsBean> list;

    public TwoAdapter(Context context, ArrayList<TwoRvBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rv, null);
        ViewHolder viewHolder = new ViewHolder(inflate);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(context).load(list.get(position).getUrl()).into(viewHolder.mIvRv);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView mIvRv;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            this.mIvRv = (ImageView) view.findViewById(R.id.iv_rv);
        }
    }
}
