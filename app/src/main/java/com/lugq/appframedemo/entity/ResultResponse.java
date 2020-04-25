package com.lugq.appframedemo.entity;

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 2019/7/5 15:54
 */
public class ResultResponse<T> {
    /*
    {"data":{"bind":0},"error_code":"","error_msg":"","request_id":"0c307fb0b04c46179511dd155eb5bc7d","status":"OK"}
     */

    public T data;
    public String error_code;
    public String status;
}
