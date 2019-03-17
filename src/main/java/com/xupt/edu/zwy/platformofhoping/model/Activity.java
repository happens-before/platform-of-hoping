package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:47
 */
@Data
public class Activity {
    private Integer id;
    private String activityId;
    private String promoter;
    private String organizer;
    private String activityTopic;
    private String activityContent;
    private Integer activityStatus;
    private Boolean valid;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date updateTime;
}
