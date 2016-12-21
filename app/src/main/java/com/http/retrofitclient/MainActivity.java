package com.http.retrofitclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.http.retrofitclient.constant.Url;
import com.http.retrofitclient.entity.StoryInfoEntity;
import com.http.retrofitclient.entity.UserEntity;
import com.http.retrofitclient.http.ResponseJsonCallBack;
import com.http.retrofitclient.http.RetrofitClient;
import com.http.retrofitclient.http.RetrofitManager;
import com.http.retrofitclient.http.RetrofitParams;

import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button getRequestTv, postRequestTv, getRequestListTv, postRequestListTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView(){
        getRequestTv = (Button) findViewById(R.id.get_request_tv);
        postRequestTv = (Button) findViewById(R.id.post_request_tv);
        getRequestListTv = (Button) findViewById(R.id.get_request_list_tv);
        postRequestListTv = (Button) findViewById(R.id.post_request_list_tv);
    }

    private void initListener(){
        getRequestTv.setOnClickListener(this);
        postRequestTv.setOnClickListener(this);
        getRequestListTv.setOnClickListener(this);
        postRequestListTv.setOnClickListener(this);
    }

    private void getObject() {
        RetrofitClient.client().get(String.format(Url.READING, "577014914611343360"), new ResponseJsonCallBack<String>() {

            @Override
            public void onSuccess(Headers headers, String str) {
                Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }

    private void getListObject() {
        RetrofitClient.client().get( String.format(Url.SEARCH, "1", "q=的",""), new ResponseJsonCallBack<StoryInfoEntity>() {

            @Override
            public void onSuccess(Headers headers, List<StoryInfoEntity> storyInfoEntities) {
                Toast.makeText(MainActivity.this,storyInfoEntities.get(3).name,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        }, "story");

    }

    private void postListObject() {
        //封装请求参数
        RetrofitParams params = new RetrofitParams();
        params.put("id", "500000286");
        RetrofitClient.client().post(Url.MYSHELF, params.create(), new ResponseJsonCallBack<StoryInfoEntity>() {

            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onSuccess(Headers headers, List<StoryInfoEntity> storyInfoEntities) {
                Toast.makeText(MainActivity.this,storyInfoEntities.get(5).name,Toast.LENGTH_SHORT).show();
            }

        }, "storyShelf");

    }

    private void postObject() {
        //封装请求参数
        RetrofitParams params = new RetrofitParams();
        params.put("id", "500000286");
        params.put("type", "2");

        RetrofitClient.client().post(Url.LOGIN, params.create(), new ResponseJsonCallBack<UserEntity>() {
            @Override
            public void onSuccess(Headers headers, UserEntity userEntity) {
                RetrofitManager.addHeaders(headers);
                Toast.makeText(MainActivity.this,userEntity.nickName,Toast.LENGTH_SHORT).show();
                Log.i("UserEntity", userEntity.nickName);
            }

            @Override
            public void onFailure(String errorMsg) {
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }, "userEntity");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_request_tv:
                getObject();
                break;

            case R.id.post_request_tv:
                postObject();
                break;

            case R.id.get_request_list_tv:
                getListObject();
                break;

            case R.id.post_request_list_tv:
                postListObject();
                break;

        }
    }
}
