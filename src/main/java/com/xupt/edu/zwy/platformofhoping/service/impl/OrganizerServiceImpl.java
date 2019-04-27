package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Optional;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IAdminDao;
import com.xupt.edu.zwy.platformofhoping.dao.IOrganizerDao;
import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.enums.UserRoleEnum;
import com.xupt.edu.zwy.platformofhoping.model.Admin;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import com.xupt.edu.zwy.platformofhoping.service.IOrganizerService;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:18
 */
@Service
@Slf4j
public class OrganizerServiceImpl implements IOrganizerService {
    public static final String COOKIE_USERID = "platform_userId";

    public static final String COOKIE_IDENTITY = "platform_identity";

    public static final String COOKIE_NAME = "platform_name";

    public static final String COOKIE_TIME = "platform_time";

    @Resource
    private IOrganizerDao iOrganizerDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int registerOrganizer(Organizer organizer) {
        try {
            organizer.setOrganizerId(CommonUtils.getUUId32());
            System.out.println(organizer);
            int result = iOrganizerDao.addOrganizer(organizer);
            return result;
        } catch (Exception e) {
            log.error("组织添加失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public boolean isRightInfo(OrganizerReq organizerReq, HttpServletResponse response) {
        try {
            Organizer rightOrganizer = iOrganizerDao.isRightOrganizer(organizerReq);
            if (rightOrganizer == null) {
                return false;
            }
            setCookie(rightOrganizer, response);
            return true;
        } catch (Exception e) {
            log.error("组织登陆失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public Organizer selectOrganizerById(OrganizerReq organizerReq) {
        try{
            Organizer organizer = iOrganizerDao.selectOrganizerById(organizerReq);
            return organizer;
        }catch (Exception e){
            log.error("获取组织信息失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public PageInfo<Organizer> selectOragnizerList(OrganizerReq organizerReq) {
        PageHelper.startPage(organizerReq.getPageNum(), 10);
        try{
            List<Organizer> organizers = iOrganizerDao.selectOrganzierList(organizerReq);
            PageInfo<Organizer> pageInfo=new PageInfo<>(organizers);
            return pageInfo;
        }catch (Exception e){
            log.error("获取组织列表失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public int deleteOrganizer(OrganizerReq organizerReq) {
        try{
            return iOrganizerDao.deleteOrganizer(organizerReq);
        }catch (Exception e){
            log.error("删除组织失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public int updateOrganizer(Organizer organizer) {
        try{
          return iOrganizerDao.updateOrganizer(organizer);
        }catch (Exception e){
            log.error("更新组织信息失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    private boolean setCookie(Organizer organizer, HttpServletResponse response) {
        try {
            Optional<Organizer> optionalUser = Optional.fromNullable(organizer);
            int identity = getUserIdentityByName(organizer.getOrganizerName());
            if (!optionalUser.isPresent()) {
                log.error("获得的用户为空");
            } else {
                Date now = new Date();
                String cookieUserId = new StringBuilder(COOKIE_USERID).append("=").append(organizer.getOrganizerId()).append(";Path=/;Max-Age=43200").toString();
                String cookieIdentity = new StringBuilder(COOKIE_IDENTITY).append("=").append(identity).append(";Path=/;Max-Age=43200").toString();
                String cookieName = new StringBuilder(COOKIE_NAME).append("=").append(URLEncoder.encode(organizer.getOrganizerName(), "UTF-8")).append(";Path=/;Max-Age=43200").toString();
                String cookieTime = new StringBuilder(COOKIE_TIME).append("=").append(now).append(";Path=/;Max-Age=43200").toString();
                response.addHeader("Set-Cookie", cookieUserId);
                response.addHeader("Set-Cookie", cookieIdentity);
                response.addHeader("Set-Cookie", cookieName);
                response.addHeader("Set-Cookie", cookieTime);
                log.info("cookie设置信息：platform_userId：{} platform_identity：{} platform_name：{} platform_time：{} ", organizer.getOrganizerId(), identity, organizer.getOrganizerName(), now);
                return true;
            }
        } catch (BusinessException e) {
            log.error("登录异常 {}", e);
        } catch (UnsupportedEncodingException e) {
            log.error("organizername编码失败{} ", e);
        }
        return false;
    }

    private int getUserIdentityByName(String orgainzerName) {
        Organizer organizer = iOrganizerDao.isOrganizer(orgainzerName);
        Optional<Organizer> optionalOrganizer = Optional.fromNullable(organizer);
        if (optionalOrganizer.isPresent()) {
            return UserRoleEnum.ORGANIZER.getRoleFlag();
        }
        return 0;

    }
}
