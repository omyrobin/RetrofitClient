package com.http.retrofitclient.model;


import com.http.retrofitclient.entity.BaseResponse;
import com.http.retrofitclient.entity.UserEntity;

import java.util.Map;

import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by omyrobin on 2016/9/18.
 */
public interface PostService {
    @FormUrlEncoded
    @POST
    Observable<Response<BaseResponse<UserEntity>>> register(@Url String url, @HeaderMap Map<String, String> maps, @FieldMap Map<String,String> value);
}
