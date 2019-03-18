package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.AdminReq;
import com.xupt.edu.zwy.platformofhoping.model.Admin;
import com.xupt.edu.zwy.platformofhoping.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:25
 */
@RestController
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Resource
    private IAdminService adminService;

    @PostMapping("/add")
    public CommonJsonResult addAdmin(AdminReq adminReq) {
        log.info("into /admin/add,adminReq:{}", adminReq);
        int result = adminService.addAdmin(adminReq);
        log.info("out /admin/add,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/delete")
    public CommonJsonResult deleteAdmin(AdminReq adminReq) {
        log.info("into /admin/delete,adminReq:{}",adminReq);
        int result = adminService.deleteAdmin(adminReq);
        log.info("out /admin/delete,result:{}", result);
        return CommonJsonResult.success();
    }

    @GetMapping("/list")
    public CommonJsonResult<Admin> adminList() {
        return null;
    }
}
