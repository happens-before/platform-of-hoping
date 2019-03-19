package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import com.xupt.edu.zwy.platformofhoping.service.IOrganizerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:26
 */
@RestController
@Slf4j
@RequestMapping("/organizer")
public class OrganizerController {
    @Resource
    private IOrganizerService organizerService;

    @GetMapping("/login")
    public CommonJsonResult organizerLogin(OrganizerReq organizerReq, HttpServletResponse response) {
        log.info("into /organizer/login,organizerReq:{}", organizerReq);
        if (!organizerService.isRightInfo(organizerReq,response)) {
            throw new BusinessException(ReturnCodes.FAILD, "组织名或密码错误，请重试");
        }
        log.info("out /organizer/login");
        return CommonJsonResult.success();
    }

    @PostMapping("/register")
    public CommonJsonResult orgainzerRegister(Organizer organizer) {
        log.info("into /organizer/register,organizer:{}", organizer);
        int result = organizerService.registerOrganizer(organizer);
        log.info("out /organizer/register,result:{}", result);
        return CommonJsonResult.success();
    }
}
