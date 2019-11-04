package com.zhang.bme.exception;


public class ResultVOUtil {



    public static ResultData SUCCESS(Object object){

        ResultData resultVO = new ResultData() ;
        resultVO.setCode(ErrorEnum.SUCCESS.getCode());
        resultVO.setMsg(ErrorEnum.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }


    public static ResultData SUCCESS()
    {
        return SUCCESS(null );
    }


    public static ResultData ERROR(ErrorEnum errorEnum){
        ResultData resultVO = new ResultData() ;
        resultVO.setCode(errorEnum.getCode());
        resultVO.setMsg(errorEnum.getMsg());
        resultVO.setData(null);
        return resultVO;
    }

}
