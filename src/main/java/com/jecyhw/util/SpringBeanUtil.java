package com.jecyhw.util;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;


/**
 * Created by jecyhw on 16-11-1.
 */
final public class SpringBeanUtil {

    public static GridFsOperations getGridFsOperations() {
        return getBean(GridFsTemplate.class);
    }

    public static MongoOperations getMongoTemplate() {
        return getBean(MongoTemplate.class);
    }

    private static <T> T getBean(Class<T> requiredType) {
        return getApplicationContext().getBean(requiredType);
    }

    private static ApplicationContext getApplicationContext() {
        return SpringContext.getApplicationContext();
    }

    public static String getPivotViewerDeepZoomFileRootPath() {
        return SpringContext.getEnvironment().getProperty("deepZoomFile.rootPath", "");
    }
}
