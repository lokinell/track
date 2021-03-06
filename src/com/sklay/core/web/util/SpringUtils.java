/*
 * @(#)SpringUtils.java 2012
 *
 * Copyright 2011 Sklay SoftWare, Inc. All rights reserved.
 * SKLAY Limited Company/CONFIDENTIAL. Use is subject to license terms.
 */
package com.sklay.core.web.util;

import java.lang.annotation.Annotation;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

/**
 * 
 *  .
 * <p/>
 *
 * @author <a href="mailto:1988fuyu@163.com">fuyu</a>
 * 
 * @version v1.0 2013-7-9
 */
@Component
public class SpringUtils implements ApplicationContextAware, ServletContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringUtils.class);

    protected static ServletContext servletContext;

    protected static ApplicationContext applicationContext;

    public void setServletContext(ServletContext servletContext) {
        SpringUtils.servletContext = servletContext;
        LOGGER.info("SpringUtils has been initialized !");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    public static String getRealPath() {
        return servletContext.getRealPath("/");
    }

    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return applicationContext.getBeansOfType(type);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
        return applicationContext.getBeansWithAnnotation(annotationType);
    }

    public static Resource getResource(String location) {
        return applicationContext.getResource(location);
    }

}
