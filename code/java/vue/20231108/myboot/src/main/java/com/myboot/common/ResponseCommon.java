package com.myboot.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import lombok.NonNull;

/**
 * 通用返回
 *
 * @author hui
 * @date 2023/11/6 13:50
 * @see ResponseErrorCode 错误码枚举类
 */
public class ResponseCommon {
    /**
     * 带参成功返回
     *
     * @param data
     * @return
     */
    public static String success(Object data) {
        JSONObject json = ResponseErrorCode.SUCCESS.toJSONObject();
        json.put("data", data);
        String js = json.toJSONString();
        return json.toJSONString();
    }

    /**
     * 成功返回
     * <p>
     * 可自定义返回信息提示
     *
     * @param msg 自定义返回文字信息
     * @return
     */
    public static String success(@NonNull String msg, Object data) {
        JSONObject json = ResponseErrorCode.SUCCESS.toJSONObject();
        json.put("msg", msg);
        json.put("data", data);
        success("查询成功", data);
        return json.toJSONString();

    }

    /**
     * 无参成功返回
     *
     * @return
     */
    public static String success() {
        return success(null);
    }

    /**
     * 调用失败
     * <p>
     * 本方法适用于同一错误类型，但需要细化返回的错误文字信息
     *
     * @param errorCode 失败枚举类型
     * @param msg       自定义返回文字提示
     * @return
     */
    public static String failed(@NonNull ResponseErrorCode errorCode, @NonNull String msg) {
        JSONObject json = errorCode.toJSONObject();
        json.put("msg", msg);
        return json.toJSONString();
    }

    /**
     * 调用失败
     * <p>
     * 直接使用失败错误码
     *
     * @param errorCode
     * @return
     */
    public static String failed(@NonNull ResponseErrorCode errorCode) {
        return errorCode.toString();
    }

    /**
     * 调用失败
     * <p>
     * 自定义错误码以及错误信息
     *
     * @param code 错误码
     * @param msg  错误信息
     * @return
     */
    public static String failed(@NonNull String code, @NonNull String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }
}
