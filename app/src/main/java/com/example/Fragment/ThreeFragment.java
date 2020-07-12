package com.example.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Adapter.LvAdapter;
import com.example.Bean.SqlBean;
import com.example.zhonghe.R;
import com.example.zhonghe.ThreeSql;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {


    private View view;
    private ListView mLv;
    private ArrayList<SqlBean> list;

    public ThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_three, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mLv = (ListView) inflate.findViewById(R.id.lv);


        list = new ArrayList<>();

        final ThreeSql threeSql = new ThreeSql(getActivity());

        SQLiteDatabase db = threeSql.getReadableDatabase();

        Cursor food = db.query("food", null, null, null, null, null, null);
        while (food.moveToNext()){
            String image = food.getString(food.getColumnIndex("image"));
            String title = food.getString(food.getColumnIndex("title"));
            String data = food.getString(food.getColumnIndex("data"));
            list.add(new SqlBean(image,title,data));
        }

        final LvAdapter lvAdapter = new LvAdapter(getActivity(), list);
        mLv.setAdapter(lvAdapter);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("是否删除")
                        .setNegativeButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ThreeSql threeSql1 = new ThreeSql(getActivity());
                                SQLiteDatabase db = threeSql.getReadableDatabase();
                                String title = list.get(position).getTitle();
                                db.delete("food","title=?",new String[]{title});
                                list.remove(position);
                                lvAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("取消",null)
                        .create().show();
            }
        });
    }
}
