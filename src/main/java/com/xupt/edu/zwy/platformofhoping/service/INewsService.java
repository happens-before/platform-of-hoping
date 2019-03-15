package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.model.Reply;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午2:01
 */
public interface INewsService {
    List<NewsHomeDto> getNewsHome();

    NewsInfoDto getNewsInfoById(String newsId);

    List<Reply> getReplyByCommentId(String commentId);

    List<News> getNewsList(NewsListReq newsListReq);
}
