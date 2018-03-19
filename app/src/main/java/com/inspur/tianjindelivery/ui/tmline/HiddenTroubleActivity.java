package com.inspur.tianjindelivery.ui.tmline;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.model.CurrentLocationMapModel;
import com.inspur.tianjindelivery.utils.base.BaseActivity;

/**
 * Created by lixu on 2018/3/14.
 */

public class HiddenTroubleActivity extends BaseActivity{
    private CurrentLocationMapModel cLocationModel;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_hidden_trouble);
    }

    @Override
    public void initViews() {
        mToolbar.setTitle("隐患上报");
    }

    @Override
    public void initData() {
        cLocationModel = ViewModelProviders.of(this).get(CurrentLocationMapModel.class);
        cLocationModel.getlocationMutableLiveData().observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                //Log.d("qqqqqq","=========="+location.getLatitude());
            }
        });

        cLocationModel.getLiveData();

    }
}
