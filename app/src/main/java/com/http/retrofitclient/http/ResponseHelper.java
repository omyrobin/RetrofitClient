package com.http.retrofitclient.http;

import com.http.retrofitclient.entity.BaseResponse;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ResponseHelper {
    /**
     * 对结果进行Transformer处理
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<Response<BaseResponse<T>>, T> transformerResult(final boolean addHeader) {
        return new Observable.Transformer<Response<BaseResponse<T>>, T>() {
            @Override
            public Observable<T> call(Observable<Response<BaseResponse<T>>> tObservable) {
                return tObservable.flatMap(new Func1<Response<BaseResponse<T>>, Observable<T>>() {
                    @Override
                    public Observable<T> call(Response<BaseResponse<T>> result) {
                        if(addHeader){
                            RetrofitManager.setHeaders(result.headers());
                        }
                        if (result.body().getErrorCode() == 0) {
                            return createData(result.body().getData());
                        } else {
                            return Observable.error(new RuntimeException(result.body().getMessage()));
                        }
                    }
                }).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(data);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}