package com.inspur.tianjindelivery.entiy;

import com.amap.api.maps.model.LatLng;

/**
 * Created by lixu on 2018/3/14.
 */

public class BaseLocationEntity {
    private double lat;
    private double lon;
    private LatLng latLng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
        this.lat = latLng.latitude;
        this.lon = latLng.longitude;
    }
}
