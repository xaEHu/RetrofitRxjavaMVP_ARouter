package com.xaehu.search_module.presenter;

import com.xaehu.mvp_library.api.Api;
import com.xaehu.mvp_library.base.BaseConstant;
import com.xaehu.mvp_library.base.BasePresenter;
import com.xaehu.mvp_library.bean.KugouSearch;
import com.xaehu.search_module.fragment.SearchFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : xaeHu
 * @e-mail : hsfemail@qq.com
 * @date : 2019/7/9 10:41
 * @desc :
 */
public class SearchP extends BasePresenter<SearchFragment> {
    public void search(String name,final int page){
        if(name.isEmpty()){
            getV().showToast("内容不能为空");
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("keyword",name);
        map.put("page",page);
        map.put("pagesize", BaseConstant.PAGE_SIZE);
        request(Api.getApiService().searchKugou(map), new OnRespListener<KugouSearch>() {
            @Override
            public void onSuccess(KugouSearch value) {
                getV().showData(value);
            }

            @Override
            public void onFailed(Throwable e) {
                getV().onError(e);
                if(page == 1){
                    getV().showError(e.getMessage());
                }else{
                    getV().loadFail();
                }
            }
        });
    }
}
