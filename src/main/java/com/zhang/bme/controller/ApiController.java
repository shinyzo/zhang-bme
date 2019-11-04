package com.zhang.bme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/11/1.
 */
@Controller
public class ApiController {

    @RequestMapping("/api/resource")
    @ResponseBody
    private String api(){

        return "api resource";
    }
}
