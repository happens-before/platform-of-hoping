package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.IActivityDao;
import com.xupt.edu.zwy.platformofhoping.dao.IAdminDao;
import com.xupt.edu.zwy.platformofhoping.dao.IOrganizerDao;
import com.xupt.edu.zwy.platformofhoping.dao.IUserDao;
import com.xupt.edu.zwy.platformofhoping.dao.IVolunteerDao;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityDetailInfo;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.VolunteerReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Activity;
import com.xupt.edu.zwy.platformofhoping.model.Volunteer;
import com.xupt.edu.zwy.platformofhoping.service.IActivityService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import com.xupt.edu.zwy.platformofhoping.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    @Resource
    private IVolunteerDao iVolunteerDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addActivity(MultipartFile file, ActivityReq activityReq, HttpServletRequest request) {
        try {
            activityReq.setActivityId(CommonUtils.getUUId32());
            System.out.println("@@@@@@@"+activityReq);
            if (file == null) {
                return iActivityDao.addActivity(activityReq);
            }
            String path = getPathOfFile(file, request);
            activityReq.setContentFile(path);
            System.out.println("%%%%%%%"+activityReq);
            return iActivityDao.addActivity(activityReq);

        } catch (Exception e) {
            log.error("活动添加失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateActivity(MultipartFile file, ActivityReq activityReq, HttpServletRequest request) {
        try {
            if (file == null) {
                return iActivityDao.updateActivity(activityReq);
            }
            String path = getPathOfFile(file, request);
            activityReq.setContentFile(path);
            return iActivityDao.updateActivity(activityReq);
        } catch (Exception e) {
            log.error("更新活动内容失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int summaryActivity(MultipartFile file, ActivityReq activityReq, HttpServletRequest request) {
        try {
            if (file == null) {
                return iActivityDao.updateActivity(activityReq);
            }
            String path = getPathOfFile(file, request);
            activityReq.setSummaryFile(path);
            return iActivityDao.updateActivity(activityReq);
        } catch (Exception e) {
            log.error("更新活动总结失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public ActivityDetailInfo getActivityDetail(String activityId) {
        try {
            ActivityDetailInfo activityDetailInfo=new ActivityDetailInfo();
            Activity activity = iActivityDao.selectActivityById(activityId);
            VolunteerReq volunteerReq=new VolunteerReq();
            volunteerReq.setActivityId(activityId);
            List<Volunteer> volunteers=iVolunteerDao.getMyAllVolunteerList(volunteerReq);
            List<String> userNames=new ArrayList<>();
            for(Volunteer volunteer:volunteers){
                userNames.add(volunteer.getUserName());
            }
            activityDetailInfo.setActivityDetailInfo(activity,userNames);
            return activityDetailInfo;
        } catch (Exception e) {
            log.error("活动细节查询失败，请重试");
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
            if (iActivityDao.updateActivity(activityReq) == 1 && iVolunteerDao.overdueVolunteer(activityReq.getActivityId()) == 1) {
                return 1;
            }
            return 0;
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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
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

    private String getPathOfFile(MultipartFile file, HttpServletRequest request) throws IOException {
        //todo 验证身份
        String path = "/home/wanyuezhao/spring/bishe/platform-of-hoping-fe/activity/content/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String suffix = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        FileOutputStream imgOut = new FileOutputStream(new File(dir, suffix));
        imgOut.write(file.getBytes());
        imgOut.close();
        String picturePath = request.getHeader("Origin") + "/platform-of-hoping-fe/activity/content/" + suffix;
        return picturePath;
    }

}
