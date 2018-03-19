package com.inspur.tianjindelivery.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.entiy.MainPageEntity;
import com.inspur.tianjindelivery.utils.tools.StringUtils;

import java.util.List;

/**
 * Created by lixu on 2018/3/15.
 * 传输线路 我的列表
 */

public class TMLineListAdapter extends BaseQuickAdapter<String,BaseViewHolder>{
    private Context mContext;
    public TMLineListAdapter(Context context, int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        //helper.setText(R.id.tv_major, item);
    }
}
