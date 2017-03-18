package com.sina.demo.common.config.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanghongfei on 10/03/2017.
 */
public class DataSourceContextHolder {
    public static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "titan-master";

    // 线程本地环境
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源类型
    public static void setDbType(String dbType) {
        log.debug("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    // 获取数据源类型
    public static String getDbType() {
        return (contextHolder.get());
    }

    // 清除数据源类型
    public static void clearDbType() {
        contextHolder.remove();
    }
}
