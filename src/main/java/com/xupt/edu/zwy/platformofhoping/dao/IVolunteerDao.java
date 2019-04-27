package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.VolunteerReq;
import com.xupt.edu.zwy.platformofhoping.model.Volunteer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IVolunteerDao {
    int addVolunteer(VolunteerReq volunteerReq);

    int overdueVolunteer(@Param("activityId") String activityId);

    int deleteVolunteer(@Param("volunteerId") String volunteerId);

    List<Volunteer> getMyAllVolunteerList(VolunteerReq volunteerReq);
}
