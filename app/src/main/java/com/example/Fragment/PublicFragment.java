package com.example.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Adapter.PublicAdapter;
import com.example.Bean.PublicBean;
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
@SuppressLint("ValidFragment")
public class PublicFragment extends Fragment implements OnRefreshLoadMoreListener {
    private String sUrl = "https://www.wanandroid.com/project/list/1/json?cid=";
    private int id;
    private View view;
    private RecyclerView mRv1;
    private ArrayList<PublicBean.DataBean.DatasBean> list;
    private PublicAdapter publicAdapter;
    private SmartRefreshLayout mSmr;


    public PublicFragment(int id) {
        // Required empty public constructor
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_public, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mRv1 = (RecyclerView) inflate.findViewById(R.id.rv1);
        mSmr = (SmartRefreshLayout) inflate.findViewById(R.id.smr);

        list = new ArrayList<>();
        mRv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        publicAdapter = new PublicAdapter(getActivity(), list);

        mRv1.setAdapter(publicAdapter);
        initData();

        mSmr.setOnRefreshLoadMoreListener(this);

    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(sUrl + id);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK) {
                        InputStream is = connection.getInputStream();

                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String len = null;
                        StringBuffer sbf = new StringBuffer();
                        while ((len = br.readLine()) != null) {
                            sbf.append(len);
                        }

                        is.close();
                        br.close();
                        String json = sbf.toString();
                        Gson gson = new Gson();
                        PublicBean publicBean = gson.fromJson(json, PublicBean.class);
                        final PublicBean.DataBean data = publicBean.getData();
                        final List<PublicBean.DataBean.DatasBean> datas = data.getDatas();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(datas);
                                publicAdapter.notifyDataSetChanged();
                                mSmr.finishLoadMore();
                                mSmr.finishRefresh();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        id++;
        initData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        id=1;
        list.clear();
        initData();
    }
}
