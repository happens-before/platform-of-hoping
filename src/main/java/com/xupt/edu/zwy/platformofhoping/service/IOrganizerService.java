package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;

import javax.servlet.http.HttpServletResponse;

public interface IOrganizerService {

    int registerOrganizer(Organizer organizer);


    boolean isRightInfo(OrganizerReq organizerReq, HttpServletResponse response);

}
