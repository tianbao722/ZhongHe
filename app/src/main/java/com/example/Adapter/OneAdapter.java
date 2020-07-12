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
import com.bumptech.glide.request.RequestOptions;
import com.example.Bean.OneBean;
import com.example.zhonghe.R;

import java.util.ArrayList;

/**
 * Created by 文龙 on 2020/1/8.
 */

public class OneAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<OneBean.DataBean> list;
    private int ITEM_TYPE_ONE = 1;
    private int ITEM_TYPE_TWO = 2;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public OneAdapter(Context context, ArrayList<OneBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_ONE) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_rlv1, null);
            ViewHolderOne viewHolderOne = new ViewHolderOne(inflate);
            return viewHolderOne;

        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_rlv2, null);
            ViewHolderTwo viewHolderTwo = new ViewHolderTwo(inflate);

            return viewHolderTwo;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType == ITEM_TYPE_ONE) {
            ViewHolderOne viewHolderOne = (ViewHolderOne) holder;
            viewHolderOne.mTvTitle.setText(list.get(position).getTitle());
            RequestOptions requestOptions = new RequestOptions();
            RequestOptions requestOptions1 = requestOptions.circleCrop();
            viewHolderOne.data.setText(list.get(position).getFood_str());
            Glide.with(context).load(list.get(position).getPic()).apply(requestOptions1).into(viewHolderOne.mIvRlv1);

        } else {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            viewHolderTwo.mTvRlv2.setText(list.get(position).getTitle());
            RequestOptions requestOptions = new RequestOptions();
            RequestOptions requestOptions1 = requestOptions.circleCrop();
            viewHolderTwo.data.setText(list.get(position).getFood_str());
            Glide.with(context).load(list.get(position).getPic()).apply(requestOptions1).into(viewHolderTwo.mIvRlv2);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                onItemLongClickListener.OnItemLongClick(position);
                return false;
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return ITEM_TYPE_ONE;
        } else {
            return ITEM_TYPE_TWO;
        }

    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {
        ImageView mIvRlv1;
        TextView mTvTitle;
        TextView data;

        ViewHolderOne(View view) {
            super(view);
            this.mIvRlv1 = (ImageView) view.findViewById(R.id.iv_rlv1);
            this.mTvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.data = view.findViewById(R.id.tv_data);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        TextView mTvRlv2;
        ImageView mIvRlv2;
        TextView data;

        ViewHolderTwo(View view) {
            super(view);
            this.mTvRlv2 = (TextView) view.findViewById(R.id.tv_rlv2_title);
            this.mIvRlv2 = (ImageView) view.findViewById(R.id.iv_iv);
            this.data = view.findViewById(R.id.tv_rlv2_data);
        }
    }

    interface OnItemClickListener{
        void OnItemClick(int i);
    }
    interface OnItemLongClickListener{
        void OnItemLongClick(int i);
    }
}
