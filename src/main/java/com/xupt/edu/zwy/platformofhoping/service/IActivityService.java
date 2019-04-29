package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.ActivityDetailInfo;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.model.Activity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


/**
 * @author wanyuezhao
 */
public interface IActivityService {
    int addActivity(MultipartFile file, ActivityReq activityReq, HttpServletRequest request);

    ActivityDetailInfo getActivityDetail(String activityId);

    int updateActivity(MultipartFile file, ActivityReq activityReq, HttpServletRequest request);

    int deleteActivity(String activityId);

    int publishActivity(ActivityReq activityReq);

    int endActivity(ActivityReq activityReq);

    PageInfo<Activity> selectActivityList(ActivityListReq activityListReq);

    int approvalActivity(ActivityReq activityReq);

    int refuseActivity(ActivityReq activityReq);

    int summaryActivity(MultipartFile file, ActivityReq activityReq, HttpServletRequest request);

}
