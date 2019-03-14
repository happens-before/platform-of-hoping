package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:29
 */
@Data
public class Picture {
    private Integer id;
    private String pictureId;
    private String newsId;
    private String picturePath;
    private Date createTime;
    private Date updateTime;
}
