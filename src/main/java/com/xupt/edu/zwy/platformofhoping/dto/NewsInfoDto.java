package com.xupt.edu.zwy.platformofhoping.dto;

import com.xupt.edu.zwy.platformofhoping.model.Comment;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.model.Picture;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午4:40
 */
@Data
public class NewsInfoDto {
    private String newsId;
    private String newsName;
    private String newsDescription;
    private String newsContent;
    private String newsCreator;
    private Integer newsCount;
    private Date updateTime;
    private List<Picture> pictures;
    private List<CommentReply> commentReplies;

    public void setNewsInfoDto(News news, List<CommentReply> commentReplies,List<Picture> pictures) {
        this.setNewsId(news.getNewsId());
        this.setNewsName(news.getNewsName());
        this.setUpdateTime(news.getUpdateTime());
        this.setNewsDescription(news.getNewsDescription());
        this.setNewsContent(news.getNewsContent());
        this.setNewsCount(news.getNewsCount());
        this.setNewsCreator(news.getNewsCreator());
        this.setCommentReplies(commentReplies);
        this.setPictures(pictures);
    }
}
