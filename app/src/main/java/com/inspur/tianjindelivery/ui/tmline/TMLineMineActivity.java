package com.inspur.tianjindelivery.ui.tmline;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.adapter.TMLineListAdapter;
import com.inspur.tianjindelivery.model.CurrentLocationMapModel;
import com.inspur.tianjindelivery.model.LocationModel;
import com.inspur.tianjindelivery.utils.base.BaseActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2018/3/15.
 * 传输线路 我的
 */

public class TMLineMineActivity extends BaseActivity {
    private MapView mMapView = null;
    private AMap aMap;
    private ImageView ivCancle;
    private ImageView ivLocation;
    private CurrentLocationMapModel currentLocationMapModel;
    private LocationModel locationModel;


    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;
    private TextView tv_actionbar_line;


    private TMLineListAdapter tmLineListAdapter;
    private List<String> tmlineList = new ArrayList<>();



    private double mCircumference = 500;//方圆多少米
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tmline_list);
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mContext = TMLineMineActivity.this;
        swipeRefreshLayout = findViewById(R.id.swipe_tmline);
        ivLocation = findViewById(R.id.iv_location);
        recyclerView = findViewById(R.id.rv_tmline);
        tv_actionbar_line = findViewById(R.id.activity_login_tv_xs);
        ivCancle = findViewById(R.id.iv_cancle);
        ivCancle.setOnClickListener(this);
        ivLocation.setOnClickListener(this);

        initAdapter();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        locationModel = ViewModelProviders.of(this).get(LocationModel.class);
        locationModel.getlocationMutableLiveData().observe(this, new Observer<LatLng>() {
            @Override
            public void onChanged(@Nullable LatLng latLng) {
                if(latLng != null) {
                    aMap.clear();

                    //当前位置
                    MarkerOptions markerOption = new MarkerOptions();
                    markerOption.position(latLng);
                    markerOption.draggable(false);//设置Marker不可拖动
                    markerOption.title("当前位置");//.snippet("西安市：34.341568, 108.940174");
                    markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(mContext.getResources(), R.drawable.icon_current)));
                    aMap.addMarker(markerOption);

                    //方圆的圈
                    CircleOptions circleOptions = new CircleOptions();
                    circleOptions.center(latLng);
                    circleOptions.radius(mCircumference);
                    circleOptions.strokeWidth(1);
                    circleOptions.strokeColor(R.color.map_cicle);
                    circleOptions.fillColor(R.color.map_cicle);
                    aMap.addCircle(circleOptions);
                    ////参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 0));
                    aMap.animateCamera(mCameraUpdate);






                    //去请求数据
                }
            }
        });


        currentLocationMapModel = ViewModelProviders.of(this).get(CurrentLocationMapModel.class);
        currentLocationMapModel.getlocationMutableLiveData().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {

            }
        });


        initChenJin();
    }

    @Override
    public void initData() {



            //开始交割时调用
        //currentLocationMapModel.getCurrentLocation(mContext, aMap, true);

        //test
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmlineList.add("1");
        tmLineListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_cancle:
                finish();
                break;
            case R.id.iv_location:
                locationModel.getCurrentLocation(mContext,true,null);
                break;
        }
    }

    private void initAdapter() {
        tmLineListAdapter = new TMLineListAdapter(mContext, R.layout.item_tmline_list, tmlineList);
        linearLayoutManager = new LinearLayoutManager(TMLineMineActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(tmLineListAdapter);
        tmLineListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                //数据全部加载完毕
                tmLineListAdapter.loadMoreEnd();
                //成功获取更多数据
                //tmLineListAdapter.loadMoreComplete();
                //获取更多数据失败
                //tmLineListAdapter.loadMoreFail();
            }
        }, recyclerView);
        tmLineListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });


    }

    private void initChenJin() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            tv_actionbar_line.setVisibility(View.VISIBLE);
            // tv_actionbar_line.getBackground().setAlpha(0);
            int statusHeight = getStatusBarHeight();
            android.view.ViewGroup.LayoutParams lp = tv_actionbar_line.getLayoutParams();
            lp.height = statusHeight;
        }
    }

    /**
     * 获取状态栏的高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
