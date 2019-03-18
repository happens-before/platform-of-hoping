package com.xupt.edu.zwy.platformofhoping.service.impl;

import com.github.pagehelper.PageHelper;
import com.xupt.edu.zwy.platformofhoping.common.BusinessException;
import com.xupt.edu.zwy.platformofhoping.dao.ICommentDao;
import com.xupt.edu.zwy.platformofhoping.dao.INewsDao;
import com.xupt.edu.zwy.platformofhoping.dao.IPictureDao;
import com.xupt.edu.zwy.platformofhoping.dao.IReplyDao;
import com.xupt.edu.zwy.platformofhoping.dto.CommentReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsAddReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.ReplyReq;
import com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes;
import com.xupt.edu.zwy.platformofhoping.model.Comment;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.model.Reply;
import com.xupt.edu.zwy.platformofhoping.service.INewsService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import com.xupt.edu.zwy.platformofhoping.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            List<NewsHomeDto> newsHomeInfo = iPictureDao.getNewsHomeInfo();
            return newsHomeInfo;
        } catch (Exception e) {
            log.error("查询主页图片失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public NewsInfoDto getNewsInfoById(String newsId) {
        try {
            NewsInfoDto newsInfoDto = new NewsInfoDto();
            News news = iNewsDao.selectNewsInfoById(newsId);
            List<Comment> comments = iCommentDao.selectCommentByNewsId(newsId);
            newsInfoDto.setNewsInfoDto(news, comments);
            return newsInfoDto;
        } catch (Exception e) {
            log.error("查询新闻详情失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }

    }

    @Override
    public List<Reply> getReplyByCommentId(String commentId) {
        try {
            List<Reply> replies = iReplyDao.selectReplyByCommentId(commentId);
            return replies;
        } catch (Exception e) {
            log.error("查询新闻追评信息失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }

    }

    @Override
    public List<NewsDto> getTenLastNews() {
        try {
            List<NewsDto> news = iNewsDao.selectLastTenNews();
            return news;
        } catch (Exception e) {
            log.error("查询最新10条新闻失败失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    public PageInfo<News> getNewsList(NewsListReq newsListReq) {
        PageHelper.startPage(newsListReq.getPageNum(), 10);
        try {
            List<News> news = iNewsDao.selectNewsList(newsListReq);
            PageInfo<News> pageInfo = new PageInfo<>(news);
            return pageInfo;
        } catch (Exception e) {
            log.error("新闻列表查询失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addNews(NewsAddReq newsAddReq) {
        try {
            newsAddReq.setNewsId(CommonUtils.getUUId32());
            if (newsAddReq.getPicturePath() != null) {
                newsAddReq.setPictureId(CommonUtils.getUUId32());
                if ((iNewsDao.addNews(newsAddReq)) == 1 && (iPictureDao.addPicture(newsAddReq)) == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return iNewsDao.addNews(newsAddReq);
        } catch (Exception e) {
            log.error("新闻添加失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNews(NewsAddReq newsAddReq) {
        try {
            if (newsAddReq.getPictureId() != null) {
                if ((iNewsDao.updateNews(newsAddReq)) == 1 && (iPictureDao.updatePicture(newsAddReq)) == 1) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return iNewsDao.updateNews(newsAddReq);
        } catch (Exception e) {
            log.error("新闻更新失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int publishNews(NewsAddReq newsAddReq) {
        try {
            newsAddReq.setStatus(30);
            int result = iNewsDao.updateNews(newsAddReq);
            return result;
        } catch (Exception e) {
            log.error("新闻发布失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteNews(NewsDto newsDto) {
        try {
            int result = iNewsDao.deleteNews(newsDto);
            return result;
        } catch (Exception e) {
            log.error("新闻删除失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addComment(CommentReq commentReq) {
        try {
            commentReq.setCommentId(CommonUtils.getUUId32());
            int result = iCommentDao.addComment(commentReq);
            return result;
        } catch (Exception e) {
            log.error("评论添加失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addReply(ReplyReq replyReq) {
        try {
            replyReq.setReplyId(CommonUtils.getUUId32());
            int result = iReplyDao.addReply(replyReq);
            return result;
        } catch (Exception e) {
            log.error("评论添加失败");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int pauseNews(NewsAddReq newsAddReq) {
        try {
            newsAddReq.setStatus(20);
            int result = iNewsDao.updateNews(newsAddReq);
            return result;
        } catch (Exception e) {
            log.error("新闻暂停失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }


}
