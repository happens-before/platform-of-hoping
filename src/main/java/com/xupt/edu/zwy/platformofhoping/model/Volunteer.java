package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-27
 * @Time 下午1:47
 */
@Data
public class Volunteer {
    private Integer id;
    private String volunteerId;
    private String userName;
    private String activityId;
    private String activityTopic;
    private Integer valid;
    private Integer volunteerStatus;
    private Date createTime;
    private Date updateTime;
}
