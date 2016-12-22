package com.http.retrofitclient.model;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

/**
 * Created by omyrobin on 2016/9/26.
 */
public interface UpLoadService {
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part("filename") String name, @Part MultipartBody.Part file);
}
