package com.zhang.bme.cas;

import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhangliangming
 * @date: 2018/8/6
 * @description: CAS配置模式
 */
@Configuration
@EnableConfigurationProperties(CasClientProperties.class)
@Slf4j
public class CasClientConfiguration {

    @Autowired
    CasClientProperties casClientProperties;

    /**
     * 配置登出过滤器
     * @return
     */
   /* @Bean
    public FilterRegistrationBean filterSingleOutRegistration() {
        log.info("==> filterSingleRegistration start ");
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/sso/logout");
        Map<String,String>  initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", casClientProperties.getServerUrlPrefix());
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        log.info("==> filterSingleRegistration end ");
        return registration;
    }*/

    /**
     * 添加监听器
     * @return
     */
   /* @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration(){
        log.info("==> singleSignOutListenerRegistration start ");
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<EventListener>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        registrationBean.setOrder(1);
        log.info("==> singleSignOutListenerRegistration end ");
        return registrationBean;
    }*/

    /**
     * 配置过滤验证器 这里用的是Cas30ProxyReceivingTicketValidationFilter
     * @return
     */
   /* @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        log.info("==> filterValidationRegistration start ");
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/main*//*");
        Map<String,String> initParameters = new HashMap<String, String>();
        initParameters.put("casServerUrlPrefix", casClientProperties.getServerUrlPrefix());
        initParameters.put("serverName", casClientProperties.getClientHostUrl());
        initParameters.put("useSession", "true");
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(2);
        log.info("==> filterValidationRegistration end ");
        return registration;

    }*/

    /**
     * 配置授权过滤器
     * @return
     */
   /* @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {
        log.info("==> filterAuthenticationRegistration start ");
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/main*//*");
        Map<String,String>  initParameters = new HashMap<String, String>();
        initParameters.put("casServerLoginUrl", casClientProperties.getServerLoginUrl());
        initParameters.put("serverName", casClientProperties.getClientHostUrl());

        if(casClientProperties.getIgnorePattern() != null && !"".equals(casClientProperties.getIgnorePattern())){
            initParameters.put("ignorePattern", casClientProperties.getIgnorePattern());
        }

        //自定义UrlPatternMatcherStrategy 验证规则
        if(casClientProperties.getIgnoreUrlPatternType() != null && !"".equals(casClientProperties.getIgnoreUrlPatternType())){
            initParameters.put("ignoreUrlPatternType", casClientProperties.getIgnoreUrlPatternType());
        }

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(3);
        log.info("==> filterAuthenticationRegistration end ");
        return registration;
    }
*/
    /**
     * request wraper过滤器
     * @return
     */
   /* @Bean
    public FilterRegistrationBean filterWrapperRegistration() {
        log.info("==> filterWrapperRegistration strart ");
        final FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/main*//*");
        // 设定加载的顺序
        registration.setOrder(4);
        log.info("==> filterWrapperRegistration end ");
        return registration;
    }*/

}
