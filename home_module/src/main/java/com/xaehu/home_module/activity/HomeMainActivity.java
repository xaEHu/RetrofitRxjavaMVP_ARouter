package com.xaehu.home_module.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xaehu.home_module.R;
import com.xaehu.home_module.fragment.HomeFragment;
import com.xaehu.mvp_library.base.BaseConstant;
import com.xaehu.mvp_library.base.BaseStaticActivity;
import com.xaehu.mvp_library.utils.ActivityUtils;

/**
 * @author : xaeHu
 * e-mail : hsfemail@qq.com
 * @date : 2019/8/12 17:03
 * desc   :
 */
public class HomeMainActivity extends BaseStaticActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.acticity_home;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
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
