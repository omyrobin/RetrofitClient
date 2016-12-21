package com.http.retrofitclient.http;

import java.util.List;

import okhttp3.Headers;

/**
 * Created by omyrobin on 2016/12/12.
 */

public interface ResponseCallBack<T>{

    void onSuccess(Headers headers, T t);

    void onSuccess(Headers headers, List<T> t);

    void onFailure(String errorMsg);

}
