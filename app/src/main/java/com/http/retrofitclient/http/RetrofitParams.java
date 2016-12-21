package com.http.retrofitclient.http;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by omyrobin on 2016/12/16.
 */

public class RetrofitParams {

    protected final ConcurrentHashMap<String, String> urlParams;
    protected final ConcurrentHashMap<String, StreamWrapper> streamParams;
    protected final ConcurrentHashMap<String, FileWrapper> fileParams;
    protected final ConcurrentHashMap<String, Object> urlParamsWithObjects;
    protected String contentEncoding;
    protected boolean autoCloseInputStreams;

    public RetrofitParams() {
        this((Map)null);
    }

    public RetrofitParams(Map<String, String> source) {
        this.urlParams = new ConcurrentHashMap();
        this.streamParams = new ConcurrentHashMap();
        this.fileParams = new ConcurrentHashMap();
        this.urlParamsWithObjects = new ConcurrentHashMap();
        this.contentEncoding = "UTF-8";
        if(source != null) {
            Iterator i$ = source.entrySet().iterator();

            while(i$.hasNext()) {
                Map.Entry entry = (Map.Entry)i$.next();
                this.put((String)entry.getKey(), (String)entry.getValue());
            }
        }

    }

    public RetrofitParams(final String key, final String value) {
        this((Map)(new HashMap() {
            {
                this.put(key, value);
            }
        }));
    }

    public void put(String key, String value) {
        if(key != null && value != null) {
            this.urlParams.put(key, value);
        }
    }

    public void put(String key, Object value) {
        if(key != null && value != null) {
            this.urlParamsWithObjects.put(key, value);
        }

    }

    public void put(String key, int value) {
        if(key != null) {
            this.urlParams.put(key, String.valueOf(value));
        }

    }

    public void put(String key, long value) {
        if(key != null) {
            this.urlParams.put(key, String.valueOf(value));
        }

    }

    /**
     * 文件类型数据
     */
    public void put(String key, File file) throws FileNotFoundException {
        this.put(key, (File)file, (String)null, (String)null);
    }

    public void put(String key, String customFileName, File file) throws FileNotFoundException {
        this.put(key, (File)file, (String)null, customFileName);
    }

    public void put(String key, File file, String contentType) throws FileNotFoundException {
        this.put(key, (File)file, contentType, (String)null);
    }

    public void put(String key, File file, String contentType, String customFileName) throws FileNotFoundException {
        if(file != null && file.exists()) {
            if(key != null) {
                this.fileParams.put(key, new RetrofitParams.FileWrapper(file, contentType, customFileName));
            }

        } else {
            throw new FileNotFoundException();
        }
    }

    /**
     * 流类型数据
     */
    public void put(String key, InputStream stream) {
        this.put(key, (InputStream)stream, (String)null);
    }

    public void put(String key, InputStream stream, String name) {
        this.put(key, (InputStream)stream, name, (String)null);
    }

    public void put(String key, InputStream stream, String name, String contentType) {
        this.put(key, stream, name, contentType, this.autoCloseInputStreams);
    }

    public void put(String key, InputStream stream, String name, String contentType, boolean autoClose) {
        if(key != null && stream != null) {
            this.streamParams.put(key, RetrofitParams.StreamWrapper.newInstance(stream, name, contentType, autoClose));
        }

    }

    public void setAutoCloseInputStreams(boolean flag) {
        this.autoCloseInputStreams = flag;
    }

    public JSONObject create(){
        JSONObject req = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject params = new JSONObject();
        try {
            for(Map.Entry<String, String> entry : urlParams.entrySet()){
                params.put(entry.getKey(), entry.getValue());
            }
            data.put("data", params);
            req.put("req", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return req;
    }


    public static class StreamWrapper {
        public final InputStream inputStream;
        public final String name;
        public final String contentType;
        public final boolean autoClose;

        public StreamWrapper(InputStream inputStream, String name, String contentType, boolean autoClose) {
            this.inputStream = inputStream;
            this.name = name;
            this.contentType = contentType;
            this.autoClose = autoClose;
        }

        static RetrofitParams.StreamWrapper newInstance(InputStream inputStream, String name, String contentType, boolean autoClose) {
            return new RetrofitParams.StreamWrapper(inputStream, name, contentType == null?"application/octet-stream":contentType, autoClose);
        }
    }

    public static class FileWrapper {
        public final File file;
        public final String contentType;
        public final String customFileName;

        public FileWrapper(File file, String contentType, String customFileName) {
            this.file = file;
            this.contentType = contentType;
            this.customFileName = customFileName;
        }
    }

}
