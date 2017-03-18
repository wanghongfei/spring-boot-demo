package com.sina.demo.common.config.ds;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态切换数据源切面
 * Created by wanghongfei on 10/03/2017.
 */
@Aspect
@Component
@Order(0) // 保证在@Transactional之前执行
public class DynamicDataSourceAspect {

    /**
     * 方法调用前切换数据源
     * @param point
     */
    @Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point){

        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();

        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature)point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);

            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
                System.out.println("DataSource Aop ====> "+dataSource);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // 切换数据源
        DataSourceContextHolder.setDbType(dataSource);

    }


    /**
     * 方法调用后切回默认数据源
     * @param point
     */
    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point){
        DataSourceContextHolder.setDbType(DataSourceContextHolder.DEFAULT_DS);
    }
}
