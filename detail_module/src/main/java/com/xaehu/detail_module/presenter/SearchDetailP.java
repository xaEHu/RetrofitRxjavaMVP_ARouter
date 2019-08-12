package com.xaehu.detail_module.presenter;

import com.xaehu.detail_module.fragment.SearchDetailFragment;
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

/**
 * @author : xaeHu
 * @e-mail : hsfemail@qq.com
 * @date : 2019/7/9 10:41
 * @desc :
 */
public class SearchDetailP extends BasePresenter<SearchDetailFragment> {

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
                        getV().showData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getV().onError(e);
                    }
                });

    }
}
