package com.http.retrofitclient.http;

import android.widget.Toast;

import com.http.retrofitclient.MyApplication;
import com.http.retrofitclient.utils.NetworkUtil;

import rx.Subscriber;

/**
 * Created by omyrobin on 2016/12/23.
 *  统一处理异常回调
 */

public abstract class BaseSubscriber<T> extends Subscriber<T> {

       @Override
       public void onStart() {
           super.onStart();

           if (!NetworkUtil.isConnected(MyApplication.getInstance().getApplicationContext())) {

                   Toast.makeText(MyApplication.getInstance().getApplicationContext(), "当前网络不可用，请检查网络情况", Toast.LENGTH_SHORT).show();
                   /**一定要主动调用下面这一句**/
                   onCompleted();
                   return;
           }
           // 显示进度条
//           showLoadingProgress();
       }

       @Override
       public void onCompleted() {
          //关闭等待进度条
//          closeLoadingProgress();
       }
    }