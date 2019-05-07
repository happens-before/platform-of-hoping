package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.sql.Date;


/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:31
 */
@Data
public class Comment {
    private Integer id;
    private String commentId;
    private String newsId;
    private String reviewer;
    private String commentContent;
    private Date createTime;
    private Date updateTime;
}
