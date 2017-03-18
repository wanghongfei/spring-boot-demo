package com.sina.demo.web.login.controller;

import com.sina.demo.common.dao.titan.MemberModel;
import com.sina.demo.common.exception.ErrorEnum;
import com.sina.demo.common.login.service.UserService;
import com.sina.demo.web.config.TitanResponse;
import com.sina.demo.web.error.TitanRestExceptionHandler;
import com.sina.demo.web.login.vo.UserLoginVO;
import com.sina.demo.web.login.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by wanghongfei on 10/03/2017.
 */
@RestController
public class DemoController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Object login(UserLoginVO loginVO,
                        BindingResult br,
                        HttpServletRequest req,
                        HttpServletResponse resp) {
        if (br.hasErrors()) {
            return new TitanResponse(ErrorEnum.FAILED.code(), ErrorEnum.FAILED.message());
        }


        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginVO.getEmail(), loginVO.getPwd());

        try {
            // 完成密码验证和授权功能
            Authentication auth = authManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (AuthenticationException ex) {
            return new TitanResponse(ex.getMessage());
        }


        return new TitanResponse();
    }


    @GetMapping("/a")
    //@PreAuthorize("hasRole('ADMIN')")
    public String A(Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = principal.getName();
        return "a" + name;
    }

    @GetMapping("/b")
    public String B() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "b";
    }

    @GetMapping("/c")
    @PreAuthorize("hasRole('C')")
    public String C() {
        return "C";
    }

    /**
     * 本地缓存demo
     * @return
     */
    @GetMapping("/cache")
    public Long cache() {
        return userService.cacheDemo();
    }

    /**
     * 动态数据源切换demo: 使用titan-master数据源
     * @return
     */
    @GetMapping("/db/master")
    public UserVO masterDb() {
        MemberModel memberModel = userService.getMember(1);

        UserVO userVO = new UserVO();
        userVO.setName(memberModel.getName());
        userVO.setId(memberModel.getId());

        return userVO;

    }

    /**
     * 动态数据源切换demo: 使用ds2数据源
     * @return
     */
    @GetMapping("/db/slave")
    public UserVO slaveDb() {
        MemberModel memberModel = userService.getMember2(1);

        UserVO userVO = new UserVO();
        userVO.setName(memberModel.getName());
        userVO.setId(memberModel.getId());

        return userVO;
    }
}
