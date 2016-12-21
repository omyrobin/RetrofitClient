package com.http.retrofitclient.http;

import android.text.TextUtils;

/**
 * Created by omyrobin on 2016/12/12.
 */

public class JsonTypeUtil {

    public enum JSON_TYPE{
        /**JSONObject*/
        JSON_TYPE_OBJECT,
        /**JSONArray*/
        JSON_TYPE_ARRAY,
        /**不是JSON格式的字符串*/
        JSON_TYPE_ERROR
    }

    /***
     *
     * 获取JSON类型
     * 判断规则
     * 判断第一个字母是否为{或[ 如果都不是则不是一个JSON格式的文本
     *
     * @param str
     * @return
     */
    public static JSON_TYPE getJSONType(String str,String name){
        if(TextUtils.isEmpty(str) || TextUtils.isEmpty(name)){
            return JSON_TYPE.JSON_TYPE_ERROR;
        }

        final char[] strChar = str.substring(name.length()+4, name.length()+5).toCharArray();
        final char firstChar = strChar[0];
        if(firstChar == '{'){
            return JSON_TYPE.JSON_TYPE_OBJECT;
        }else if(firstChar == '['){
            return JSON_TYPE.JSON_TYPE_ARRAY;
        }else{
            return JSON_TYPE.JSON_TYPE_ERROR;
        }
    }
}
