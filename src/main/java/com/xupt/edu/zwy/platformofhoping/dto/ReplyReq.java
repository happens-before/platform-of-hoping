package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午8:35
 */
@Data
public class ReplyReq {
    private String replyId;
    private String commentId;
    private String replier;
    private String replyContent;
}
