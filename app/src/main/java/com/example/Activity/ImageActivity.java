package com.example.Activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.Bean.ImageBean;
import com.example.Adapter.ImageAdapter;
import com.example.zhonghe.R;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {

    private RecyclerView mRlv1;
    private ArrayList<ImageBean> list;
    private ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
    }

    private void initView() {
        mRlv1 = (RecyclerView) findViewById(R.id.rlv1);


        mRlv1.setLayoutManager(new GridLayoutManager(this,2));
        list = new ArrayList<>();

        ContentResolver contentResolver = getContentResolver();
        Cursor query = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (query.moveToNext()){
            String name = query.getString(query.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)).substring(0,5);//图片地址
            String data = query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));

            ImageBean imageBean = new ImageBean(data, name);

            list.add(imageBean);
        }

        imageAdapter = new ImageAdapter(this, list);

        mRlv1.setAdapter(imageAdapter);

    }
}
