package com.xaehu.person_module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xaehu.mvp_library.base.BaseStaticActivity;
import com.xaehu.person_module.R;
import com.xaehu.person_module.fragment.PersonFragment;

public class PersonMainActivity extends BaseStaticActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        FragmentManager fm = getSupportFragmentManager();
        if(fm != null) {
            Fragment fragment = fm.findFragmentById(R.id.activity_person_fragment);
            if(fragment == null){
                fragment = new PersonFragment();
                fm.beginTransaction().add(R.id.activity_person_fragment, fragment).commit();
            }
        }
    }

    @Override
    protected void initListener() {

    }
}
