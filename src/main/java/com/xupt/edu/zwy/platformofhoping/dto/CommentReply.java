package com.xupt.edu.zwy.platformofhoping.dto;

import com.xupt.edu.zwy.platformofhoping.model.Comment;
import com.xupt.edu.zwy.platformofhoping.model.Reply;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-23
 * @Time 下午5:47
 */
@Data
public class CommentReply {
    private Comment comment;
    private List<Reply> replies;

    public void setCommentReply(Comment comment,List<Reply> replies){
        this.setComment(comment);
        this.setReplies(replies);
    }
}
