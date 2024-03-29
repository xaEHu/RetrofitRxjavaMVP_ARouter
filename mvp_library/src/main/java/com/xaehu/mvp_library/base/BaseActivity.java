package com.xaehu.mvp_library.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xaehu.mvp_library.R;
import com.xaehu.mvp_library.mvp.IView;

import java.lang.reflect.ParameterizedType;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Objects;

import retrofit2.HttpException;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    private P p;
    private View emptyView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        initListener();
        initSetting();
    }

    protected abstract void initView();

    protected void initSetting(){
        //左侧添加一个默认的返回图标
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(hasBackBtn());
            //设置返回键可用
            getSupportActionBar().setHomeButtonEnabled(hasBackBtn());
        }
    }

    /**
     * @return 是否有返回按钮
     * 需要有的话重写此方法返回true
     */
    protected boolean hasBackBtn(){
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toolbar的事件---返回
        if(item.getItemId() == android.R.id.home){
            if(hasBackBtn()){
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void showLongToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 获取Presenter
     */
    public P getP(){
        if(p == null){
            //泛型形式实例化P层
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Class<P> clazz = (Class<P>) parameterizedType.getActualTypeArguments()[0];
            try {
                p = clazz.newInstance();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(p != null){
            if(!p.isAttachedV()){
                p.attachView(this);
            }
        }
        return p;
    }

    /**
     * 错误处理
     */
    public void onError(Throwable e){
        if (e instanceof UnknownHostException) {
            showLongToast("请打开网络");
        } else if (e instanceof SocketTimeoutException) {
            showLongToast("请求超时");
        } else if (e instanceof ConnectException) {
            showLongToast("连接失败");
        } else if (e instanceof HttpException) {
            showLongToast("请求超时");
        }else {
            showLongToast("请求失败:"+e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(getP()!=null){
            getP().detachView();
        }
        p = null;
    }

    /**
     * 设置适配器的空布局
     * @param adapter 适配器
     * @param msg 空布局文字提示
     * @param imgResId 空布局图片资源，若isLoad为true则不生效
     * @param isLoad 是否是加载中
     */
    public void setAdapterEmptyView(BaseQuickAdapter adapter, String msg, int imgResId, boolean isLoad){
        if(emptyView == null){
            emptyView = getLayoutInflater().inflate(R.layout.view_empty, null);
        }
        ((TextView)emptyView.findViewById(R.id.textView_msg)).setText(msg);
        if(isLoad){
            emptyView.findViewById(R.id.imageView_img).setVisibility(View.GONE);
            emptyView.findViewById(R.id.progressBar_loading).setVisibility(View.VISIBLE);
        }else{
            ((ImageView)emptyView.findViewById(R.id.imageView_img)).setImageResource(imgResId);
            emptyView.findViewById(R.id.imageView_img).setVisibility(View.VISIBLE);
            emptyView.findViewById(R.id.progressBar_loading).setVisibility(View.GONE);
        }
        adapter.getData().clear();
        adapter.setEmptyView(emptyView);
        adapter.notifyDataSetChanged();
    }

    /**
     * 显示错误布局
     * @param adapter recyclerView的适配器
     * @param msg 错误提示信息
     */
    public void showErrorView(BaseQuickAdapter adapter,String msg){
        //// TODO: 2019/6/5 替换错误布局图片
        setAdapterEmptyView(adapter,msg, R.drawable.ic_launcher,false);
    }

    /**
     * 显示空布局
     * @param adapter recyclerView的适配器
     */
    public void showEmptyView(BaseQuickAdapter adapter){
        //// TODO: 2019/6/5 替换空布局图片
        setAdapterEmptyView(adapter,"空空如也", R.drawable.ic_launcher,false);
    }

    /**
     * 显示加载中布局
     * @param adapter recyclerView的适配器
     */
    public void showLoadView(BaseQuickAdapter adapter){
        setAdapterEmptyView(adapter,"加载中……",0,true);
    }
}
