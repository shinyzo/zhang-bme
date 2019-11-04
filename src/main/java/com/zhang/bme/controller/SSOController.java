package com.zhang.bme.controller;

import com.zhang.bme.cas.CasClientProperties;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2019/10/30.
 */
@Controller
@Slf4j
public class SSOController {

    @Autowired
    private CasClientProperties casClientProperties;


    @RequestMapping("/main/index")
    public String index(HttpServletRequest request){

        // 退出地址
        String ssoLogoutUrl = casClientProperties.getClientLogoutUrl() ;
        if("true".equals(casClientProperties.getOnOff())){
            ssoLogoutUrl = casClientProperties.getServerLogoutUrl();
        }
        request.setAttribute("ssoLogoutUrl",ssoLogoutUrl);

        return "/main/index";

    }

    @RequestMapping("/main/main")
    public String main(HttpServletRequest request){

        return "/main/main";
    }


    @RequestMapping("/sso/logout")
    public String logout(HttpServletRequest request){

        // 移除本地用户信息
        request.getSession().removeAttribute("USER");

        // 跳回首页，登出后会被拦截至登录页面，登录成功跳回至首页
        return "redirect:/main/index";
    }

}
