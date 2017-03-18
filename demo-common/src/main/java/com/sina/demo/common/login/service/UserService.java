package com.sina.demo.common.login.service;

import com.sina.demo.common.config.ds.DS;
import com.sina.demo.common.dao.titan.MemberModel;
import com.sina.demo.common.dao.titan.MemberModelMapper;
import com.sina.demo.common.utils.PictureGenUtils;
import com.sina.demo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by wanghongfei on 13/03/2017.
 */
@Service
public class UserService {
    public static final int CODE_LENGTH = 5;

    @Autowired
    private MemberModelMapper memMapper;



    /**
     * 本地缓存demo
     * @return
     */
    @Cacheable("titan")
    public Long cacheDemo() {
        return new Date().getTime();
    }


    @DS("titan-master")
    @Transactional(readOnly = true)
    public MemberModel getMember(Integer id) {
        return memMapper.selectByPrimaryKey(id);
    }

    @DS("ds2")
    @Transactional(readOnly = true)
    public MemberModel getMember2(Integer id) {
        return memMapper.selectByPrimaryKey(id);
    }
}
