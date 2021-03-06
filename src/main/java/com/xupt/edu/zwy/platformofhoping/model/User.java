package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.sql.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:36
 */
@Data
public class User {
    private Integer id;
    private String userId;
    private String school;
    private String college;
    private String profession;
    private String userSno;
    private String community;
    private Integer minister;
    private String userName;
    private String password;
    private Integer valid;
    private String sex;
    private Integer age;
    private String email;
    private String phone;
    private Date createTime;
    private Date updateTime;
}
