package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午9:34
 */
@Data
public class UserLoginReq {
    private String school;
    private String userSno;
    private String password;
}
