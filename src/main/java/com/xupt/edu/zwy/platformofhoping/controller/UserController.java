package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.UserListDto;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.User;
import com.xupt.edu.zwy.platformofhoping.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public CommonJsonResult registerUser(@RequestBody User user) {
        log.info("into /user/register,user:{}", user);
        int result = userService.addUser(user);
        log.info("out /user/register,result:{}", result);
        return CommonJsonResult.success();
    }

    @GetMapping(value = "/login")
    public CommonJsonResult login(UserLoginReq userLoginReq, HttpServletResponse response) {
        log.info("into /user/login,userLoginReq:{}", userLoginReq);
        if ((!userService.isRightInfo(userLoginReq, response))) {
            return CommonJsonResult.fail(ReturnCodes.FAILD, "用户名或密码错误");
        }
        log.info("out /user/login");
        return CommonJsonResult.success();
    }

    @GetMapping("/destory")
    public CommonJsonResult destory(HttpServletResponse response) {
        log.info("into user/destory");
        userService.destroyCookie(response);
        log.info("out /user/destory");
        return CommonJsonResult.success();
    }

    @GetMapping("/info")
    public CommonJsonResult<User> userInfo(@RequestParam("userId") String userId) {
        log.info("into user/info,userId:{}", userId);
        User user = userService.selectUserInfoById(userId);
        log.info("out /user/info,user:{}", user);
        return CommonJsonResult.success(user);
    }

    @GetMapping("list")
    public CommonJsonResult<PageInfo<User>> userList(UserListDto userListDto) {
        log.info("into /user/list,userListDto:{}", userListDto);
        PageInfo<User> userPageInfo = userService.userList(userListDto);
        log.info("out /user/list,userPageInfo:{}", userPageInfo);
        return CommonJsonResult.success(userPageInfo);
    }

    @PostMapping("update")
    public CommonJsonResult updateUser(@RequestBody User user){
        log.info("into /user/update,user:{}", user);
        int result = userService.updateUser(user);
        log.info("out user/update,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("delete")
    public CommonJsonResult deleteUser(@RequestBody UserListDto userListDto){
        log.info("into /user/delete,userListDto:{}", userListDto);
        int result = userService.deleteUser(userListDto);
        log.info("out user/delete,result:{}", result);
        return CommonJsonResult.success();
    }
}
