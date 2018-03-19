package com.inspur.tianjindelivery.ui.tmline;

import android.animation.ObjectAnimator;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.entiy.RecordLocationEntity;
import com.inspur.tianjindelivery.entiy.eventbean.MyEvent;
import com.inspur.tianjindelivery.entiy.eventbean.ScreenDialogBean;
import com.inspur.tianjindelivery.model.CurrentLocationMapModel;
import com.inspur.tianjindelivery.model.LocationModel;
import com.inspur.tianjindelivery.utils.base.BaseActivity;
import com.inspur.tianjindelivery.utils.base.MyListPopuWindow;
import com.inspur.tianjindelivery.utils.tools.ToastUtil;
import com.inspur.tianjindelivery.utils.tools.ToolUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixu on 2018/3/14.
 * 传输线路
 */

public class TMLineMainActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {
    private MapView mMapView = null;
    private AMap aMap;
    //private CurrentLocationMapModel cLocationModel;
    private LocationModel cLocationModel;
    private Context mContext;
    private ImageView ivLocation;
    private Button zsl_go, zsl_error_report;


    //toolbar
    private TextView tvTitle;
    private ImageView ivCancle;
    private ImageView mToolbarCicle;
    private ObjectAnimator rotation;
    private double mCircumference = 500;//方圆多少米
    private LatLng currentLatLng;//当前位置  最新的经纬度
    private Marker currentMarker;//当前Marker
    private Circle currentCircle;//当前Marker  方圆的圈


    private MarkerOptions markerOption = new MarkerOptions();
    private CircleOptions circleOptions = new CircleOptions();


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tmline_main);
        mContext = TMLineMainActivity.this;
        //获取地图控件引用
        mMapView = findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void initViews() {
        tvTitle = findViewById(R.id.toolbar_title);
        ivLocation = findViewById(R.id.iv_location);
        ivCancle = findViewById(R.id.iv_cancle);
        mToolbarCicle = findViewById(R.id.toolbar_cicle);
        zsl_go = findViewById(R.id.zsl_go);
        zsl_error_report = findViewById(R.id.zsl_error_report);
        tvTitle.setText(R.string.tm_line);
        mToolbar.setTitle("");
        ivCancle.setVisibility(View.VISIBLE);

        ivCancle.setOnClickListener(this);
        ivLocation.setOnClickListener(this);
        zsl_go.setOnClickListener(this);
        zsl_error_report.setOnClickListener(this);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        setSupportActionBar(mToolbar);
    }

    @Override
    public void initData() {
        cicleAnime();
//
//        cLocationModel.getlocationMutableLiveData().observe(this, new Observer<LatLng>() {
//            @Override
//            public void onChanged(@Nullable LatLng latLng) {
//                cLocationModel.getLocationListLiveData();
//            }
//        });
        cLocationModel = ViewModelProviders.of(this).get(LocationModel.class);
        cLocationModel.getlocationListMutableLiveData().observe(this, new Observer<List<RecordLocationEntity>>() {
            @Override
            public void onChanged(@Nullable List<RecordLocationEntity> recordLocationEntities) {
                //得到记录在本地的点资源
                if (!ToolUtil.isEmpty(recordLocationEntities)) {
                    Log.d("qqqqq", "返回点==" + recordLocationEntities.size());
                } else {
                    Log.d("qqqqq", "返回点为空");
                }
                //去请求新数据
            }
        });


        //   locationModel = ViewModelProviders.of(this).get(LocationModel.class);
        cLocationModel.getlocationMutableLiveData().observe(this, new Observer<LatLng>() {
            @Override
            public void onChanged(@Nullable LatLng latLng) {
                if (latLng != null) {
                    //aMap.clear();
                    if (currentMarker == null) {
                        markerOption.position(latLng);
                        markerOption.draggable(false);//设置Marker不可拖动
                        markerOption.title("当前位置");//.snippet("西安市：34.341568, 108.940174");
                        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                .decodeResource(mContext.getResources(), R.drawable.icon_current)));
                        currentMarker = aMap.addMarker(markerOption);
                    } else {
                        markerOption.position(currentLatLng);
                        currentMarker.setMarkerOptions(markerOption);
                    }

                    if (currentCircle == null) {
                        circleOptions.center(latLng);
                        circleOptions.radius(mCircumference);
                        circleOptions.strokeWidth(1);
                        circleOptions.strokeColor(R.color.devide_line);
                        circleOptions.fillColor(R.color.devide_line);
                        currentCircle = aMap.addCircle(circleOptions);
                    } else {
                        currentCircle.setCenter(currentLatLng);
                    }


                    //清空地图的点、线资源
                    if (currentLatLng == null) {
                        ////参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
                        CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 12, 30, 0));
                        aMap.animateCamera(mCameraUpdate);

                    }
                    //记录当前最新的点
                    currentLatLng = latLng;
                }
            }
        });

        mToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_cancle:
                goBack();
                break;
            case R.id.iv_location:
                if (currentLatLng != null) {
                    if (currentCircle != null && circleOptions != null) {
                        currentCircle.setCenter(currentLatLng);
                    }
                    if (markerOption != null) {
                        markerOption.position(currentLatLng);
                        currentMarker.setMarkerOptions(markerOption);
                    }
                    CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(currentLatLng, 12, 30, 0));
                    aMap.animateCamera(mCameraUpdate);
                } else {
                    //获取当前经纬度
                    cLocationModel.getCurrentLocation(mContext, true,null);
                }


                cLocationModel.getLocationListLiveData();
                break;
            case R.id.zsl_go:
                cLocationModel.getCurrentLocation(mContext, false,aMap);
                break;
            case R.id.zsl_error_report:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long exitTime;
    private void goBack() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(mContext, "再按一次退出传输线路交割", ToastUtil.TOAST_TYPE_NOMAL);
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            //System.exit(0);
        }
    }


    private void cicleAnime() {
        rotation = ObjectAnimator.ofFloat(mToolbarCicle, "rotation", 0f, 359f);//最好是0f到359f，0f和360f的位置是重复的
        rotation.setRepeatCount(ObjectAnimator.INFINITE);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setDuration(1000);
        rotation.start();
    }

    @Subscribe
    public void onEvent(MyEvent event) {
        if (event.getType() == event.OKK) {
            mToolbarCicle.setVisibility(View.VISIBLE);
        } else {
            mToolbarCicle.setVisibility(View.GONE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_more:
                List mList = new ArrayList<>();
                mList.add(new ScreenDialogBean("离线数据", false));
                mList.add(new ScreenDialogBean("我的", false));
                MyListPopuWindow.showTopDialog(mContext, TMLineMainActivity.this, menuItem.getActionView(), R.id.action_more, mList, new MyListPopuWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:

                                break;
                            case 1:
                                Intent htIntent = new Intent(mContext, TMLineMineActivity.class);
                                startActivity(htIntent);
                                break;
                        }
//                        Intent htIntent = new Intent(mContext,HiddenTroubleActivity.class);
//                        startActivity(htIntent);
                    }
                });
                break;

        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        if (rotation != null && rotation.isStarted()) {
            rotation.cancel();
        }
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
