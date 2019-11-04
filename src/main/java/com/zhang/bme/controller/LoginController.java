package com.zhang.bme.controller;

import com.zhang.bme.exception.ErrorEnum;
import com.zhang.bme.exception.ResultVO;
import com.zhang.bme.exception.ResultVOUtil;
import com.zhang.bme.util.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/10/31.
 */
@Controller
public class LoginController {


    @RequestMapping("/login")
    private String login(HttpServletRequest request){

        request.setAttribute("isCaptcha",true);
        return "login";
    }


    @RequestMapping("/loginauth")
    @ResponseBody
    private ResultVO loginAuth(HttpServletRequest request,String username,String password,String captcha,String rememeberMe){

        if("admin".equals(username) && "admin".equals(password)){

            request.getSession().setAttribute("USER",username);
            return ResultVOUtil.SUCCESS;
        }

        return ResultVOUtil.error(ErrorEnum.LOGIN_FAILED);
    }


    /**
     * 验证码图片
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置响应头信息，通知浏览器不要缓存
        response.setHeader("Expires", "-1");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "-1");
        response.setContentType("image/jpeg");

        // 获取验证码
        String code = CaptchaUtil.getRandomCode();
        // 将验证码输入到session中，用来验证
        request.getSession().setAttribute("captcha", code);
        // 输出到web页面
        ImageIO.write(CaptchaUtil.genCaptcha(code), "jpg", response.getOutputStream());
    }


}
