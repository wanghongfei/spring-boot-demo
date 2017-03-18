package com.sina.demo.web.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghongfei on 12/03/2017.
 */
@Component
public class TitanAuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        // 通过用户名密码登陆
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            // 验证密码


            // 加载role
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("a_auth"));

            // setDetails

            return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);

        }

        // 通过cookie
        if (authentication instanceof RememberMeAuthenticationToken) {
            return authentication;
        }



        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RememberMeAuthenticationToken.class.isAssignableFrom(authentication)
                || UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
