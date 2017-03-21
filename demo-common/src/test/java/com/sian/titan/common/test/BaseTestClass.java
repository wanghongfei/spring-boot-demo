package com.sian.titan.common.test;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wanghongfei on 14/03/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CommonTestApplication.class)
//@Transactional
public class BaseTestClass {
    public static void main(String[] args) {
        SpringApplication.run(BaseTestClass.class, args);
    }
}
