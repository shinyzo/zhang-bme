package com.zhang.bme.exception;


public class ResultVOUtil {


    public static ResultVO SUCCESS = success();


    public static ResultVO success(Object object){

        ResultVO resultVO = new ResultVO() ;
        resultVO.setCode(ErrorEnum.SUCCESS.getCode());
        resultVO.setMsg(ErrorEnum.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }


    public static ResultVO success()
    {
        return success(null );
    }


    public static ResultVO error(ErrorEnum errorEnum){
        ResultVO resultVO = new ResultVO() ;
        resultVO.setCode(errorEnum.getCode());
        resultVO.setMsg(errorEnum.getMsg());
        resultVO.setData(null);
        return resultVO;
    }

}
