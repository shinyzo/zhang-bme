package com.zhang.bme.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * Created by Administrator on 2019/11/2.
 * 统一异常处理
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
    @ResponseBody
    public ResultVO sqlException(){

        return  ResultVOUtil.error(ErrorEnum.SQL_ERROR);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO exception(){

        return  ResultVOUtil.error(ErrorEnum.ERROR);
    }


}
