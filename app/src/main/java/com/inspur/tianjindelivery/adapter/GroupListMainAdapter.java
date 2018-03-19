package com.inspur.tianjindelivery.adapter;

import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.entiy.GroupListMainEntity;

import java.util.List;

/**
 * Created by ${lupinghua} on 2018/3/19.
 */

public class GroupListMainAdapter extends BaseQuickAdapter<GroupListMainEntity.SiteInfoListBean,BaseViewHolder> {


    public GroupListMainAdapter(int layoutResId, @Nullable List<GroupListMainEntity.SiteInfoListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GroupListMainEntity.SiteInfoListBean item) {

        helper.setText(R.id.tv_major,item.getDisplay_value());
        // helper.setText(R.id.tv_status,item.);

    }
}
