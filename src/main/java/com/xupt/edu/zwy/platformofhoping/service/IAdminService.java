package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.AdminReq;

public interface IAdminService {

    int deleteAdmin(AdminReq adminReq);

    int addAdmin(AdminReq adminReq);
}
