package com.http.retrofitclient.model;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by omyrobin on 2016/12/13.
 */

public interface GetService {
    @GET
    Observable<Response<String>> get(@Url String url);
}
