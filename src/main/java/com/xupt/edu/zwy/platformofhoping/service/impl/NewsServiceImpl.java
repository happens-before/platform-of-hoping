package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.edu.zwy.platformofhoping.dao.ICommentDao;
import com.xupt.edu.zwy.platformofhoping.dao.INewsDao;
import com.xupt.edu.zwy.platformofhoping.dao.IPictureDao;
import com.xupt.edu.zwy.platformofhoping.dao.IReplyDao;
import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.model.Comment;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.model.Reply;
import com.xupt.edu.zwy.platformofhoping.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午2:02
 */
@Slf4j
@Service
public class NewsServiceImpl implements INewsService {
    @Resource
    private INewsDao iNewsDao;
    @Resource
    private IPictureDao iPictureDao;
    @Resource
    private ICommentDao iCommentDao;
    @Resource
    private IReplyDao iReplyDao;

    @Override
    public List<NewsHomeDto> getNewsHome() {
        List<NewsDto> newsDtos = iNewsDao.selectLastFiveNewsId();
        log.info("newsId为:{}", newsDtos);

        List<NewsHomeDto> newsHomeInfo = iPictureDao.getNewsHomeInfo(newsDtos);
        log.info("homeInfo为:{}", newsHomeInfo);

        return newsHomeInfo;
    }

    @Override
    public NewsInfoDto getNewsInfoById(String newsId) {
        NewsInfoDto newsInfoDto = new NewsInfoDto();

        News news = iNewsDao.selectNewsInfoById(newsId);
        List<Comment> comments = iCommentDao.selectCommentByNewsId(newsId);
        newsInfoDto.setNewsInfoDto(news, comments);

        log.info("newsInfo为: {}", newsInfoDto);
        return newsInfoDto;
    }

    @Override
    public List<Reply> getReplyByCommentId(String commentId) {
        List<Reply> replies = iReplyDao.selectReplyByCommentId(commentId);

        return replies;
    }

    @Override
    public List<News> getNewsList(NewsListReq newsListReq) {
        List<News> news = iNewsDao.selectNewsList(newsListReq);
        return news;
    }

}
