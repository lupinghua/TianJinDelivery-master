package com.inspur.tianjindelivery.ui.other;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.adapter.GroupListMainAdapter;
import com.inspur.tianjindelivery.entiy.GroupListMainEntity;
import com.inspur.tianjindelivery.utils.base.BaseActivity;
import com.inspur.tianjindelivery.utils.tools.ToastUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${lupinghua} on 2018/3/19.
 */

public class GroupListMainActivity extends BaseActivity {

    private String TAG = "GroupListMainActivity";
    private RecyclerView mRecycleView;
    private int start = 0;
    private String length = "10";
    private GroupListMainAdapter groupListMainAdapter;
    private Intent intent;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_grouplistmain);

    }

    @Override
    public void initViews() {
        mRecycleView = findViewById(R.id.mRecycleView);


    }

    @Override
    public void initData() {
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        getData();


    }

    public void getData() {
        mRequestClient.querySiteList(start + "", length, "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupListMainEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupListMainEntity groupListEntity) {
                        Log.e(TAG, "站址列表成功返回:" + groupListEntity.toString());
                        if (groupListEntity.getSiteInfoList().size() > 0) {
                            groupListMainAdapter = new GroupListMainAdapter(R.layout.item_tmline_list, groupListEntity.getSiteInfoList());
                            mRecycleView.setAdapter(groupListMainAdapter);
                            initAdapter(groupListMainAdapter);
                            groupListMainAdapter.loadMoreComplete();
                        } else {
                            if (null != groupListMainAdapter){
                                groupListMainAdapter.loadMoreEnd();
                            }
                            ToastUtil.show(GroupListMainActivity.this, "没有更多数据", 0);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "站址列表成功error:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void initAdapter(GroupListMainAdapter groupListMainAdapter) {
        groupListMainAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        if (null != groupListMainAdapter) {
            groupListMainAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    start++;
                    getData();
                }
            });
            groupListMainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.show(GroupListMainActivity.this, position + "", 0);
                    intent = new Intent(GroupListMainActivity.this, GroupListSecondActivity.class);
                    startActivity(intent);
                }
            });
        }
    }


}
