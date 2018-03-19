package com.inspur.tianjindelivery.ui.other;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.entiy.GroupListSecondEntity;
import com.inspur.tianjindelivery.utils.base.BaseActivity;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${lupinghua} on 2018/3/19.
 */

public class GroupListSecondActivity extends BaseActivity {

    private String TAG = "GroupListSecondActivity";
    private LinearLayout ll_content;
    private String UID = "8582311";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_grouplistsecond);
    }

    @Override
    public void initViews() {
        ll_content = findViewById(R.id.ll_content);

    }

    @Override
    public void initData() {
        getData();
    }

    private void getData() {
        mRequestClient.queryBasicSiteInfo(UID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupListSecondEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupListSecondEntity groupListSecondEntity) {
                        Log.e(TAG, "成功:" + groupListSecondEntity.toString());
                        if (groupListSecondEntity.getSiteInfoList().size()>0){
                            initLayout(groupListSecondEntity.getSiteInfoList());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "返回的error:" + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initLayout(List<GroupListSecondEntity.SiteInfoListBean> siteInfoListBean) {
        for (int i = 0; i < siteInfoListBean.size(); i++) {
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textView1 = new TextView(this);
            textView.setLayoutParams(layoutParams);
            textView1.setLayoutParams(layoutParams);
            textView.setText(siteInfoListBean.get(i).getDisplay_label());
            textView1.setText(siteInfoListBean.get(i).getDisplay_value());
            ll_content.addView(textView);
            ll_content.addView(textView1);
        }


    }

}
