package com.zhang.bme.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by Administrator on 2019/11/4.
 */
@Component
@Slf4j
public class BmeApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("==> init BmeApplicationListener begin");
        // 将 ApplicationContext 转化为 WebApplicationContext
        WebApplicationContext webApplicationContext =
                (WebApplicationContext)contextRefreshedEvent.getApplicationContext();
        // 从 webApplicationContext 中获取  servletContext
        ServletContext servletContext = webApplicationContext.getServletContext();
        // servletContext设置值
        servletContext.setAttribute("app", "/bme");
        log.info("==> init BmeApplicationListener end");
    }


}




