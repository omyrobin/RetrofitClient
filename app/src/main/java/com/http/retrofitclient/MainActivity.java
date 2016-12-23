package com.http.retrofitclient;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.http.retrofitclient.constant.Constant;
import com.http.retrofitclient.constant.Url;
import com.http.retrofitclient.entity.StoryInfoEntity;
import com.http.retrofitclient.entity.UserEntity;
import com.http.retrofitclient.http.ResponseJsonCallBack;
import com.http.retrofitclient.http.RetrofitClient;
import com.http.retrofitclient.http.RetrofitManager;
import com.http.retrofitclient.http.RetrofitParams;
import com.http.retrofitclient.utils.BitmapUtil;
import com.http.retrofitclient.utils.KitKatUri;

import java.io.File;
import java.util.List;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        RetrofitClient.client().get( String.format(Url.SEARCH, "1", "q=幽",""), new ResponseJsonCallBack<StoryInfoEntity>() {

            @Override
            public void onSuccess(Headers headers, List<StoryInfoEntity> storyInfoEntities) {
                Toast.makeText(MainActivity.this,storyInfoEntities.get(2).name,Toast.LENGTH_LONG).show();
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

        RetrofitClient.client().upLoad(Url.getUploadImageUrl("user"),  filePath.split("photo/")[1], body, new ResponseJsonCallBack<String>() {
            @Override
            public void onFailure(String errorMsg) {

            }

            @Override
            public void onSuccess(Headers headers, String url) {
                Toast.makeText(MainActivity.this, url,Toast.LENGTH_SHORT).show();
            }
        });
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

            default:
                getFilePath();
                break;

        }
    }
}
