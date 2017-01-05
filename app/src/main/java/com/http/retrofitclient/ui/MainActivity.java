package com.http.retrofitclient.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.http.retrofitclient.R;
import com.http.retrofitclient.constant.Constant;
import com.http.retrofitclient.constant.Url;
import com.http.retrofitclient.entity.UserEntity;
import com.http.retrofitclient.http.ProgressSubscriber;
import com.http.retrofitclient.http.RetrofitClient;
import com.http.retrofitclient.http.RetrofitManager;
import com.http.retrofitclient.model.GetService;
import com.http.retrofitclient.model.PostService;
import com.http.retrofitclient.utils.BitmapUtil;
import com.http.retrofitclient.utils.KitKatUri;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button getRequestTv, postRequestTv, getRequestListTv, postRequestListTv,uploadRequestTv;

    private String untreatedFile;

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
        uploadRequestTv = (Button) findViewById(R.id.upload_request_tv);
    }

    private void initListener(){
        getRequestTv.setOnClickListener(this);
        postRequestTv.setOnClickListener(this);
        getRequestListTv.setOnClickListener(this);
        postRequestListTv.setOnClickListener(this);
        uploadRequestTv.setOnClickListener(this);
    }
    
    private void login(){
        Map<String, String> params = new HashMap<>();
        params.put("os","android");

        Observable ob = RetrofitManager.getRetrofitClient().create(PostService.class).register(Url.REGISTER, new HashMap<String, String>(), params);
        RetrofitClient.client().post(ob, new ProgressSubscriber<UserEntity>(MainActivity.this) {
            @Override
            protected void onSuccess(UserEntity userEntity) {
                Log.i("res", userEntity.getNickName());
            }

            @Override
            protected void onFailure(String message) {
                Log.i("res", message);
            }
        },true);
    }

    private void getObject() {
        Map<String, String> params = new HashMap<>();
        params.put("id","501215412");

        Observable ob = RetrofitManager.getRetrofitClient().create(GetService.class).getUserInfo("http://10.10.10.43:8080/story-app/m1/user/501215412", RetrofitManager.getHeaders());
        RetrofitClient.client().get(ob, new ProgressSubscriber<UserEntity>(MainActivity.this) {
            @Override
            protected void onSuccess(UserEntity userEntity) {
                Log.i("res", userEntity.getNickName());
            }

            @Override
            protected void onFailure(String message) {

            }
        });
    }

    private void getListObject() {

    }

    private void postListObject() {

    }

    private void postObject() {

    }

    public void getFilePath(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, Constant.SELECT_CHOOSER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            getUntreatedFile(requestCode, data);
            String compressedFile = BitmapUtil.compressImage(untreatedFile);
            Log.i("获取的文件路径", compressedFile);
            if(compressedFile!=null){
                upLoad(compressedFile);
            }
        }
    }

    /**
     * 获取未处理的图片路径
     * @param requestCode 相册还是拍照
     * @param data 数据
     * @return
     */
    private String getUntreatedFile(int requestCode,Intent data){
        if(requestCode == Constant.SELECT_CHOOSER){//是相册
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,  filePathColumn, null, null, null);
            if(cursor!=null){
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                untreatedFile = cursor.getString(columnIndex);
                cursor.close();
            }else{
                /***4.4得到的uri,需要以下方法来获取文件的路径***/
                untreatedFile = KitKatUri.getPath(this, selectedImage);
            }
        }
        return untreatedFile;
    }

    private void upLoad(String filePath){
        // 创建 RequestBody，用于封装 请求RequestBody
        File file = new File(filePath);
        Log.i("文件名", filePath.split("photo/")[1]);
        //设置文件的传输类型
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        //KEY = files
        MultipartBody.Part body = MultipartBody.Part.createFormData("files", file.getName(), requestFile);

//        RetrofitClient.client().upLoad(Url.getUploadImageUrl("user"),  filePath.split("photo/")[1], body, new ResponseJsonCallBack<String>() {
//            @Override
//            public void onFailure(String errorMsg) {
//
//            }
//
//            @Override
//            public void onSuccess(Headers headers, String url) {
//                Toast.makeText(MainActivity.this, url,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_request_tv:
                getObject();
                break;

            case R.id.post_request_tv:
                login();
                break;

            case R.id.get_request_list_tv:
                getListObject();
                break;

            case R.id.post_request_list_tv:
                postListObject();
                break;

            default:
                getFilePath();
                break;

        }
    }
}
