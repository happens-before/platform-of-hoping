package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IActivityDao;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Activity;
import com.xupt.edu.zwy.platformofhoping.service.IActivityService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
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
 * @Date 19-3-18
 * @Time 下午5:15
 */
@Service
@Slf4j
public class ActivityServiceImpl implements IActivityService {
    @Resource
    private IActivityDao iActivityDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addActivity(ActivityReq activityReq) {
        try {
            System.out.println(activityReq);
            activityReq.setActivityId(CommonUtils.getUUId32());
            System.out.println("****");
            int result = iActivityDao.addActivity(activityReq);
            return result;
        } catch (Exception e) {
            log.error("活动添加失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public Activity getActivityDetail(String activityId) {
        try {
            Activity activity = iActivityDao.selectActivityById(activityId);
            return activity;
        } catch (Exception e) {
            log.error("活动细节查询失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateActivity(ActivityReq activityReq) {
        try {
            int result = iActivityDao.updateActivity(activityReq);
            return result;
        } catch (Exception e) {
            log.error("更新活动内容失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteActivity(String activityId) {
        try {
            int result = iActivityDao.deleteActivity(activityId);
            return result;
        } catch (Exception e) {
            log.error("删除活动失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int publishActivity(ActivityReq activityReq) {
        try {
            activityReq.setActivityStatus(30);
            int result = iActivityDao.updateActivity(activityReq);
            return result;
        } catch (Exception e) {
            log.error("发布活动失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int endActivity(ActivityReq activityReq) {
        try {
            activityReq.setActivityStatus(10);
            int result = iActivityDao.updateActivity(activityReq);
            return result;
        } catch (Exception e) {
            log.error("终止活动失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public PageInfo<Activity> selectActivityList(ActivityListReq activityListReq) {
        PageHelper.startPage(activityListReq.getPageNum(), 10);
        try {
            List<Activity> activities = iActivityDao.selectActivityList(activityListReq);
            PageInfo<Activity> pageInfo = new PageInfo<>(activities);
            return pageInfo;
        } catch (Exception e) {
            log.error("查询活动列表失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public int approvalActivity(ActivityReq activityReq) {
        try {
            activityReq.setActivityApproval(30);
            int result = iActivityDao.updateActivity(activityReq);
            return result;
        } catch (Exception e) {
            log.error("活动通过审核失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public int refuseActivity(ActivityReq activityReq) {
        try {
            activityReq.setActivityApproval(10);
            int result = iActivityDao.updateActivity(activityReq);
            return result;
        } catch (Exception e) {
            log.error("活动审核拒绝失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

}
