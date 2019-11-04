package com.zhang.bme.cas.filter;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2019/11/1.
 */
@Configuration
public class FilterConfig {

    /**
     * 需要本地登录拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean casAuthTicketFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CASLoginFilter());
        // 拦截规则
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter("excludedURL","/layui/,/test,/login,/loginauth");
        registrationBean.setName("casAuthTicketFilter");
        registrationBean.setOrder(0);
        return registrationBean;
    }

}
