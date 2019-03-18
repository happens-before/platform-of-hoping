package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
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
    private Integer valid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endTime;
    private Date createTime;
    private Date updateTime;
}
