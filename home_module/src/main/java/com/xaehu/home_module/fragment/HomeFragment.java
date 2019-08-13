package com.xaehu.home_module.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xaehu.home_module.R;
import com.xaehu.home_module.activity.SearchActivity;
import com.xaehu.home_module.activity.SearchDetailActivity;
import com.xaehu.home_module.presenter.HomeP;
import com.xaehu.mvp_library.base.BaseArouter;
import com.xaehu.mvp_library.base.BaseFragment;

import java.util.Objects;

/**
 * @author : xaeHu
 * @e-mail : hsfemail@qq.com
 * @date : 2019/7/9 10:27
 * @desc : 首页Fragment
 */
@Route(path = BaseArouter.Home.HomeFragment)
public class HomeFragment extends BaseFragment<HomeP> implements View.OnClickListener {
    private TextView tvMain;
    private TextView tvDetail;
    @Override
    protected void initView(View view) {
        tvMain = view.findViewById(R.id.tv_main);
        tvDetail = view.findViewById(R.id.tv_detail);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tvMain.setOnClickListener(this);
        tvDetail.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
//        Intent intent = new Intent();
//        if(v == tvMain){
//            intent.setClass(Objects.requireNonNull(getActivity()), SearchActivity.class);
//        }else if(v == tvDetail){
//            intent.setClass(Objects.requireNonNull(getActivity()), SearchDetailActivity.class);
//        }
//        startActivity(intent);

        if(v == tvMain) {
            ARouter.getInstance().build(BaseArouter.Home.SearchActivity).navigation();
        }else{
            ARouter.getInstance().build(BaseArouter.Home.SearchDetailActivity).navigation();
        }
    }
}
