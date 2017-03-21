package com.sian.titan.common.test.service;

import com.sian.titan.common.test.BaseTestClass;
import com.sina.demo.common.dao.titan.MemberModel;
import com.sina.demo.common.dao.titan.MemberModelMapper;
import com.sina.demo.common.login.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wanghongfei on 14/03/2017.
 */
public class MapperTest extends BaseTestClass {
    @Autowired
    private MemberModelMapper memMapper;

    @Autowired
    private UserService userService;

    @Test
    public void testMapper() {
        MemberModel mem = memMapper.selectByPrimaryKey(1);
        System.out.println(mem);
    }

    @Test
    public void testSwitchDS() {
        MemberModel mem = userService.getMember(1);
        System.out.println(mem);
    }
}
