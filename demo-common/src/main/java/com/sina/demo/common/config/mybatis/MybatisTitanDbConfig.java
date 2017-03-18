package com.sina.demo.common.config.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by wanghongfei on 10/03/2017.
 */
@Configuration
@MapperScan(basePackages = {"com.sina.demo.common.dao.titan"}, sqlSessionFactoryRef = "sqlSessionFactory1")
public class MybatisTitanDbConfig {

/*    @Autowired
    @Qualifier("titanMasterDS")
    private DataSource ds1;*/

    @Autowired
    @Qualifier("dynamicDS1")
    private DataSource dynamicDS1;



    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //factoryBean.setDataSource(ds1);
        factoryBean.setDataSource(dynamicDS1);

        return factoryBean.getObject();

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
        return template;
    }

}
