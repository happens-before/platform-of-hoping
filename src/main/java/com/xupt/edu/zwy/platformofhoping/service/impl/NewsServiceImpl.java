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
import com.xupt.edu.zwy.platformofhoping.model.Picture;
import com.xupt.edu.zwy.platformofhoping.model.Reply;
import com.xupt.edu.zwy.platformofhoping.service.INewsService;
import com.xupt.edu.zwy.platformofhoping.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
            System.out.println(newsId);
            News news = iNewsDao.selectNewsInfoById(newsId);
            iNewsDao.addCountNews(newsId);
            List<Comment> comments = iCommentDao.selectCommentByNewsId(newsId);
            List<Picture> pictures = iPictureDao.selectPictureByNewsId(newsId);
            newsInfoDto.setNewsInfoDto(news, comments, pictures);
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
    public int addNews(MultipartFile file, NewsAddReq newsAddReq, HttpServletRequest request) {
        try {
            if (file == null) {
                newsAddReq.setNewsId(CommonUtils.getUUId32());
                System.out.println(newsAddReq.getNewsId());
                return iNewsDao.addNews(newsAddReq);
            }
            newsAddReq.setNewsId(CommonUtils.getUUId32());
            System.out.println(newsAddReq.getNewsId());
            newsAddReq.setPictureId(CommonUtils.getUUId32());
            newsAddReq.setPicturePath(getUploadPicturePath(file,request));
            if (iNewsDao.addNews(newsAddReq) == 1 && iPictureDao.addPicture(newsAddReq) == 1) {
                return 1;
            }
            return 0;
        } catch (Exception e) {
            log.error("新闻添加失败，请重试");
            throw new BusinessException(ReturnCodes.FAILD, "服务器很忙");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateNews(MultipartFile file, NewsAddReq newsAddReq,HttpServletRequest request) {
        try {
            if (file == null) {
                return iNewsDao.updateNews(newsAddReq);
            }
            int deletePictureById = iPictureDao.deletePictureById(newsAddReq.getNewsId());

            System.out.println(deletePictureById);
            newsAddReq.setPictureId(CommonUtils.getUUId32());
            newsAddReq.setPicturePath(getUploadPicturePath(file,request));
            if (iPictureDao.addPicture(newsAddReq) == 1 && iNewsDao.updateNews(newsAddReq) == 1) {
                return 1;
            }
            return 0;
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

//    @Override
//    public int updatePicture(MultipartFile file, NewsAddReq newsAddReq) throws IOException {
//        newsAddReq.setPicturePath(getUploadPicturePath(file));
//        return iPictureDao.updatePicture(newsAddReq);
//    }

    private String getUploadPicturePath(MultipartFile file,HttpServletRequest request) throws IOException {
        System.out.println(file.getName());
        //todo 验证身份
        //上传文件
//        String path = "/home/wanyuezhao/spring/bishe/platform-of-hoping-fe/pictures/";
//        String path = request.getServletContext().getRealPath("/upload/newsPictures/");
        String path="/home/wanyuezhao/spring/bishe/platform-of-hoping-fe/upload/";
        System.out.println(path);
        //创建文件
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        //zhao.jpg
        String img = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
        //根据 dir 抽象路径名和 img 路径名字符串创建一个新 File 实例。
        FileOutputStream imgOut = new FileOutputStream(new File(dir, img));
        //返回一个字节数组文件的内容
        imgOut.write(file.getBytes());
        imgOut.close();
        String picturePath = request.getHeader("Origin") + "/platform-of-hoping-fe/upload/"+img;
        return picturePath;
    }
}
