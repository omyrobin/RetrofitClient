package com.http.retrofitclient.http;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import retrofit2.Response;

/**
 * Created by omyrobin on 2016/12/12.
 */

public abstract class ResponseJsonCallBack<T> implements ResponseCallBack<T> {

    private static String flag = "1";

    private static String objectJson;

    private static String error;

    private static JSONObject res = null;

    private static JSONObject data;

    /**
     * 根据本服务器的数据格式来进行解析转换成Bean对象
     * 如果字段名称为空，直接返回字符串数据
     * 如果字段名称不为空，尝试转换成JsonObject然后根据字段名称、泛型进行解析
     * @param response Retrofit返回的字符串格式数据
     * @param name 最里层需要解析的Bean字段名称
     */
    public void parseT(Response<String> response, final String name){
        if(name == null){
            onSuccess(response.headers(),(T)response.body());
        }else{
            Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            JsonTypeUtil.JSON_TYPE type = JsonTypeUtil.JSON_TYPE.JSON_TYPE_ERROR;
            try {
                res = new JSONObject(response.body());
                data = ResponseUtil.getData(res);
                if(data != null){
                    type = JsonTypeUtil.getJSONType(data.toString(), name);
                }
                flag = ResponseUtil.getFlag(res);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(res != null){
                Gson gson = new Gson();
                if("0".equals(flag)){
                    if(type == JsonTypeUtil.JSON_TYPE.JSON_TYPE_OBJECT){
                        objectJson = ResponseUtil.getBean(res, name);
                        onSuccess(response.headers(), gson.fromJson(objectJson,((Class<T>)t)));
                    }else if(type == JsonTypeUtil.JSON_TYPE.JSON_TYPE_ARRAY){
                        objectJson = ResponseUtil.getBeanList(res, name);
                        this.onSuccess(response.headers(), fromJsonArray(objectJson,((Class<T>)t)));
                    }
                }else {
                    error = ResponseUtil.getError(res);
                    onFailure(error);
                }
            }else{
                onSuccess(response.headers(),(T)response.body());
            }
        }
    }

    public List<T> fromJsonArray(String json, Class<T> clazz)  throws JsonSyntaxException {
        List<T> lst =  new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            lst.add(new Gson().fromJson(elem, clazz));
        }
        return lst;
    }

    @Override
    public void onSuccess(Headers headers, T t) {

    }

    @Override
    public void onSuccess(Headers headers, List<T> t) {

    }
}