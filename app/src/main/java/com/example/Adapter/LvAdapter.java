package com.example.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Bean.SqlBean;
import com.example.zhonghe.R;

import java.util.ArrayList;

/**
 * Created by 文龙 on 2020/1/8.
 */

public class LvAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SqlBean> list;

    public LvAdapter(Context context, ArrayList<SqlBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv, null);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        viewHolder.mLvTitle.setText(list.get(position).getTitle());
        viewHolder.mLvData.setText(list.get(position).getData());
        Glide.with(context).load(list.get(position).getImage()).into(viewHolder.mIvLv);
        return convertView;
    }

    static class ViewHolder {
        View view;
        ImageView mIvLv;
        TextView mLvTitle;
        TextView mLvData;

        ViewHolder(View view) {
            this.view = view;
            this.mIvLv = (ImageView) view.findViewById(R.id.iv_lv);
            this.mLvTitle = (TextView) view.findViewById(R.id.lv_title);
            this.mLvData = (TextView) view.findViewById(R.id.lv_data);
        }
    }
}
