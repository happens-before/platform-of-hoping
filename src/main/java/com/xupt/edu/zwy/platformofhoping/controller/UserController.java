package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.User;
import com.xupt.edu.zwy.platformofhoping.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
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
 * @Date 19-3-16
 * @Time 下午9:02
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private IUserService userService;

    @PostMapping("/register")
    public CommonJsonResult registerUser(User user) {
        log.info("into /user/register,user:{}", user);
        int result = userService.addUser(user);
        log.info("out /user/register,result:{}", result);
        return CommonJsonResult.success();
    }

    @GetMapping(value = "/login")
    public CommonJsonResult login(UserLoginReq userLoginReq, HttpServletResponse response) {
        if (!userService.isRightInfo(userLoginReq, response)) {
            log.info("用户名或密码错误");
            throw new BusinessException(ReturnCodes.FAILD, "用户名或密码错误");
        }
        return CommonJsonResult.success();
    }
}
