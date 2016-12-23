package com.http.retrofitclient.model;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by omyrobin on 2016/9/18.
 */
public interface PostService {
    @FormUrlEncoded
    @POST
    Observable<Response<String>> post(@Url String url, @Field("req") String value);
}
