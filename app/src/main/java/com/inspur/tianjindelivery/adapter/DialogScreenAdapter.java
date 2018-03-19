package com.inspur.tianjindelivery.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.inspur.tianjindelivery.R;
import com.inspur.tianjindelivery.entiy.eventbean.ScreenDialogBean;
import com.inspur.tianjindelivery.utils.tools.StringUtils;


import java.util.List;

/**
 * Created by lixu on 2018/3/7.
 */

public class DialogScreenAdapter extends BaseItemDraggableAdapter<ScreenDialogBean,BaseViewHolder> {


    private Context mContext;
    private boolean isBc;//是否设置背景
    /**
     *
     */
    public DialogScreenAdapter(Context context, int layoutResId, @Nullable List<ScreenDialogBean> data, boolean isBc1) {
        super(layoutResId, data);
        this.mContext = context;
        this.isBc = isBc1;
    }

    @Override
    protected void convert(BaseViewHolder helper, ScreenDialogBean item) {
        if(!StringUtils.isEmpty(item.getText())) {
            helper.setText(R.id.tv_item_dialog_tag, item.getText());
        }
    }
}
