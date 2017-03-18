package com.sina.demo.common.config.ds;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置
 * Created by wanghongfei on 10/03/2017.
 */
@Configuration
public class DataSourceConfig {

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */
    @Bean(name = "dynamicDS1")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("titan-master", dataSource1());
        dsMap.put("ds2", dataSource2());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }


    @Bean(name = "titanMasterDS")
    @ConfigurationProperties(prefix = "spring.datasource.titan-master")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }





    @Bean(name = "ds2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
