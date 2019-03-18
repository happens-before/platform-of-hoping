package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:42
 */
@Data
public class Organizer {
    private Integer id;
    private String organizerId;
    private String organizerCity;
    private String organizerName;
    private String organizerPhone;
    private String organizerprincipal;
    private String organizerDescription;
    private String organizerAddress;
    private Integer valid;
    private String password;
    private Date createTime;
    private Date updateTime;
}
