package com.sina.demo.web.config;

import com.sina.demo.web.error.TitanDeniedHandler;
import com.sina.demo.web.error.TitanUnauthenticatedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;

/**
 * Web安全配置类
 * Created by wanghongfei on 10/03/2017.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TitanWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TitanUnauthenticatedHandler unauthenticatedHandler;

    @Autowired
    private TitanDeniedHandler deniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 启用记住我功能
        //http.addFilterAt(rememberMeAuthenticationFilter(), RememberMeAuthenticationFilter.class);

        http.exceptionHandling()
                // 用户已登陆, 但权限不足时调用
                .accessDeniedHandler(deniedHandler)
                // 用户未登陆, 访问受保护资源时调用
                .authenticationEntryPoint(unauthenticatedHandler);


        http.authorizeRequests()
                .antMatchers("/a").hasAuthority("a_auth")
                //b请求需要登陆和user角色
                .antMatchers("/b").hasRole("ADMIN");


    }



    /**
     * RememberMe功能工作流程:
     * 1) 添加RememberMeAuthenticationFilter过虑器
     * 2) 实现RememberMeServices接口, 在autoLogin()方法中检查是否存在记住我功能的cookie,
     * 有则取出返回RememberMeAuthenticationToken, 没则直接返回null
     * 3) 实现AuthenticationProvider接口, 验证步骤2)中传过来的token值是否合法, 成功则读取角色信息并返回UsernamePasswordAuthenticationToken, 失败返回null
     *
     * @return
     */
/*    @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() {
        RememberMeAuthenticationFilter filter = new RememberMeAuthenticationFilter(authenticationManager, rememberMeServices);

        return filter;
    }*/
}
