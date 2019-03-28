package com.xupt.edu.zwy.platformofhoping.dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyuezhao
 */
@Mapper
public interface IOrganizerDao {
    Organizer isRightOrganizer(OrganizerReq organizerReq);

    int addOrganizer(Organizer organizer);

    Organizer isOrganizer(@Param("organizerName") String organizerName);

    Organizer selectOrganizerById(OrganizerReq organizerReq);

    List<Organizer> selectOrganzierList(OrganizerReq organizerReq);

    int deleteOrganizer(OrganizerReq organizerReq);

    int updateOrganizer(Organizer organizer);
}
