package com.inspur.tianjindelivery.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.entiy.MainPageEntity;
import com.inspur.tianjindelivery.utils.tools.StringUtils;

import java.util.List;

/**
 * Created by lixu on 2018/2/6.
 *
 */

public class MainPageAdapter extends BaseQuickAdapter<MainPageEntity,BaseViewHolder>{
    private Context mContext;
    public MainPageAdapter(Context context,int layoutResId, @Nullable List<MainPageEntity> data) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MainPageEntity item) {
        if(!StringUtils.isEmpty(item.getText())) {
            helper.setText(R.id.tv_main_text, item.getText());
        }
        if(!StringUtils.isEmpty(item.getText())) {
            helper.setText(R.id.tv_main_text, item.getText());
           // helper.setImageDrawable(R.id.iv_main,ContextCompat.getDrawable(mContext, item.getImgId()));
            // 加载图片
            Glide.with(mContext).load(item.getImgId()).crossFade().into((ImageView) helper.getView(R.id.iv_main));
        }

    }
}
