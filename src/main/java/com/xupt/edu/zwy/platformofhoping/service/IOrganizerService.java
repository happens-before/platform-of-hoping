package com.xupt.edu.zwy.platformofhoping.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.UserLoginReq;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IOrganizerService {

    int registerOrganizer(Organizer organizer);

    boolean isRightInfo(OrganizerReq organizerReq, HttpServletResponse response);

    Organizer selectOrganizerById(OrganizerReq organizerReq);

    PageInfo<Organizer> selectOragnizerList(OrganizerReq organizerReq);

    int deleteOrganizer(OrganizerReq organizerReq);

    int updateOrganizer(Organizer organizer);

}
