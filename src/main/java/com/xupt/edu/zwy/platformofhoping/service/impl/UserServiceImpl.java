package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Optional;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IAdminDao;
import com.xupt.edu.zwy.platformofhoping.dao.IOrganizerDao;
import com.xupt.edu.zwy.platformofhoping.dao.IUserDao;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.UserListDto;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.enums.UserRoleEnum;
import com.xupt.edu.zwy.platformofhoping.model.Admin;
import com.xupt.edu.zwy.platformofhoping.model.User;
import com.xupt.edu.zwy.platformofhoping.service.IUserService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


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

    public static final String COOKIE_MINISTER = "platform_minister";

    @Resource
    private IUserDao iUserDao;
    @Resource
    private IAdminDao iAdminDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(User user) {
        try {
            user.setUserId(CommonUtils.getUUId32());
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
        String cookieMinister = new StringBuilder(COOKIE_MINISTER).append("=").append("null").append(";Path=/;Max-Age=43200").toString();

        response.addHeader("Set-Cookie", cookieUserId);
        response.addHeader("Set-Cookie", cookieIdentity);
        response.addHeader("Set-Cookie", cookieName);
        response.addHeader("Set-Cookie", cookieTime);
        response.addHeader("Set-Cookie",cookieMinister);
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
    public PageInfo<User> userList(UserListDto userListDto) {
        PageHelper.startPage(userListDto.getPageNum(), 10);
        try {
            List<User> users = iUserDao.selectUserList(userListDto);
            PageInfo<User> pageInfo = new PageInfo<>(users);
            return pageInfo;
        } catch (Exception e) {
            log.error("用户列表查询失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public int updateUser(User user) {
        try {
            int result = iUserDao.updateUser(user);
            return result;
        } catch (Exception e) {
            log.error("更新用户信息失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public int deleteUser(UserListDto userListDto) {
        try {
            int result = iUserDao.deleteUser(userListDto);
            return result;
        } catch (Exception e) {
            log.error("用户删除失败");
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
            int minister = minister(user.getUserName());
            if (!optionalUser.isPresent()) {
                log.error("获得的用户为空");
            } else {
                Date now = new Date();
                String cookieUserId = new StringBuilder(COOKIE_USERID).append("=").append(user.getUserId()).append(";Path=/;Max-Age=43200").toString();
                String cookieIdentity = new StringBuilder(COOKIE_IDENTITY).append("=").append(identity).append(";Path=/;Max-Age=43200").toString();
                String cookieName = new StringBuilder(COOKIE_NAME).append("=").append(URLEncoder.encode(user.getUserName(), "UTF-8")).append(";Path=/;Max-Age=43200").toString();
                String cookieMinister = new StringBuilder(COOKIE_MINISTER).append("=").append(minister).append(";Path=/;Max-Age=43200").toString();
                String cookieTime = new StringBuilder(COOKIE_TIME).append("=").append(now).append(";Path=/;Max-Age=43200").toString();

                response.addHeader("Set-Cookie", cookieUserId);
                response.addHeader("Set-Cookie", cookieIdentity);
                response.addHeader("Set-Cookie", cookieName);
                response.addHeader("Set-Cookie", cookieMinister);
                response.addHeader("Set-Cookie", cookieTime);
                log.info("cookie设置信息：platform_userId：{} platform_identity：{} platform_name：{} platform_isMinister:{} platform_time：{} ", user.getUserId(), identity, user.getUserName(), minister, now);
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

    private int minister(String userName) {
        User user = iUserDao.minister(userName);
        Optional<User> optionalUser = Optional.fromNullable(user);
        if (optionalUser.isPresent()) {
            return 1;
        }
        return 0;
    }


}
