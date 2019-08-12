package com.xaehu.home_module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xaehu.home_module.R;
import com.xaehu.home_module.fragment.HomeFragment;
import com.xaehu.mvp_library.base.BaseStaticActivity;

/**
 * @author : xaeHu
 * e-mail : hsfemail@qq.com
 * @date : 2019/8/12 17:03
 * desc   :
 */
public class HomeActivity extends BaseStaticActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acticity_home;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm != null) {
            Fragment fragment = fm.findFragmentById(R.id.activity_home_fragment);
            if(fragment == null){
                fragment = new HomeFragment();
                fm.beginTransaction().add(R.id.activity_home_fragment, fragment).commit();
            }
        }
    }

    @Override
    protected void initListener() {

    }
}
