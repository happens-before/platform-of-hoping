package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IAdminDao;
import com.xupt.edu.zwy.platformofhoping.dao.IUserDao;
import com.xupt.edu.zwy.platformofhoping.dto.AdminReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Admin;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.service.IAdminService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:19
 */
@Service
@Slf4j
public class AdminServiceImpl implements IAdminService {
    @Resource
    private IAdminDao iAdminDao;
    @Resource
    private IUserDao iUserDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAdmin(AdminReq adminReq) {
        try {
            int result = iAdminDao.deleteAdmin(adminReq);
            return result;
        } catch (Exception e) {
            log.error("管理员删除失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addAdmin(AdminReq adminReq) {
        if (iUserDao.isUser(adminReq.getAdminName()) == null) {
            throw new BusinessException(ReturnCodes.FAILD, "该用户不是注册用户，不能被添加为管理员");
        }
        if (isAdmin(adminReq.getAdminName())) {
            throw new BusinessException(ReturnCodes.NULL_POINTER, "该用户是管理员，不能重复添加");
        }
        try {
            if (isEverAdmin(adminReq)) {
                int result = iAdminDao.addAdminWithSign(adminReq);
                return result;
            } else {
                adminReq.setAdminId(CommonUtils.getUUId32());
                int result = iAdminDao.addAdminWithNoSign(adminReq);
                return result;
            }
        } catch (Exception e) {
            log.error("管理员添加失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public PageInfo<Admin> selectAdminList(AdminReq adminReq) {
        PageHelper.startPage(adminReq.getPageNum(), 10);
        try
        {
            List<Admin> admins = iAdminDao.selectAdminList(adminReq);
            PageInfo<Admin> pageInfo= new PageInfo<>(admins);
            return pageInfo;
        }catch (Exception e) {
            log.error("查询管理员列表失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    private boolean isAdmin(String adminName) {
        try {
            Admin admin = iAdminDao.isAdmin(adminName);
            if (admin == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("查询是否是管理员失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }

    }

    private boolean isEverAdmin(AdminReq adminReq) {
        try {
            Admin everAdmin = iAdminDao.isEverAdmin(adminReq);
            if (everAdmin == null) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("查询曾经是否是管理员失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }
}

