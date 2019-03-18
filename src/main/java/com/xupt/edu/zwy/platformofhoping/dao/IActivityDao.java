package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.ActivityReq;
import com.xupt.edu.zwy.platformofhoping.model.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyuezhao
 */
@Mapper
public interface IActivityDao {
    int addActivity(ActivityReq activityReq);

    Activity selectActivityById(@Param("activityId") String activityId);

    int updateActivity(ActivityReq activityReq);

    int deleteActivity(@Param("activityId")String activityId);

    List<Activity> selectActivityList(ActivityListReq activityListReq);
}
