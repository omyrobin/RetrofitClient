package com.http.retrofitclient.http;

import org.json.JSONObject;

/**
 * Created by omyrobin on 2016/12/12.
 */

public class ResponseUtil {

    public static JSONObject getData(JSONObject res){
        JSONObject data = res.optJSONObject("res").optJSONObject("data");
        return data != null ? data : null;
    }

    public static String getFlag(JSONObject res){
        String flag =  getData(res).optString("flag");
        return flag != null ? flag : "1";
    }

    public static String getBean(JSONObject res, String name){
        String beanJson = getData(res).optJSONObject(name).toString();
        return beanJson;
    }

    public static String getBeanList(JSONObject res, String name){
        String beanListJson = getData(res).optJSONArray(name).toString();
        return beanListJson;
    }

    public static String getError(JSONObject res){
        String error = getData(res).optString("error","获取数据失败");
        return error;
    }
}
