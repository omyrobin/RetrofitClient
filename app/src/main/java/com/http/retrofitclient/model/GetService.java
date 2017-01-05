package com.http.retrofitclient.model;

import com.http.retrofitclient.entity.BaseResponse;
import com.http.retrofitclient.entity.UserEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by omyrobin on 2016/12/13.
 */

public interface GetService {
    @GET
    Observable<Response<BaseResponse<UserEntity>>> getUserInfo(@Url String url, @HeaderMap Map<String, String> headers);
}
