package com.xaehu.detail_module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xaehu.detail_module.R;
import com.xaehu.detail_module.fragment.SearchDetailFragment;
import com.xaehu.mvp_library.base.BaseConstant;
import com.xaehu.mvp_library.base.BaseStaticActivity;
import com.xaehu.mvp_library.utils.ActivityUtils;

public class SearchDetailMainActivity extends BaseStaticActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_detail_main;
    }

    @Override
    protected void initView() {
        ActivityUtils.getInstance().setContext(getApplicationContext());
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BaseConstant.IS_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(getApplication());
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
