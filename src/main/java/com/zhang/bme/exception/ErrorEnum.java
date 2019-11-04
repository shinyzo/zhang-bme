package com.zhang.bme.exception;

/**
 * Created by Administrator on 2019/11/2.
 */
public enum  ErrorEnum {

    SQL_ERROR("9001","数据库操作异常"),
    ERROR("9999","未知异常"),
    SUCCESS("0000","操作成功"),


    LOGIN_FAILED("1001","用户名或密码错误，登录失败！");


    private String code;
    private String msg;

    private ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }





}
