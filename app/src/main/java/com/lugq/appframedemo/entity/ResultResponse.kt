package com.lugq.appframedemo.entity

/**
 * @Description 统一相应
 * @Author Lu Guoqiang
 * @Time 2019/7/5 15:54
 */
class ResultResponse<T>(val data: T?,
                        val code: String,
                        val msg: String)
/*
{"data":{"bind":0},"error_code":"","error_msg":"","request_id":"0c307fb0b04c46179511dd155eb5bc7d","status":"OK"}
 */

