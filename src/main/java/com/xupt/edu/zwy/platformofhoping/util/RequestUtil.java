package com.xupt.edu.zwy.platformofhoping.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:19
 */
@Slf4j
public class RequestUtil {

    public static final String COOKIE_USERID = "platform_userId";

    public static final String COOKIE_IDENTITY = "platform_identity";

    public static final String COOKIE_NAME = "platform_name";

    public static final String COOKIE_TIME = "platform_time";

    /**
     * 获取当前请求的request
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request;
    }

    /**
     * 获取当前登录用户Id
     */
    public static String getLoginUserId() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            if (request.getAttribute(COOKIE_USERID) == null) {
                request.setAttribute(COOKIE_USERID, getCookieValue(request, COOKIE_USERID));
            }
            return (String) request.getAttribute(COOKIE_USERID);
        }
        return null;
    }


    /**
     * 获取当前登录用户中文名称
     */
    public static String getLoginUserName() {
        HttpServletRequest request = getRequest();

        if (request != null) {
            if (request.getAttribute(COOKIE_NAME) == null) {
                request.setAttribute(COOKIE_NAME, getCookieValue(request, COOKIE_NAME));
            }

            try {
                return URLDecoder.decode((String) request.getAttribute(COOKIE_NAME), "utf-8");
            } catch (UnsupportedEncodingException e) {
                log.info("名称解码失败{}", e.getMessage());
            }

        }
        return null;
    }

    /**
     * 由request获取当前登录用户的角色
     *
     * @result:
     */
    public static String getLoginUserStringIdentity() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            if (request.getAttribute(COOKIE_IDENTITY) == null) {
                request.setAttribute(COOKIE_IDENTITY, getCookieValue(request, COOKIE_IDENTITY));
            }
            return (String) request.getAttribute(COOKIE_IDENTITY);
        }
        return null;
    }


    /**
     * 根据cookie名称获取内容
     */
    private static String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    /**
     * 由request获取当前登录用户的time
     */
    public static String getTimeByRequest() {
        HttpServletRequest request = getRequest();
        if (request != null) {
            return getCookieValue(request, COOKIE_TIME);
        }
        return null;
    }

}
