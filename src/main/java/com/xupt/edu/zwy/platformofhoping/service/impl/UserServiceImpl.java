package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.google.common.base.Optional;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IAdminDao;
import com.xupt.edu.zwy.platformofhoping.dao.IOrganizerDao;
import com.xupt.edu.zwy.platformofhoping.dao.IUserDao;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.enums.UserRoleEnum;
import com.xupt.edu.zwy.platformofhoping.model.Admin;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import com.xupt.edu.zwy.platformofhoping.model.User;
import com.xupt.edu.zwy.platformofhoping.service.IUserService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;


/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午9:04
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    public static final String COOKIE_USERID = "platform_userId";

    public static final String COOKIE_IDENTITY = "platform_identity";

    public static final String COOKIE_NAME = "platform_name";

    public static final String COOKIE_TIME = "platform_time";

    @Resource
    private IUserDao iUserDao;
    @Resource
    private IAdminDao iAdminDao;
    @Resource
    private IOrganizerDao iOrganizerDao;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        try {
            user.setUserId(CommonUtils.getUUId32());
            System.out.println(user);
            int result = iUserDao.addUser(user);
            return result;
        } catch (Exception e) {
            log.error("用户添加失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    /**
     * 销毁cookie
     */
    @Override
    public void destroyCookie(HttpServletResponse response) {
        String cookieUserId = new StringBuilder(COOKIE_USERID).append("=").append("null").append(";Path=/;Max-Age=43200;HttpOnly").toString();
        String cookieIdentity = new StringBuilder(COOKIE_IDENTITY).append("=").append("null").append(";Path=/;Max-Age=43200;HttpOnly").toString();
        String cookieName = new StringBuilder(COOKIE_NAME).append("=").append("null").append(";Path=/;Max-Age=43200;HttpOnly").toString();
        String cookieTime = new StringBuilder(COOKIE_TIME).append("=").append("null").append(";Path=/;Max-Age=43200;HttpOnly").toString();
        response.addHeader("Set-Cookie", cookieUserId);
        response.addHeader("Set-Cookie", cookieIdentity);
        response.addHeader("Set-Cookie", cookieName);
        response.addHeader("Set-Cookie", cookieTime);
    }

    @Override
    public User selectUserInfoById(String userId) {
        try {
            User user = iUserDao.selectUserByUserId(userId);
            return user;
        } catch (Exception e) {
            log.error("用户信息查询失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public boolean isRightInfo(UserLoginReq userLoginReq, HttpServletResponse response) {
        try {
            User user = iUserDao.isRightInfo(userLoginReq);
            if (user == null) {
                return false;
            }
            setCookie(user, response);
            return true;
        } catch (Exception e) {
            log.error("用户登陆失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }

    }

    private boolean setCookie(User user, HttpServletResponse response) {
        try {
            Optional<User> optionalUser = Optional.fromNullable(user);
            int identity = getUserIdentityByName(user.getUserName());
            if (!optionalUser.isPresent()) {
                log.error("获得的用户为空");
            } else {
                Date now = new Date();
                String cookieUserId = new StringBuilder(COOKIE_USERID).append("=").append(user.getUserId()).append(";Path=/;Max-Age=43200").toString();
                String cookieIdentity = new StringBuilder(COOKIE_IDENTITY).append("=").append(identity).append(";Path=/;Max-Age=43200").toString();
                String cookieName = new StringBuilder(COOKIE_NAME).append("=").append(URLEncoder.encode(user.getUserName(), "UTF-8")).append(";Path=/;Max-Age=43200").toString();
                String cookieTime = new StringBuilder(COOKIE_TIME).append("=").append(now).append(";Path=/;Max-Age=43200").toString();
                response.addHeader("Set-Cookie", cookieUserId);
                response.addHeader("Set-Cookie", cookieIdentity);
                response.addHeader("Set-Cookie", cookieName);
                response.addHeader("Set-Cookie", cookieTime);
                log.info("cookie设置信息：platform_userId：{} platform_identity：{} platform_name：{} platform_time：{} ", user.getUserId(), identity, user.getUserName(), now);
                return true;
            }
        } catch (BusinessException e) {
            log.error("登录异常 {}", e);
        } catch (UnsupportedEncodingException e) {
            log.error("username编码失败{} ", e);
        }
        return false;
    }

    private int getUserIdentityByName(String adminName) {
        Admin admin = iAdminDao.isAdmin(adminName);
        Optional<Admin> optionalAdmin = Optional.fromNullable(admin);
        if (optionalAdmin.isPresent()) {
            return UserRoleEnum.ADMIN.getRoleFlag();
        } else {
            return UserRoleEnum.USER.getRoleFlag();
        }

    }


}
