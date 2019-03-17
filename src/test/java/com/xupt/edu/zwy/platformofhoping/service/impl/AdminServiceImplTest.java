package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.xupt.edu.zwy.platformofhoping.dto.AdminReq;
import com.xupt.edu.zwy.platformofhoping.service.IAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {
    @Resource
    private IAdminService adminService;
    @Test
    public void deleteAdmin() {

    }

    @Test
    public void addAdmin() {
        AdminReq adminReq=new AdminReq();
        adminReq.setAdminName("赵婉悦");
        int result = adminService.addAdmin(adminReq);
        System.out.println(result);
    }
}