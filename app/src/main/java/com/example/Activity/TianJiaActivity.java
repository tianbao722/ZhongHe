package com.example.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhonghe.R;

public class TianJiaActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 请输入菜名
     */
    private EditText mEdTitle;
    /**
     * 请输入材料
     */
    private EditText mEdData;
    /**
     * 添加
     */
    private Button mBtnAdd;
    /**
     * 取消
     */
    private Button mBtnQuxiao;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tian_jia);
        initView();
    }

    private void initView() {
        mEdTitle = (EditText) findViewById(R.id.ed_title);
        mEdData = (EditText) findViewById(R.id.ed_data);
        mBtnAdd = (Button) findViewById(R.id.btn_add);
        mBtnAdd.setOnClickListener(this);
        mBtnQuxiao = (Button) findViewById(R.id.btn_quxiao);
        mBtnQuxiao.setOnClickListener(this);


        intent = getIntent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_add:
                String s = mEdTitle.getText().toString();
                String s1 = mEdData.getText().toString();

                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(s1)){
                    intent.putExtra("tit",s);
                    intent.putExtra("da",s1);
                    setResult(4,intent);
                    finish();
                }else {
                    Toast.makeText(this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_quxiao:
                finish();
                break;
        }
    }
}
