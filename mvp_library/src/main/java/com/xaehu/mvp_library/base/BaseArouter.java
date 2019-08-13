package com.xaehu.mvp_library.base;

/**
 * @author : xaeHu
 * e-mail : hsfemail@qq.com
 * @date : 2019/8/13 12:07
 * desc   :Arouter类注解声明path常量
 */
public class BaseArouter {
    /**
     * 主页模块
     */
    public class Home{
        public final static String HomeFragment = "/Home/HomeFragment";
        public final static String SearchActivity = "/Home/SearchActivity";
        public final static String SearchDetailActivity = "/Home/SearchDetailActivity";
    }

    /**
     * 搜索模块
     */
    public class Search{
        public final static String SearchFragment = "/Search/SearchFragment";
    }

    /**
     * 搜索详情模块
     */
    public class Detail{
        public final static String SearchDetailFragment = "/Detail/SearchDetailFragment";
    }

    /**
     * 个人中心模块
     */
    public class Person{
        public final static String PersonFragment = "/Person/PersonFragment";
    }
}
