package com.inspur.tianjindelivery.model;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.inspur.tianjindelivery.entiy.RecordLocationEntity;
import com.inspur.tianjindelivery.entiy.eventbean.MyEvent;
import com.inspur.tianjindelivery.utils.base.BaseViewModel;
import com.inspur.tianjindelivery.utils.tools.SPUtil;
import com.inspur.tianjindelivery.utils.tools.ToastUtil;
import com.inspur.tianjindelivery.utils.tools.ToolUtil;


import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lixu on 2018/1/23.
 */

public class LocationModel extends BaseViewModel {
    public LocationModel(Application application) {
        super(application);
    }

    private MutableLiveData<LatLng> locationMutableLiveData;
    private AMap mAmap;
    private Context mContext;
    private MyLocationStyle myLocationStyle;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;



    private LatLng lastLocation;
    private LatLng currentLocation;
    private int DIS = 40;
    private int TOTAL_DISTANCE = 0;//总距离
    private int MAX_DISTANCE = 1000;//最大距离
    private List<RecordLocationEntity> mLocations = new ArrayList<>();
    private MutableLiveData<List<RecordLocationEntity>> locationList;
    private List<LatLng> latLngs = new ArrayList<>(); //所有的点
    private List<Polyline> polylines = new ArrayList<>(); //所有的线

    public MutableLiveData<LatLng> getlocationMutableLiveData() {
        if (locationMutableLiveData == null) {
            locationMutableLiveData = new MutableLiveData<>();
        }

        return locationMutableLiveData;
    }
    public MutableLiveData<List<RecordLocationEntity>> getlocationListMutableLiveData(){

        if(locationList == null){
            locationList = new MutableLiveData<>();
        }
//        if(!ToolUtil.isEmpty(mLocations)) {
//            locationList.postValue(mLocations);
//        }

        return locationList;
    }

//    public void getLiveData() {
//        if (locationMutableLiveData == null) {
//            locationMutableLiveData = new MutableLiveData<>();
//        }
//        locationMutableLiveData.postValue(locationMutableLiveData.getValue());
//    }

    public void getLocationListLiveData(){
        if(locationList == null){
            locationList = new MutableLiveData<>();
        }
        //if(!ToolUtil.isEmpty(mLocations)){
            locationList.postValue(mLocations);
       // }

    }
    public void getCurrentLocation(final Context context, final boolean isOnce,AMap aMap) {
        if(aMap != null){
            this.mAmap = aMap;
        }
        this.mContext = context;
        if(myLocationStyle == null) {
            myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        }
        //清空记录的点
        if(!isOnce && !ToolUtil.isEmpty(mLocations)){
            mLocations.clear();
            latLngs.clear();
            polylines.clear();
        }
        EventBus.getDefault().post(new MyEvent(MyEvent.OKK));
        if(mLocationOption == null){
            mLocationOption = new AMapLocationClientOption();
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {

                        LatLng latLng = new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());

                        if(!isOnce){

                          //  locationMutableLiveData.setValue(latLng);

                            if (lastLocation == null) {
                                //修改为多次定位
                                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
                                //mAmap.setMyLocationStyle(myLocationStyle);
                                //第一次不计算距离，直接add到轨迹点
                                lastLocation = new LatLng(latLng.latitude, latLng.longitude);
                                RecordLocationEntity recordLocationEntity = new RecordLocationEntity();
                                recordLocationEntity.setLatLng(lastLocation);
                                mLocations.add(recordLocationEntity);
                                latLngs.add(lastLocation);
                             //   recordLocationEntity = null;


                                Log.d("qqqq", "第一次直接add");
                            } else {
                                if (currentLocation != null) {
                                    currentLocation = null;
                                }
                                currentLocation = new LatLng(latLng.latitude, latLng.longitude);
                                double sDistance = convert(AMapUtils.calculateLineDistance(lastLocation, currentLocation));


                                Log.d("qqqq", "计算距离==" + sDistance);
                                ToastUtil.showToast(mContext,"距离="+sDistance+"==",ToastUtil.TOAST_TYPE_WARNING);
                                //和上一个点距离大于DIS才会记录，一直站着不动不会记录
                                if (sDistance > DIS) {

                                    //交割距离
                                    TOTAL_DISTANCE += DIS;
                                    Log.d("qqqq", "记录总距离==" + TOTAL_DISTANCE);
                                    //到达最大距离

                                    if (TOTAL_DISTANCE >= MAX_DISTANCE) {
                                        return;
                                    }
                                    Log.d("qqqq", "addList");
                                    //记录轨迹点加的list中
                                    RecordLocationEntity recordLocationEntity = new RecordLocationEntity();
                                    recordLocationEntity.setLatLng(lastLocation);
                                    mLocations.add(recordLocationEntity);


                                    latLngs.add(lastLocation);
                                    Polyline polyline = mAmap.addPolyline(new PolylineOptions().
                                            addAll(latLngs).width(10).color(Color.argb(255, 1, 1, 1)));
                                    polylines.add(polyline);

                                    ToastUtil.showToast(mContext,"划线"+polylines.size(),ToastUtil.TOAST_TYPE_SUCCESS);

                                    latLngs.remove(0);
                                    //记录上一个点
                                    lastLocation = null;
                                    lastLocation = currentLocation;
                                }
                            }
                        }



                        Log.d("qqq", "initMapData定位结束" + latLng.latitude + "==" + latLng.latitude);
                        locationMutableLiveData.postValue(latLng);

                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo());
                    }
                }
                EventBus.getDefault().post(new MyEvent(MyEvent.NOO));
            }
        });
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        if (isOnce) {
            //获取一次定位结果：
            //该方法默认为false。
            mLocationOption.setOnceLocation(true);
            //获取最近3s内精度最高的一次定位结果：
            mLocationOption.setOnceLocationLatest(true);
        }else{
            mLocationOption.setOnceLocation(false);
        }
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(10000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //缓存机制
        mLocationOption.setLocationCacheEnable(true);
        //初始化AMapLocationClientOption对象
     //   mLocationOption = new AMapLocationClientOption();
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.stopLocation();
        mLocationClient.startLocation();

        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
       // AMapLocationClientOption option = new AMapLocationClientOption();
//        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//        if (null != mLocationClient) {
//            mLocationClient.setLocationOption(option);
//            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//            mLocationClient.stopLocation();
//            mLocationClient.startLocation();
//        }


    }


    private void getAdress(final Context context, final Location location) {

        GeocodeSearch geocoderSearch = new GeocodeSearch(context);
        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                SPUtil ss = new SPUtil(mContext, SPUtil.USER);
                RegeocodeAddress aAdress = regeocodeResult.getRegeocodeAddress();
                String adressStr = aAdress.getCountry()
                        + aAdress.getProvince()
                        + aAdress.getCity()
                        + aAdress.getDistrict()
                        + aAdress.getStreetNumber().getStreet()
                        + aAdress.getStreetNumber().getNumber();
                ss.putString(SPUtil.LOCATION_ADRESS, adressStr);
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        LatLonPoint latLonPoint = new LatLonPoint(location.getLatitude(), location.getLongitude());
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
        geocoderSearch.getFromLocationAsyn(query);
    }

    private double convert(float value){
        long l1 = Math.round(value*100); //四舍五入
        double ret = l1/100.0; //注意:使用 100.0 而不是 100
        return ret;
    }


    //可在其中解析amapLocation获取相应内容。

                        /*amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                        amapLocation.getLatitude();//获取纬度
                        amapLocation.getLongitude();//获取经度
                        amapLocation.getAccuracy();//获取精度信息
                        amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                        amapLocation.getCountry();//国家信息
                        amapLocation.getProvince();//省信息
                        amapLocation.getCity();//城市信息
                        amapLocation.getDistrict();//城区信息
                        amapLocation.getStreet();//街道信息
                        amapLocation.getStreetNum();//街道门牌号信息
                        amapLocation.getCityCode();//城市编码
                        amapLocation.getAdCode();//地区编码
                        amapLocation.getAoiName();//获取当前定位点的AOI信息
                        amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                        amapLocation.getFloor();//获取当前室内定位的楼层
                        amapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                        //获取定位时间
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date(amapLocation.getTime());
                        df.format(date);*/
}

