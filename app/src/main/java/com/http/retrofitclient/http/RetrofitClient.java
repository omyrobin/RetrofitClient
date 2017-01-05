package com.http.retrofitclient.http;

import com.http.retrofitclient.entity.BaseResponse;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Action0;

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

    public <T> void post(Observable ob, final ProgressSubscriber<T> subscriber, boolean addHeader){
        //数据预处理
        Observable.Transformer<Response<BaseResponse<T>>, T> result = ResponseHelper.transformerResult(addHeader);
        ob.compose(result)
            .doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    subscriber.showProgressDialog();
                }
            })
            .subscribe(subscriber);
    }

    public <T> void get(Observable ob, final ProgressSubscriber<T> subscriber){
        //数据预处理
        Observable.Transformer<Response<BaseResponse<T>>, T> result = ResponseHelper.transformerResult(false);
        ob.compose(result)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        subscriber.showProgressDialog();
                    }
                })
                .subscribe(subscriber);
    }

}
