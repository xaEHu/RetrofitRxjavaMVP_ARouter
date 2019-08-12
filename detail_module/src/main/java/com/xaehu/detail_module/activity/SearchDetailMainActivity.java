package com.xaehu.detail_module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xaehu.detail_module.R;
import com.xaehu.detail_module.fragment.SearchDetailFragment;
import com.xaehu.mvp_library.base.BaseStaticActivity;

public class SearchDetailMainActivity extends BaseStaticActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_detail_main;
    }

    @Override
    protected void initView() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm != null) {
            Fragment fragment = fm.findFragmentById(R.id.activity_detail_fragment);
            if(fragment == null){
                fragment = new SearchDetailFragment();
                fm.beginTransaction().add(R.id.activity_detail_fragment, fragment).commit();
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
