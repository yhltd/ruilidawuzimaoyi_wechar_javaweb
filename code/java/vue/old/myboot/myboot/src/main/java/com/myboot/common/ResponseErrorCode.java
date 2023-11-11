package com.myboot.common;

import com.alibaba.fastjson.JSONObject;

/**
 * 响应码
 * @author hui
 * @date 2023/11/7 9:19
 */
public enum ResponseErrorCode {
    SUCCESS("00", "成功"),
    SEL_FAILED("01", "查询失败"),
    DEL_FAILED("02", "删除失败"),
    UPD_FAILED("03", "更新失败"),
    INS_FAILED("04", "添加失败"),
    SYS_ERROR("05", "服务异常"),
    LOGIN_FAILED("06", "登录失败"),
    REG_FAILED("07", "注册失败"),
    LOGOUT_FAILED("08", "登出失败"),
    SEL_USR_PERM_FAILED("09", "获取用户权限失败"),
    NO_PERMISSION("10", "无访问权限"),
    PARAM_ERROR("11", "参数错误");
    private ResponseErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private final String code;
    private final String msg;

    @Override
    public String toString() {
        return "ResponseErrorCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }
}
