package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:20
 */
@Data
public class Admin {
    private Integer id;
    private String adminId;
    private String adminName;
    private Boolean valid;
    private Date createTime;
    private Date updateTime;
}