package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午8:05
 */
@Data
public class CommentReq {
    private String commentId;
    private String newsId;
    private String reviewer;
    private String commentContent;
}
