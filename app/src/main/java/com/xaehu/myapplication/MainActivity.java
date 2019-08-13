package com.xaehu.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.flyco.tablayout.SlidingTabLayout;
import com.xaehu.mvp_library.base.BaseArouter;
import com.xaehu.mvp_library.base.BaseStaticActivity;

import java.util.ArrayList;

public class MainActivity extends BaseStaticActivity {

    private ViewPager viewpager;
    private SlidingTabLayout tab;
    private String []title = {"首页","搜索","详细搜索","个人中心"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        viewpager = findViewById(R.id.viewpager);
        tab = findViewById(R.id.tab);
    }

    @Override
    public void initData() {
        setTitle(title[0]);
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add((Fragment) ARouter.getInstance().build(BaseArouter.Home.HomeFragment).navigation());
        fragmentList.add((Fragment) ARouter.getInstance().build(BaseArouter.Search.SearchFragment).navigation());
        fragmentList.add((Fragment) ARouter.getInstance().build(BaseArouter.Detail.SearchDetailFragment).navigation());
        fragmentList.add((Fragment) ARouter.getInstance().build(BaseArouter.Person.PersonFragment).navigation());
        tab.setViewPager(viewpager,title,this, fragmentList);
//        viewpager.setOffscreenPageLimit(4);

    }

    @Override
    public void initListener() {
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setTitle(title[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
