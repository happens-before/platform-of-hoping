package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.VolunteerReq;
import com.xupt.edu.zwy.platformofhoping.model.Volunteer;

import java.util.List;

public interface IVolunteerService {
    int addVolunteer(VolunteerReq volunteerReq);

    PageInfo<Volunteer> selectMyVolunteerList(VolunteerReq volunteerReq);

    int deleteVolunteer(VolunteerReq volunteerReq);

}
