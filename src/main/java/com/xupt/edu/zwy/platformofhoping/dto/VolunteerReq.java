package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-27
 * @Time 下午9:08
 */
@Data
public class VolunteerReq {
    private int pageNum;
    private String volunteerId;
    private String userName;
    private String activityId;
    private String activityTopic;
}
