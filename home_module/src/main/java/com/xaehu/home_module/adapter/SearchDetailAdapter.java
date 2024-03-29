package com.xaehu.home_module.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xaehu.home_module.R;
import com.xaehu.mvp_library.bean.KugouDetail;
import com.xaehu.mvp_library.utils.GlideUtils;
import com.xaehu.mvp_library.utils.StaticUtils;

import java.util.List;

public class SearchDetailAdapter extends BaseItemDraggableAdapter<KugouDetail, BaseViewHolder> {
    public SearchDetailAdapter(@Nullable List<KugouDetail> data) {
        super(R.layout.adapter_detail,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KugouDetail item) {
        GlideUtils.loadImage(mContext,item.getAlbum_img().replace("{size}","100"), (ImageView) helper.getView(R.id.iv_music));
        helper.setText(R.id.tv_song,item.getSongName());
        helper.setText(R.id.tv_singer,item.getSingerName());
        helper.setText(R.id.tv_time, StaticUtils.sToMin(item.getTimeLength()));
        int color;
        if(item.getUrl()!=null && !"".equals(item.getUrl())){
            color = Color.BLACK;
        }else{
            color = Color.GRAY;
        }
        helper.setTextColor(R.id.tv_song, color);
        helper.setTextColor(R.id.tv_singer, color);
        helper.setTextColor(R.id.tv_time, color);
    }
}
