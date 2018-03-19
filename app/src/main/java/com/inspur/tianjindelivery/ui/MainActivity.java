package com.inspur.tianjindelivery.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.adapter.MainPageAdapter;
import com.inspur.tianjindelivery.entiy.MainPageEntity;
import com.inspur.tianjindelivery.ui.other.GroupListMainActivity;
import com.inspur.tianjindelivery.ui.tmline.TMLineMainActivity;
import com.inspur.tianjindelivery.utils.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by lixu on 2018/3/13.
 */

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private MainPageAdapter mainPageAdapter;
    private List<MainPageEntity> textList = new ArrayList<>();
    private Context mContext;
    private TextView tvTitle;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {

        mContext = MainActivity.this;
        recyclerView = findViewById(R.id.rv_main);
        tvTitle = findViewById(R.id.toolbar_title);
        tvTitle.setText(R.string.app_name);
        //mToolbar.setTitle("");

        mainPageAdapter = new MainPageAdapter(mContext, R.layout.item_main_page, textList);
        gridLayoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mainPageAdapter);
        mainPageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    //基站设备及配套
                    case 0:
                        Intent groupListIntent = new Intent(mContext, GroupListMainActivity.class);
                        startActivity(groupListIntent);
                        break;
                    //传输线路
                    case 1:
                        Intent tmIntent = new Intent(mContext, TMLineMainActivity.class);
                        startActivity(tmIntent);
                        break;
                    //直放站
                    case 2:
                        break;
                    //铁塔
                    case 3:
                        break;
                    //室分
                    case 4:
                        break;
                    //WLAN
                    case 5:
                        break;
                    //集客
                    case 6:
                        break;
                    //家客
                    case 7:
                        break;


                }
            }
        });
    }

    @Override
    public void initData() {
        textList.clear();
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "基站设备及配套"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "传输线路"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "直放站"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "铁塔"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "室分"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "WLAN"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "集客"));
        textList.add(new MainPageEntity(R.drawable.icon_main_jzsb, "家客"));
        mainPageAdapter.notifyDataSetChanged();

        //添加相机，存储权限
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_WIFI_STATE
        ).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {

                } else {
                    // 用户拒绝了该权限，并且选中不再询问,引导用户跳转权限管理页面
                }
            }
        });

    }
}
