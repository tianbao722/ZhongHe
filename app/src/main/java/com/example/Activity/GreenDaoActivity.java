package com.example.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.Bean.Bean;
import com.example.zhongh.db.BeanDao;
import com.example.zhonghe.BaseApp;
import com.example.zhonghe.R;
import com.example.Adapter.Rlv2Adapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener, OnRefreshLoadMoreListener {

    private RecyclerView mRlv2;
    private ArrayList<Bean> list;
    private Rlv2Adapter rlv2Adapter;
    /**
     * 添加
     */
    private Button mBtnInsert;
    private SmartRefreshLayout mSrl2;
    /**
     * 清空
     */
    private Button mBtnDelete;
    /**
     * 替换id为1的数据
     */
    private Button mBtnOrreplace;
    private BeanDao beanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        initView();


    }

    private void initView() {
        mRlv2 = (RecyclerView) findViewById(R.id.rlv2);
        mSrl2 = (SmartRefreshLayout) findViewById(R.id.srl2);
        mSrl2.setOnRefreshLoadMoreListener(this);
        mRlv2.setLayoutManager(new LinearLayoutManager(this));
        mRlv2.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        list = new ArrayList<>();
        rlv2Adapter = new Rlv2Adapter(this, list);
        mRlv2.setAdapter(rlv2Adapter);
        initdata();
        mBtnInsert = (Button) findViewById(R.id.btn_insert);
        mBtnInsert.setOnClickListener(this);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnDelete.setOnClickListener(this);
        mBtnOrreplace = (Button) findViewById(R.id.btn_orreplace);
        mBtnOrreplace.setOnClickListener(this);
    }

    private void initdata() {
        // id 1l 这个是数字1和小写字母l，不是十一
        beanDao = BaseApp.getInstance().getDaoSession().getBeanDao();
        List<Bean> beans = beanDao.loadAll();//查询所有
        Log.i("111", "onCreate: " + beans);
        list.addAll(beans);
        rlv2Adapter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_insert:
                beanDao.insert(new Bean(1l, "2020-02-01", "郑文龙", 18));
                beanDao.insert(new Bean(2l, "2020-02-02", "郑天保", 18));
                beanDao.insert(new Bean(3l, "2020-02-03", "郑世豪", 18));
                beanDao.insert(new Bean(4l, "2020-02-04", "郑  坤", 18));
                beanDao.insert(new Bean(5l, "2020-02-05", "郑新瑶", 18));
                beanDao.insert(new Bean(6l, "2020-02-06", "郑苗苗", 27));
                beanDao.insert(new Bean(7l, "2020-02-07", "正贝贝", 29));
                beanDao.insert(new Bean(8l, "2020-02-05", "郑爱民", 49));
                beanDao.insert(new Bean(9l, "2020-02-05", "李海霞", 50));
                Toast.makeText(this, "添加完成", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_delete:
                beanDao.deleteAll();
                break;
            case R.id.btn_orreplace:
                //插入：没有则插入，有则替换
                beanDao.insertOrReplace(new Bean(1l,"2001-07-22","郑无敌",2001));
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        initdata();
        mSrl2.finishLoadMore();
        mSrl2.finishRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        list.clear();
        initdata();
        mSrl2.finishLoadMore();
        mSrl2.finishRefresh();
    }
}
