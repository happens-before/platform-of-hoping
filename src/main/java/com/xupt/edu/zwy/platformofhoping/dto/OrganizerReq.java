package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午8:14
 */
@Data
public class OrganizerReq {
    private int  pageNum;
    private String organizerId;
    private String organizerCity;
    private String organizerName;
    private String password;
}
