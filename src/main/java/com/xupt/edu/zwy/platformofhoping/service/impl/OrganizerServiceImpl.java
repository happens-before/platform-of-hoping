package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IOrganizerDao;
import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import com.xupt.edu.zwy.platformofhoping.service.IOrganizerService;
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
 * @Date 19-3-17
 * @Time 上午8:18
 */
@Service
@Slf4j
public class OrganizerServiceImpl implements IOrganizerService {
    @Resource
    private IOrganizerDao iOrganizerDao;

    @Override
    public boolean isRightOrganizer(OrganizerReq organizerReq) {
        Organizer organizer = iOrganizerDao.isRightOrganizer(organizerReq);
        if (StringUtils.isBlank(organizer.getOrganizerId())) {
            return false;
        }
        return true;
    }

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
}
