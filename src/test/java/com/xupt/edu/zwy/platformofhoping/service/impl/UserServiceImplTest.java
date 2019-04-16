package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.xupt.edu.zwy.platformofhoping.model.User;
import com.xupt.edu.zwy.platformofhoping.service.IUserService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Resource
    private IUserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setSchool("西安邮电大学");
        user.setCollege("经济与管理学院");
        user.setProfession("工程管理专业");
        user.setUserSno("02151008");
        user.setCommunity("校科协");
        user.setMinister(1);
        user.setUserName("刘家继");
        user.setPassword("liujiaji");
        user.setSex("男");
        user.setAge(18);
        user.setEmail("liu1196329879@163.com");
        user.setPhone("17765026580");
        System.out.println(user);
        int result = userService.addUser(user);
        System.out.println(result);
    }

    @Test
    public void destroyCookie() {
    }

    @Test
    public void isRightInfo() {
    }

    @Test
    public void tesy() {
        String name = null;
        System.out.println(StringUtils.isBlank(name));
        if (name == null) {
            System.out.println("1.null");
        }
        if (StringUtils.isBlank(name)) {
            System.out.println("2.null");
        }
        if(StringUtils.isEmpty(name)){
            System.out.println("3.null");
        }
    }
}