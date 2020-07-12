package com.example.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhonghe.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaiDuFragment extends Fragment {

    private View view;
    private WebView mWeb;

    public BaiDuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_bai_du, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mWeb = (WebView) inflate.findViewById(R.id.web);

        mWeb.loadUrl("http://www.baidu.com/");
        mWeb.setWebViewClient(new WebViewClient());
    }
}
