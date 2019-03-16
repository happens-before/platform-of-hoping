package com.xupt.edu.zwy.platformofhoping.dao;

import com.xupt.edu.zwy.platformofhoping.dto.NewsAddReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.model.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wanyuezhao
 */
@Mapper
public interface INewsDao {
    List<NewsDto> selectLastFiveNewsId();

    News selectNewsInfoById(@Param("newsId") String newsId);

    List<News> selectNewsList(NewsListReq newsListReq);

    int addNews(NewsAddReq newsAddReq);

    int updateNews(NewsAddReq newsAddReq);
}
