package com.xaehu.home_module.presenter;


import com.xaehu.home_module.activity.SearchDetailActivity;
import com.xaehu.mvp_library.api.Api;
import com.xaehu.mvp_library.base.BaseConstant;
import com.xaehu.mvp_library.base.BasePresenter;
import com.xaehu.mvp_library.bean.KugouDetail;
import com.xaehu.mvp_library.bean.KugouSearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SearchDetailPresenter extends BasePresenter<SearchDetailActivity> {

    public void searchDetail(String name,final int page){
        if(name.isEmpty()){
            getV().showToast("内容不能为空");
            return;
        }
        Map<String,Object> map = new HashMap<>();
        map.put("keyword",name);
        map.put("page",page);
        map.put("pagesize", BaseConstant.PAGE_SIZE);
        Api.getApiService().searchKugou(map)
                .flatMap(new Function<KugouSearch, Observable<KugouSearch.DataBean.InfoBean>>() {
                    @Override
                    public Observable<KugouSearch.DataBean.InfoBean> apply(KugouSearch kugouSearch) throws Exception {
                        return Observable.fromIterable(kugouSearch.getData().getInfo());
                    }
                })
                .flatMap(new Function<KugouSearch.DataBean.InfoBean, ObservableSource<KugouDetail>>() {
                    @Override
                    public ObservableSource<KugouDetail> apply(KugouSearch.DataBean.InfoBean infoBean) throws Exception {
                        return Api.getApiService().getDetail(infoBean.getHash());
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<KugouDetail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<KugouDetail> value) {
                        if(getV()!=null){
                            getV().showData(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(getV()!=null){
                            getV().onError(e);
                        }
                    }
                });

    }
}
