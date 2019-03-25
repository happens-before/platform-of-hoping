package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-25
 * @Time 下午3:31
 */
@Data
public class UserListDto {
    private Integer pageNum;
    private String userId;
    private String school;
    private String userSno;
    private String community;
    private String userName;
    private String email;
    private String phone;
}
