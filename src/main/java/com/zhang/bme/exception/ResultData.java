package com.zhang.bme.exception;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回给前端数据统一模型
 * @param <T>
 */
@Data
public class ResultData<T> implements Serializable {


    private static final long serialVersionUID = 5679855537586185898L;

    private String code;

    private String msg;

    private T data;

}
