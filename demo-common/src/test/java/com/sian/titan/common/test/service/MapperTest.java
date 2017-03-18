package com.sian.titan.common.test.service;

import com.sian.titan.common.test.BaseTestClass;
import com.sina.demo.common.dao.titan.MemberModelMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by wanghongfei on 14/03/2017.
 */
public class MapperTest extends BaseTestClass {
    @Autowired
    private MemberModelMapper memMapper;

    @Test
    public void testMapper() {
        memMapper.selectByPrimaryKey(1);
    }
}
