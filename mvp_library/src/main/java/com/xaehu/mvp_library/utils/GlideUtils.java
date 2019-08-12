package com.xaehu.mvp_library.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.xaehu.mvp_library.R;

public class GlideUtils {

    public static void loadImage(Context context, String url,int placeholder, int error, ImageView imageView){
        RequestOptions options = new RequestOptions()
                //占位图
                .placeholder(placeholder)
                //错误图
                .error(error)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(url).apply(options).into(imageView);
    }
    public static void loadImage(Context context, String url, ImageView imageView){
        loadImage(context,url, R.drawable.ic_launcher, R.drawable.ic_launcher,imageView);
    }
}
