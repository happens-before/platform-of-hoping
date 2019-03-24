package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.model.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午9:04
 */
public interface IUserService {
    int addUser(User user);

    boolean isRightInfo(UserLoginReq userLoginReq,HttpServletResponse response);

    /**
     * 销毁所有cookie,用于退出登录
     */
    void destroyCookie(HttpServletResponse response);

    User selectUserInfoById(String userId);
}
