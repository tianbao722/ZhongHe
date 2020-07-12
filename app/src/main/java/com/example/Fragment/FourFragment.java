package com.example.Fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Bean.FourBean;
import com.example.Adapter.FourAdapter;
import com.example.zhonghe.R;
import com.google.gson.Gson;

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
public class FourFragment extends Fragment {


    private View view;
    private TabLayout mTab1;
    private ViewPager mVpTab1;
    private String sUrl = "https://www.wanandroid.com/project/tree/json";

    public FourFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_four, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mTab1 = (TabLayout) inflate.findViewById(R.id.tab1);
        mVpTab1 = (ViewPager) inflate.findViewById(R.id.vp_tab1);


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
                        FourBean fourBean = gson.fromJson(json, FourBean.class);
                        final List<FourBean.DataBean> data = fourBean.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ArrayList<String> title = new ArrayList<>();
                                ArrayList<Fragment> list = new ArrayList<>();
                                for (int i = 0; i < data.size(); i++) {
                                    title.add(data.get(i).getName());
                                    list.add(new PublicFragment(data.get(i).getId()));
                                }
                                FourAdapter fourAdapter = new FourAdapter(getChildFragmentManager(), title, list);
                                mVpTab1.setAdapter(fourAdapter);
                                mTab1.setupWithViewPager(mVpTab1);
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
