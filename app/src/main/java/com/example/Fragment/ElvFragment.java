package com.example.Fragment;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Bean.ElvBean;
import com.example.Adapter.ElvAdapter;
import com.example.zhonghe.R;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ElvFragment extends Fragment implements OnRefreshLoadMoreListener {


    private View view;
    private ExpandableListView mElv;
    private SmartRefreshLayout mSrlElv;
    private String sUrl = " https://www.wanandroid.com/tree/json";
    private ArrayList<ElvBean.DataBean> list;
    private ElvAdapter elvAdapter;
    private Button btn_remove;
    private Button btn_quxiao;
    private TextView et_remove;
    private PopupWindow popupWindow;

    public ElvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_elv, container, false);
        initView(inflate);
        initListener();
        return inflate;
    }

    private void initListener() {
        //父条目点击监听
        mElv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String name = list.get(groupPosition).getName();
                Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mElv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, final int groupPosition, final int childPosition, long id) {
                //找popu布局
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.pop_elv_remove, null);
                //找控件
                btn_remove = inflate.findViewById(R.id.btn_rlv2_remove);
                btn_quxiao = inflate.findViewById(R.id.btn_quxiao_elv);
                et_remove = inflate.findViewById(R.id.tv_rlv2_remove);
                //设置popu
                popupWindow = new PopupWindow(inflate, 1100, 300);
                popupWindow.setBackgroundDrawable(new ColorDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAtLocation(mElv,Gravity.CENTER,-100,-100);

                //删除监听
                btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.get(groupPosition).getChildren().remove(childPosition);
                        elvAdapter.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
                //取消监听
                btn_quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                return false;
            }
        });


    }

    private void initView(View inflate) {
        mElv = (ExpandableListView) inflate.findViewById(R.id.elv);
        mSrlElv = (SmartRefreshLayout) inflate.findViewById(R.id.srl_elv);

        list = new ArrayList<>();

        elvAdapter = new ElvAdapter(getActivity(), list);

        mElv.setAdapter(elvAdapter);

        mSrlElv.setOnRefreshLoadMoreListener(this);

        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(sUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK){
                        InputStream is = connection.getInputStream();

                        BufferedReader br = new BufferedReader(new InputStreamReader(is));

                        String len = null;
                        StringBuffer sbf = new StringBuffer();
                        while ((len = br.readLine()) != null){
                            sbf.append(len);
                        }
                        is.close();
                        br.close();
                        String json = sbf.toString();
                        Gson gson = new Gson();
                        ElvBean elvBean = gson.fromJson(json, ElvBean.class);
                        final List<ElvBean.DataBean> data = elvBean.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(data);
                                elvAdapter.notifyDataSetChanged();
                                mSrlElv.finishLoadMore();
                                mSrlElv.finishRefresh();
                            }
                        });

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }

    public void bgAlpha(float bgAlpha){
        WindowManager.LayoutParams ab = getActivity().getWindow().getAttributes();
        ab.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(ab);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        initData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        list.clear();
        initData();
    }
}
