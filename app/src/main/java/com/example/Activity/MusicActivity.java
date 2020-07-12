package com.example.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.zhonghe.R;

import java.io.IOException;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 播放
     */
    private Button mBtnStart;
    /**
     * 暂停
     */
    private Button mBtnZanting;
    /**
     * 继续
     */
    private Button mBtnJixu;
    /**
     * 停止
     */
    private Button mBtnStop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
    }

    private void initView() {
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
        mBtnZanting = (Button) findViewById(R.id.btn_zanting);
        mBtnZanting.setOnClickListener(this);
        mBtnJixu = (Button) findViewById(R.id.btn_jixu);
        mBtnJixu.setOnClickListener(this);
        mBtnStop = (Button) findViewById(R.id.btn_stop);
        mBtnStop.setOnClickListener(this);
        mp = MediaPlayer.create(this, R.raw.yy);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_start:

                mp.start();
                break;
            case R.id.btn_zanting:
                mp.pause();
                break;
            case R.id.btn_jixu:
                if (!mp.isPlaying()){
                    mp.start();
                }
                break;
            case R.id.btn_stop:
                mp.stop();
                break;
        }
    }

    @Override
    protected void onStop() {
        if (mp.isPlaying()){
            mp.stop();
        }
        super.onStop();
    }
}
