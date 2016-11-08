package com.jecyhw.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

/**
 * Created by jecyhw on 16-11-1.
 */
final public class SpringContext implements ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(SpringContext.class);

    private static ApplicationContext applicationContext = null;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContext.applicationContext == null) {
            SpringContext.applicationContext = applicationContext;
            logger.info("applicationContext获取成功,可以使用");
        }
    }

    static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    static Environment getEnvironment() {
        return applicationContext.getEnvironment();
    }
}
