package com.zhang.bme.controller;

import com.zhang.bme.exception.ErrorEnum;
import com.zhang.bme.exception.ResultData;
import com.zhang.bme.exception.ResultVOUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/10/31.
 */
@Controller
public class LoginController {


    @RequestMapping("/login")
    private String login(){

        return "login";
    }


    @RequestMapping("/loginauth")
    @ResponseBody
    private ResultData loginAuth(HttpServletRequest request){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if("admin".equals(username) && "admin".equals(password)){

            request.getSession().setAttribute("USER",username);
            return ResultVOUtil.SUCCESS();
        }

        return ResultVOUtil.ERROR(ErrorEnum.LOGIN_FAILED);
    }


}
