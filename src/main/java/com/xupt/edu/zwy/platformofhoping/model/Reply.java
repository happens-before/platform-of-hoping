package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:34
 */
@Data
public class Reply {
    private Integer id;
    private String replyId;
    private String commentId;
    private String replier;
    private String replyContent;
    private Date createTime;
    private Date updateTime;
}
