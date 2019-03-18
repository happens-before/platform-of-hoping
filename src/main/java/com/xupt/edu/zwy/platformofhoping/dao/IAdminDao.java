package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.AdminReq;
import com.xupt.edu.zwy.platformofhoping.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyuezhao
 */
@Mapper
public interface IAdminDao {
    Admin isAdmin(@Param("adminName") String adminName);

    int addAdminWithNoSign(AdminReq adminReq);

    int addAdminWithSign(AdminReq adminReq);

    int deleteAdmin(AdminReq adminReq);

    Admin isEverAdmin(AdminReq adminReq);

    List<Admin> selectAdminList(AdminReq adminReq);
}
