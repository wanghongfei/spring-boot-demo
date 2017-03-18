package com.sina.demo.web.login.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by wanghongfei on 15/03/2017.
 */
@Data
public class UserLoginVO {
    @NotEmpty
    private String email;
    private String phone;
    @NotEmpty
    private String pwd;
}
