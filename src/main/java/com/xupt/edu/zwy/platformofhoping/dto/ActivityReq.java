package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;


/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-18
 * @Time 下午8:38
 */
@Data
public class ActivityReq {
    private int findAll;
    private String activityId;
    private String promoter;
    private String organizer;
    private String activityTopic;
    private String activityContent;
    private Integer activityStatus;
    private Integer activityApproval;
    private String startTime;
    private String endTime;
    private Integer peopleTotal;
    private Integer peopleJoin;
    private String contentFile;
    private String summaryFile;
}
