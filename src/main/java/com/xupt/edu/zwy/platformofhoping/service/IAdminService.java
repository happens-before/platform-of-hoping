package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.AdminReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.model.Admin;

import java.util.List;

public interface IAdminService {

    int deleteAdmin(AdminReq adminReq);

    int addAdmin(AdminReq adminReq);

    PageInfo<Admin> selectAdminList(AdminReq adminReq);
}
