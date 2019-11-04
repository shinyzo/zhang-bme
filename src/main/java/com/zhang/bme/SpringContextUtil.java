package com.zhang.bme;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/11/1.
 * SpringContext容器
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static SpringContextUtil util = new SpringContextUtil();
    private ApplicationContext context;

    public SpringContextUtil() {
    }

    public static SpringContextUtil getInstance() {
        if(util == null) {
            util = new SpringContextUtil();
        }

        return util;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        getInstance().context = applicationContext;
    }

    public ApplicationContext getContext() {
        return this.context;
    }

    public <T> T getBean(String name) throws BeansException {
        return (T) this.context.getBean(name);
    }

    public <T> T getBean(Class<T> clz) throws BeansException {
        T result = this.context.getBean(clz);
        return result;
    }

    public boolean containsBean(String name) {
        return this.context.containsBean(name);
    }

    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return this.context.isSingleton(name);
    }

    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return this.context.getType(name);
    }

    public String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return this.context.getAliases(name);
    }

}
