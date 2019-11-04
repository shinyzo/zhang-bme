package com.zhang.bme.cas;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author: WangSaiChao
 * @date: 2018/8/5
 * @description: cas springboot 配置类
 */

@Component
@Configuration
@ConfigurationProperties(prefix = "cas", ignoreUnknownFields = false)
@Data
public class CasClientProperties {

    /**
     * CAS server URL E.g. https://example.com/cas or https://cas.example. Required.
     * CAS 服务端 url 不能为空
     */
    @NotNull
    private String serverUrlPrefix;

    /**
     * CAS server login URL E.g. https://example.com/cas/login or https://cas.example/login. Required.
     * CAS 服务端登录地址  上面的连接 加上/login 该参数不能为空
     */
    @NotNull
    private String serverLoginUrl;
    /**
     *  CAS服务端验证ticket URL
     */
    @NotNull
    private String serverValidate;
    /**
     * CAS认证中心退出地址
     */
    private String serverLogoutUrl;

    /**
     * CAS-protected client application host URL E.g. https://myclient.example.com Required.
     * 当前客户端的地址
     */
    @NotNull
    private String clientHostUrl;

    /**
     * 忽略规则,访问那些地址 不需要登录
     */
    private String ignorePattern;

    /**
     * 自定义UrlPatternMatcherStrategy验证
     */
    private String ignoreUrlPatternType;

    /**
     * CAS 开关
     */
    private String onOff;

    /**
     *  本地登录地址
     */
    private String clientLoginUrl;
    /**
     *  本地退出地址
     */
    private String clientLogoutUrl;


}
