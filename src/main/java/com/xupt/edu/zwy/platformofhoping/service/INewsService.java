package com.xupt.edu.zwy.platformofhoping.service;

import com.xupt.edu.zwy.platformofhoping.dto.CommentReq;
import com.xupt.edu.zwy.platformofhoping.dto.HomeInfo;
import com.xupt.edu.zwy.platformofhoping.dto.NewsAddReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.ReplyReq;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.model.Reply;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    HomeInfo getNewsHome();

    NewsInfoDto getNewsInfoById(String newsId);

    List<Reply> getReplyByCommentId(String commentId);

    List<NewsDto> getTenLastNews();

    PageInfo<News> getNewsList(NewsListReq newsListReq);

    int addNews(MultipartFile file, NewsAddReq newsAddReq, HttpServletRequest request);

    int updateNews(MultipartFile file,NewsAddReq newsAddReq,HttpServletRequest request);

    int publishNews(NewsAddReq newsAddReq);

    int deleteNews(NewsDto newsDto);

    int addComment(CommentReq commentReq);

    int addReply(ReplyReq replyReq);

    int pauseNews(NewsAddReq newsAddReq);

//    int updatePicture(MultipartFile file,NewsAddReq newsAddReq) throws IOException;
}
