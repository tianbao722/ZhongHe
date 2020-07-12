package com.example.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.Adapter.TwoAdapter;
import com.example.Bean.BannerBean;
import com.example.Bean.TwoRvBean;
import com.example.zhonghe.R;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

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
public class TwoFragment extends Fragment implements OnRefreshLoadMoreListener {


    private View view;
    private Banner mBanner;
    private RecyclerView mRv;
    private SmartRefreshLayout mSr;
    private String BannerUrl = " https://www.wanandroid.com/banner/json";
    private String sUrl = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/";
    private int page = 2;
    private ArrayList<TwoRvBean.ResultsBean> list;
    private TwoAdapter twoAdapter;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_two, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mBanner = (Banner) inflate.findViewById(R.id.banner);
        mSr = (SmartRefreshLayout) inflate.findViewById(R.id.sr);
        mRv = (RecyclerView) inflate.findViewById(R.id.rv);


        list = new ArrayList<>();

        mRv.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        twoAdapter = new TwoAdapter(getActivity(), list);

        mRv.setAdapter(twoAdapter);

        mSr.setOnRefreshLoadMoreListener(this);
        intBanner();
        iniList();
    }

    private void iniList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(BannerUrl);
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
                        BannerBean bannerBean = gson.fromJson(json, BannerBean.class);
                        final List<BannerBean.DataBean> data = bannerBean.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<String> image = new ArrayList<>();
                                ArrayList<String> title = new ArrayList<>();
                                for (int i = 0; i < data.size(); i++) {
                                    String imagePath = data.get(i).getImagePath();
                                    String title1 = data.get(i).getTitle();

                                    image.add(imagePath);
                                    title.add(title1);
                                }
                                mBanner
                                        .setBannerTitles(title)
                                        .setImages(image)
                                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                                        .setImageLoader(new ImageLoader() {
                                            @Override
                                            public void displayImage(Context context, Object path, ImageView imageView) {
                                                Glide.with(getActivity()).load(path).into(imageView);
                                            }
                                        })
                                        .start();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void intBanner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(sUrl + page);
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
                        TwoRvBean twoRvBean = gson.fromJson(json, TwoRvBean.class);
                        final List<TwoRvBean.ResultsBean> results = twoRvBean.getResults();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(results);
                                twoAdapter.notifyDataSetChanged();
                                mSr.finishLoadMore();
                                mSr.finishRefresh();
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
        page++;
        intBanner();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 2;
        list.clear();
        intBanner();
    }
}
