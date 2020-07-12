package com.example.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Bean.Bean;
import com.example.zhonghe.R;

import java.util.ArrayList;

/**
 * Created by 文龙 on 2020/2/17.
 */

public class Rlv2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Bean> list;

    public Rlv2Adapter(Context context, ArrayList<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item1_rlv2, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTvId.setText(list.get(position).getId()+"");
        viewHolder.mTvData1.setText(list.get(position).getData());
        viewHolder.mTvName.setText(list.get(position).getName());
        viewHolder.mTvStet.setText(list.get(position).getStep()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTvId;
        TextView mTvData1;
        TextView mTvName;
        TextView mTvStet;

        ViewHolder(View view) {
            super(view);
            this.mTvId = (TextView) view.findViewById(R.id.tv_id);
            this.mTvData1 = (TextView) view.findViewById(R.id.tv_data1);
            this.mTvName = (TextView) view.findViewById(R.id.tv_name);
            this.mTvStet = (TextView) view.findViewById(R.id.tv_stet);
        }
    }
}
