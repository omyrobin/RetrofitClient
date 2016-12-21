package com.http.retrofitclient.http;


import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import com.http.retrofitclient.MyApplication;
import com.http.retrofitclient.constant.Constant;
import com.http.retrofitclient.constant.Url;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by omyrobin on 2016/9/17.
 */
public class RetrofitManager {

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_RANGE = "Content-Range";
    public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ENCODING_GZIP = "gzip";
    public static final int DEFAULT_TIMEOUT = 15;
    public volatile static Retrofit retrofit;
    public volatile static OkHttpClient client;

    public static Retrofit getRetrofitClient(){
        if (retrofit == null) {
            synchronized (RetrofitManager.class) {
                initRetorfit(initClient());
            }
        }
        return retrofit;
    }

    private static Retrofit initRetorfit(OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Url.NOVEL_SERVER_ADDRESS)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //增加拦截器 支持header头
                .client(client)
                .build();
        return retrofit;
    }

    public static OkHttpClient initClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("C", getHeaderC())
                                .addHeader(HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Connection", "Keep-Alive")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        return client;
    }

    public static void addHeaders(Headers headers) {
        final String X_I = headers.get(Constant.HEADER_X_I);
        final String X_S = headers.get(Constant.HEADER_X_S);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("C", getHeaderC())
                                .addHeader(Constant.HEADER_X_I, X_I)
                                .addHeader(Constant.HEADER_X_S, X_S)
                                .addHeader(HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8")
                                .addHeader("Connection", "Keep-Alive")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        initRetorfit(client);
    }

    public static String getHeaderC() {
        TelephonyManager tm = (TelephonyManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String clientHeader = "android/model:" + Build.MODEL + ",SDK:Android"
                + Build.VERSION.RELEASE + "(API" + Build.VERSION.SDK_INT
                + ")-huoxing/"
                + "1.0.6.8"+"/"
                + "oppo"+ "/"
                + tm.getDeviceId();
        return clientHeader;
    }
}
