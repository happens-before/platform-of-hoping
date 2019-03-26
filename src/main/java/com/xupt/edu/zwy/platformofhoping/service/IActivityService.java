package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.model.Activity;

import java.util.List;

public interface IActivityService {
    int addActivity(ActivityReq activityReq);

    Activity getActivityDetail(String activityId);

    int updateActivity(ActivityReq activityReq);

    int deleteActivity(String activityId);

    int publishActivity(ActivityReq activityReq);

    int endActivity(ActivityReq activityReq);

    PageInfo<Activity> selectActivityList(ActivityListReq activityListReq);

    int approvalActivity(ActivityReq activityReq);

    int refuseActivity(ActivityReq activityReq);

}
