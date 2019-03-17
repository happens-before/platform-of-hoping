package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.OrganizerReq;
import com.xupt.edu.zwy.platformofhoping.model.Organizer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author wanyuezhao
 */
@Mapper
public interface IOrganizerDao {
    Organizer isRightOrganizer(OrganizerReq organizerReq);

    int addOrganizer(Organizer organizer);

    Organizer isOrganizer(@Param("organizerName") String organizerName);
}
