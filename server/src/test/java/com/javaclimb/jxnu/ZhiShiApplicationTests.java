package com.javaclimb.jxnu;

import com.javaclimb.jxnu.controller.Code;
import com.javaclimb.jxnu.controller.Result;
import com.javaclimb.jxnu.controller.UserController;
import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.dao.UserMapper;
import com.javaclimb.jxnu.domain.Contents;
import com.javaclimb.jxnu.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhiShiApplicationTests {
    @Autowired
     UserMapper userMapper;
    @Test
    void contextLoads() {
        String username = "123";
        User user = userMapper.findUserByName(username);
        System.out.println(user);
        if (user != null) {
            System.out.println(2);
        }
    }
    }


