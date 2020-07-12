package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.zhonghe.R;
import com.example.Adapter.VpAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private ArrayList<View> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);


        list = new ArrayList<>();
        View vp1 = LayoutInflater.from(this).inflate(R.layout.vp1, null);
        View vp2 = LayoutInflater.from(this).inflate(R.layout.vp2, null);
        View vp3 = LayoutInflater.from(this).inflate(R.layout.vp3, null);

        list.add(vp1);
        list.add(vp2);
        list.add(vp3);

        Button btn = vp3.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        VpAdapter vpAdapter = new VpAdapter(list);

        mVp.setAdapter(vpAdapter);
    }
}
