package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.Activity.GreenDaoActivity;
import com.example.zhonghe.R;

public class MyActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 龙
     */
    private TextView mTvLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
    }

    private void initView() {
        mTvLong = (TextView) findViewById(R.id.tv_long);
        mTvLong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_long:
                Intent intent = new Intent(this, GreenDaoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
