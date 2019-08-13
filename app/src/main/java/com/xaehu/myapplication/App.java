package com.xaehu.myapplication;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xaehu.mvp_library.base.BaseConstant;
import com.xaehu.mvp_library.utils.ActivityUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityUtils.getInstance().setContext(getApplicationContext());

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BaseConstant.IS_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }

}
