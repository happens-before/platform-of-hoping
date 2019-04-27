package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IActivityDao;
import com.xupt.edu.zwy.platformofhoping.dao.IVolunteerDao;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.VolunteerReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Volunteer;
import com.xupt.edu.zwy.platformofhoping.service.IVolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-27
 * @Time 下午9:00
 */
@Service
@Slf4j
public class VolunteerServiceImpl implements IVolunteerService {
    @Resource
    private IVolunteerDao iVolunteerDao;
    @Resource
    private IActivityDao iActivityDao;

    @Override
    public int addVolunteer(VolunteerReq volunteerReq) {
        try {
            if (iVolunteerDao.addVolunteer(volunteerReq) == 1 && iActivityDao.addActivityJoin(volunteerReq.getActivityId()) == 1) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            log.error("志愿者添加失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public PageInfo<Volunteer> selectMyVolunteerList(VolunteerReq volunteerReq) {
        PageHelper.startPage(volunteerReq.getPageNum(), 10);
        try {
            List<Volunteer> volunteerList = iVolunteerDao.getMyAllVolunteerList(volunteerReq);
            PageInfo<Volunteer> pageInfo = new PageInfo<>(volunteerList);
            return pageInfo;
        } catch (Exception e) {
            log.error("志愿者列表获取失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteVolunteer(VolunteerReq volunteerReq) {
        try {
            if (iVolunteerDao.deleteVolunteer(volunteerReq.getVolunteerId()) == 1 && iActivityDao.cancelActivityJoin(volunteerReq.getActivityId()) == 1) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            log.error("志愿者删除失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

}
