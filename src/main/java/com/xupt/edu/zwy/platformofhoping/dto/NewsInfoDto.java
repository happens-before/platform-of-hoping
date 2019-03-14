package com.xupt.edu.zwy.platformofhoping.dto;

import com.xupt.edu.zwy.platformofhoping.model.News;
import lombok.Data;

import java.util.Date;

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
    private Date publishedTime;

    public void setNewsInfoDto(News news) {
        this.setNewsId(news.getNewsId());
        this.setNewsName(news.getNewsName());
        this.setNewsDescription(news.getNewsDescription());
        this.setNewsContent(news.getNewsContent());
        this.setNewsCount(news.getNewsCount());
        this.setNewsCreator(news.getNewsCreator());
        this.setPublishedTime(news.getPublishedTime());
    }
}
