package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;


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
    private Integer valid;
    private Integer activityApproval;
    private Integer peopleTotal;
    private Integer peopleJoin;
    private String contentFile;
    private String summaryFile;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private Date updateTime;
}
