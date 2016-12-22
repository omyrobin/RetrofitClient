package com.http.retrofitclient.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by omyrobin on 2016/12/22.
 */

public class FileUtil {

    private static Context context;

    public static void instance(Context context){
        FileUtil.context = context;
    }

    /**
     * 获取该应用的缓存文件根目录(dirt novel photo)
     * 该方法中有小说.txt文件 不建议使用
     */
    public static File getCacheFile(){
        String packageName = context.getPackageName();
        File f;
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            f = new File(Environment.getExternalStorageDirectory()+File.separator+packageName);
        }else{
            f = new File(context.getCacheDir()+File.separator+packageName);
        }
        if(!f.exists())
            f.mkdirs();
        return f;
    }
}
