package com.sina.demo.common.config.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 * Created by wanghongfei on 10/03/2017.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        log.debug("此时获取到的数据源为{}", DataSourceContextHolder.getDbType());

        return DataSourceContextHolder.getDbType();
    }

}
