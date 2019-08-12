package com.xaehu.mvp_library.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

/**
 * @author xaeHu
 * 单例模式工具类：含有外部引用对象的公共方法写到这里
 */
public class ActivityUtils {
    private static ActivityUtils utils;
    private Context context;

    public static ActivityUtils getInstance(){
        if(null == utils){
            synchronized (ActivityUtils.class){
                if(null == utils){
                    utils = new ActivityUtils();
                }
            }
        }
        return utils;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public Context getAppContext(){
        return context;
    }

    /**
     * 适配器绑定纵向列表控件
     * @param adapter 适配器
     * @param recyclerView 列表控件
     */
    public void bindToRecyclerView(BaseQuickAdapter adapter, RecyclerView recyclerView){
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter.bindToRecyclerView(recyclerView);
    }

    /**
     * 适配器绑定列表控件
     * @param adapter 适配器
     * @param recyclerView 列表控件
     * @param isHorizontal 是否横向，否为纵向
     */
    public void bindToRecyclerView(BaseQuickAdapter adapter, RecyclerView recyclerView, boolean isHorizontal){
        recyclerView.setLayoutManager(new LinearLayoutManager(context,
                isHorizontal?LinearLayoutManager.HORIZONTAL:LinearLayoutManager.VERTICAL,false));
        adapter.bindToRecyclerView(recyclerView);
    }

    /**
     * 收起输入法
     */
    public void hideInput(Activity ctx){
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm!=null){
            imm.hideSoftInputFromWindow(ctx.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
