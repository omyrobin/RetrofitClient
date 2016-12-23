package com.http.retrofitclient.http;

import com.http.retrofitclient.model.PostService;
import com.http.retrofitclient.model.GetService;
import com.http.retrofitclient.model.UpLoadService;

import org.json.JSONObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public <T> void post(String url, JSONObject values, final ResponseCallBack<T> callBack, final String name){
        PostService postService = RetrofitManager.getRetrofitClient().create(PostService.class);
        Observable<Response<String>> call = postService.post(url, values.toString());
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Response<String>>() {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<String> response) {
                        sendSuccessMessage(response, callBack, name);
                    }
                });
    }

    public <T> void get(String url, final ResponseCallBack<T> callBack, final String name){
        GetService apiService = RetrofitManager.getRetrofitClient().create(GetService.class);
        Observable<Response<String>> call = apiService.get(url);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Response<String>>() {
                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure("请求超时");
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        sendSuccessMessage(response, callBack, name);
                    }
                });
    }

    public void upLoad(String url, String filename, MultipartBody.Part body, final ResponseCallBack callBack){
        UpLoadService upLoadService = RetrofitManager.getRetrofitClient().create(UpLoadService.class);
        Observable<Response<String>> call = upLoadService.upload(url,filename,body);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<Response<String>>() {
                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailure("请求超时");
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        callBack.onSuccess(response.headers(), response.body());
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
