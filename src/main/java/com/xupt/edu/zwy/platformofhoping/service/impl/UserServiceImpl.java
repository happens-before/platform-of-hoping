package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IUserDao;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.User;
import com.xupt.edu.zwy.platformofhoping.service.IUserService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    @Resource
    private IUserDao iUserDao;

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

    @Override
    public boolean isUser(UserLoginReq userLoginReq) {
        User user = iUserDao.isUser(userLoginReq);
        if (StringUtils.isBlank(user.getUserId())) {
            return false;
        }
        return true;
    }
}
