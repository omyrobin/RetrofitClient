package com.http.retrofitclient.http;

import android.util.Log;


import com.http.retrofitclient.model.ApiService;
import com.http.retrofitclient.model.GetService;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by omyrobin on 2016/12/12.
 */

public class RetrofitClient {

    private static volatile RetrofitClient client;

    private RetrofitClient(){

    }

    public static RetrofitClient client(){
        if(client == null){
            synchronized (RetrofitClient.class){
                if(client == null){
                    client = new RetrofitClient();
                }
            }
        }
        return client;
    }

    public <T> void post(final String url, JSONObject values, final ResponseCallBack<T> callBack){
        this.post(url, values, callBack, null);
    }

    public <T> void get(final String url, final ResponseCallBack<T> callBack){
        this.get(url, callBack, null);
    }

    public <T> void post(final String url, JSONObject values, final ResponseCallBack<T> callBack, final String name){
        ApiService apiService = RetrofitManager.getRetrofitClient().create(ApiService.class);
        Call<String> call = apiService.post(url, values.toString());
        call.enqueue( new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("数据",response.body());
                sendSuccessMessage(response, callBack, name);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBack.onFailure("请求超时");
            }
        });
    }

    public <T> void get(final String url, final ResponseCallBack<T> callBack, final String name){
        GetService apiService = RetrofitManager.getRetrofitClient().create(GetService.class);
        Call<String> call = apiService.get(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                sendSuccessMessage(response, callBack, name);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                callBack.onFailure("请求超时");
            }
        });
    }

    private <T> void sendSuccessMessage(Response<String> response, ResponseCallBack<T> callBack, String name){
        switch (response.code()){
            case 200:
                ResponseJsonCallBack call = (ResponseJsonCallBack) callBack;
                call.parseT(response,name);
                break;
            case 401:
                callBack.onFailure("您还没有登录,请登录后再做此操作");
                break;
            case 404:
                callBack.onFailure("未找到页面");
                break;
            case 406:
                callBack.onFailure("客户端接受类型错误");
                break;
            default:
                callBack.onFailure("获取数据失败");
                break;
        }
    }
}
