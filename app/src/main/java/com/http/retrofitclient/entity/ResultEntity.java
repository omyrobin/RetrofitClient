package com.http.retrofitclient.entity;

/**
 * Created by omyrobin on 2017/1/3.
 */

public class ResultEntity<T>{

    private String errorCode;

    private String message;

    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
