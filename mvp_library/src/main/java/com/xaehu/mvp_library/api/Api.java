package com.xaehu.mvp_library.api;


import com.xaehu.mvp_library.base.BaseApi;

public class Api {
    private static ApiService apiService;

    public static ApiService getApiService(){
        if(apiService == null){
            synchronized (Api.class){
                if(null == apiService){
                    apiService = BaseApi.getInstance().getRetrofit().create(ApiService.class);
                }
            }
        }
        return apiService;
    }
}
