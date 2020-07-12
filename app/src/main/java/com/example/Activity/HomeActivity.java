package com.example.Activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Fragment.BaiDuFragment;
import com.example.Fragment.ElvFragment;
import com.example.Fragment.FourFragment;
import com.example.zhonghe.HomeFragment;
import com.example.zhonghe.R;
import com.example.Fragment.ThreeFragment;
import com.example.Fragment.TwoFragment;
import com.example.Adapter.VpTabAdapter;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private ViewPager mVpTab;
    private TabLayout mTab;
    private LinearLayout mLl;
    private NavigationView mNv;
    private DrawerLayout mDl;
    private ArrayList<Fragment> list;
    private ImageView iv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initListener();
    }

    private void initListener() {
        View headerView = mNv.getHeaderView(0);
        iv_head = headerView.findViewById(R.id.iv_Head);
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "欢迎你", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        //我的音乐
                        Toast.makeText(HomeActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HomeActivity.this, MusicActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.item2:
                        //我的设置
                        Toast.makeText(HomeActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        //我的图片
                        Intent intent2 = new Intent(HomeActivity.this, ImageActivity.class);
                        startActivity(intent2);
                        Toast.makeText(HomeActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item4:
                        //开启消息提醒
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                        Intent intent1 = new Intent(HomeActivity.this, MyActivity.class);

                        String channelId = "1";
                        String channelName = "default";

                        PendingIntent activity = PendingIntent.getActivity(HomeActivity.this, 1, intent1, PendingIntent.FLAG_ONE_SHOT);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
                            channel.enableLights(true);
                            channel.setLightColor(R.color.colorAccent);
                            channel.setShowBadge(true);
                            manager.createNotificationChannel(channel);
                        }
                        Notification build = new NotificationCompat.Builder(HomeActivity.this, channelId)
                                .setContentTitle("我是不是最帅的？")
                                .setContentIntent(activity)
                                .setContentText("是的")
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setAutoCancel(true)
                                .setNumber(3)
                                .setDefaults(Notification.DEFAULT_VIBRATE)
                                .build();

                        manager.notify(1,build);
                        break;
                    case R.id.item5:
                        PermissionsUtil.requestPermission(HomeActivity.this, new PermissionListener() {
                            @Override
                            public void permissionGranted(@NonNull String[] permission) {
                                Intent intent = new Intent(Intent.ACTION_CALL);
                                intent.setData(Uri.parse("tel:10086"));
                                startActivity(intent);
                            }

                            @Override
                            public void permissionDenied(@NonNull String[] permission) {

                            }
                        }, new String[]{Manifest.permission.CALL_PHONE});
                        break;
                }
                return false;
            }
        });

        mDl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                int right = drawerView.getRight();
                mLl.setX(right);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mToolbar.setTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mVpTab = (ViewPager) findViewById(R.id.vp_tab);
        mTab = (TabLayout) findViewById(R.id.tab);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDl = (DrawerLayout) findViewById(R.id.dl);

        //设置toolbar
        setToolbar();
        //设置TabLayout
        setTab();
    }

    private void setTab() {
        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new TwoFragment());
        list.add(new ElvFragment());
        list.add(new ThreeFragment());
        list.add(new BaiDuFragment());
        list.add(new FourFragment());
        VpTabAdapter vpTabAdapter = new VpTabAdapter(getSupportFragmentManager(), list);

        mVpTab.setAdapter(vpTabAdapter);

        mTab.setupWithViewPager(mVpTab);

        mTab.getTabAt(0).setText("首页").setIcon(R.drawable.tab_selector);
        mTab.getTabAt(1).setText("我的").setIcon(R.drawable.tab1_selector);
        mTab.getTabAt(2).setText("二级").setIcon(R.drawable.tab5_selector);
        mTab.getTabAt(3).setText("收藏").setIcon(R.drawable.tab3_selector);
        mTab.getTabAt(4).setText("百度").setIcon(R.drawable.tab4_selector);
        mTab.getTabAt(5).setText("项目").setIcon(R.drawable.tab2_selector);
    }

    private void setToolbar() {
        mToolbar.setTitle("首页");
        setSupportActionBar(mToolbar);
        mNv.setItemIconTintList(null);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDl, mToolbar, R.string.app_name, R.string.app_name);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorAccent));
        mDl.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "扫一扫");
        menu.add(0, 0, 0, "添加好友");
        menu.add(0, 0, 0, "发起群聊");

        getMenuInflater().inflate(R.menu.item_option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1_op:
                View inflate = LayoutInflater.from(this).inflate(R.layout.item1_op_chazhao, null);
                final EditText chazhao = inflate.findViewById(R.id.ed_chazhao);

                new AlertDialog.Builder(this)
                        .setTitle("输入你要查找的数据")
                        .setView(inflate)
                        .setNegativeButton("查找", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String s = chazhao.getText().toString();
                                if (!TextUtils.isEmpty(s)) {
                                    Toast.makeText(HomeActivity.this, "查找不到该数据", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(HomeActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setPositiveButton("取消", null)
                        .create().show();
                break;
            case R.id.item2_op:
                View inflate1 = LayoutInflater.from(this).inflate(R.layout.item_popu, null);

                TextView qq = inflate1.findViewById(R.id.qq);
                TextView wx = inflate1.findViewById(R.id.wx);
                TextView wz = inflate1.findViewById(R.id.wz);
                TextView cj = inflate1.findViewById(R.id.cj);
                qq.setOnClickListener(this);

                PopupWindow pw = new PopupWindow(inflate1, 300, 340);
                pw.setBackgroundDrawable(new ColorDrawable());
                pw.setOutsideTouchable(true);

                bgAlpha(0.5f);
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        bgAlpha(1f);
                    }
                });
                pw.showAsDropDown(mToolbar,800,-50);
                break;
            case 0:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri data1 = data.getData();
            iv_head.setImageURI(data1);
        }
    }


    public void bgAlpha(float bgalpah){
        WindowManager.LayoutParams ab = getWindow().getAttributes();
        ab.alpha = bgalpah;
        getWindow().setAttributes(ab);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.qq:
                Toast.makeText(this, "QQ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wx:
                Toast.makeText(this, "微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.wz:
                Toast.makeText(this, "王者荣耀", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cj:
                Toast.makeText(this, "和平精英", Toast.LENGTH_SHORT).show();
                break;




        }




    }
}
