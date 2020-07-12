package com.example.zhonghe;


import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Activity.TianJiaActivity;
import com.example.Adapter.OneAdapter;
import com.example.Bean.OneBean;
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
public class HomeFragment extends Fragment implements OnRefreshLoadMoreListener {

    private View view;
    private RecyclerView mRlv;
    private SmartRefreshLayout mSrl;
    private String sUrl = "http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=";
    private ArrayList<OneBean.DataBean> list;
    private String bannerUrl = "https://www.wanandroid.com/banner/json";
    private int page = 1;
    private OneAdapter oneAdapter;
    private int id;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        initView(inflate);
        initListener();
        initBanner();
        return inflate;
    }

    private void initBanner() {
        new Thread(new Runnable() {
            @Override
            public void run() {









            }
        }).start();
    }

    private void initListener() {
        oneAdapter.setOnItemClickListener(new OneAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(final int i) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("是否添加收藏")
                        .setMessage("即将添加到收藏...")
                        .setNegativeButton("收藏", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ThreeSql threeSql = new ThreeSql(getActivity());
                                SQLiteDatabase db = threeSql.getReadableDatabase();
                                String title = list.get(i).getTitle();
                                String food_str = list.get(i).getFood_str();
                                String pic = list.get(i).getPic();

                                ContentValues contentValues = new ContentValues();
                                contentValues.put("image",pic);
                                contentValues.put("title",title);
                                contentValues.put("data",food_str);

                                db.insert("food",null,contentValues);
                            }
                        })
                        .setPositiveButton("取消",null)
                        .create().show();
            }
        });
        oneAdapter.setOnItemLongClickListener(new OneAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(int i) {
                id = i;
            }
        });
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        mSrl = (SmartRefreshLayout) inflate.findViewById(R.id.srl);

        list = new ArrayList<>();

        oneAdapter = new OneAdapter(getActivity(), list);

        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRlv.setAdapter(oneAdapter);

        initData();
        mSrl.setOnRefreshLoadMoreListener(this);
        registerForContextMenu(mRlv);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(sUrl+page);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    int code = connection.getResponseCode();
                    if (code == HttpURLConnection.HTTP_OK){
                        InputStream is = connection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String len = null;
                        StringBuffer sbf = new StringBuffer();
                        while ((len  = br.readLine()) != null){
                            sbf.append(len);
                        }
                        is.close();
                        br.close();
                        String json = sbf.toString();
                        Gson gson = new Gson();
                        OneBean oneBean = gson.fromJson(json, OneBean.class);
                        final List<OneBean.DataBean> data = oneBean.getData();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(data);
                                oneAdapter.notifyDataSetChanged();
                                mSrl.finishRefresh();
                                mSrl.finishLoadMore();
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
        initData();
    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 1;
        list.clear();
        initData();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


        menu.add(0, 0, 0, "修改");
        menu.add(0, 1, 0, "删除");
        menu.add(0, 2, 0, "添加");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.context_xiugai, null);
                final EditText title = inflate.findViewById(R.id.et_title);
                final EditText data = inflate.findViewById(R.id.et_data);
                String title1 = list.get(id).getTitle();
                String food_str = list.get(id).getFood_str();
                title.setText(title1);
                data.setText(food_str);
                new AlertDialog.Builder(getActivity())
                        .setTitle("修改")
                        .setView(inflate)
                        .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s = title.getText().toString();
                                String s1 = data.getText().toString();
                                list.get(id).setTitle(s);
                                list.get(id).setFood_str(s1);
                                oneAdapter.notifyDataSetChanged();
                            }
                        })
                        .setPositiveButton("取消",null)
                        .create().show();
                break;
            case 1:
                new AlertDialog.Builder(getActivity())
                        .setTitle("是否删除此数据")
                        .setMessage("删除将不可恢复，请慎重!!!")
                        .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                list.remove(id);
                                oneAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "删除成功，小可爱", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("取消",null)
                        .create().show();
                break;
            case 2:
                Toast.makeText(getActivity(), item.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), TianJiaActivity.class);


                startActivityForResult(intent,3);

                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3 && resultCode == 3){
            String tit = data.getStringExtra("tit");
            String da = data.getStringExtra("da");

            List<OneBean.DataBean> data1 = new OneBean().getData();
            data1.add(new OneBean.DataBean(da,tit));
            list.addAll(data1);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
