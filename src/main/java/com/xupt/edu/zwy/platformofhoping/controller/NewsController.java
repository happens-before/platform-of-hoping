package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.CheckArgumentUtil;
import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.CommentReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsAddReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.dto.PageInfo;
import com.xupt.edu.zwy.platformofhoping.dto.ReplyReq;
import com.xupt.edu.zwy.platformofhoping.model.News;
import com.xupt.edu.zwy.platformofhoping.model.Reply;
import com.xupt.edu.zwy.platformofhoping.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午3:57
 */
@Slf4j
@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    private INewsService newsService;

    @GetMapping("/home")
    public CommonJsonResult<List<NewsHomeDto>> newsHome() {
        log.info("into /news/home");
        //todo 验证本人身份

        //从数据库取数据
        List<NewsHomeDto> newsHome = newsService.getNewsHome();

        log.info("out /news/home");
        return CommonJsonResult.success(newsHome);
    }

    @GetMapping("/info")
    public CommonJsonResult<NewsInfoDto> newsInfo(@RequestParam("newsId") String newsId) {
        log.info("into /news/info,newsId:{}", newsId);
        //todo 验证本人身份

        //检验参数的合法性
        CheckArgumentUtil.checkNewsId(newsId);
        NewsInfoDto newsInfoById = newsService.getNewsInfoById(newsId);

        log.info("out /news/info");
        return CommonJsonResult.success(newsInfoById);

    }

    @GetMapping("/reply")
    public CommonJsonResult<List<Reply>> getReplyByCommentId(@RequestParam("commentId") String commentId) {
        log.info("into /news/reply,commentId:{}", commentId);
        //todo 验证本人身份

        //检验参数的合法性
        CheckArgumentUtil.checkCommentId(commentId);
        List<Reply> replies = newsService.getReplyByCommentId(commentId);
        log.info("out /news/reply");
        return CommonJsonResult.success(replies);
    }

    @GetMapping("/list")
    public CommonJsonResult<PageInfo<News>> newsList(NewsListReq newsListReq) {
        log.info("into /news/list,newsListReq:{}", newsListReq);
        //todo 身份验证 管理员，学生会部长以及组织可以发布新闻并能查看所有的新闻信息,而以学生身份登陆只能查看最新50条新闻
        //todo  验证参数信息
        CheckArgumentUtil.checkNewsListReq(newsListReq);

        //todo 若没有搜索条件，则找到最新的50条新闻记录，若有搜索条件则根据搜索条件进行查询新闻记录
        PageInfo<News> newsList = newsService.getNewsList(newsListReq);
        log.info("out /news/list");
        return CommonJsonResult.success(newsList);
    }

    @PostMapping("/add")
    public CommonJsonResult addNews(NewsAddReq newsAddReq) {
        log.info("into /news/add,newsAddReq:{}", newsAddReq);
        //todo 检查参数合法性

        //todo 验证身份

        int result = newsService.addNews(newsAddReq);
        log.info("out /news/add,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/update")
    public CommonJsonResult updateNews(NewsAddReq newsAddReq) {
        log.info("into /news/update,newsAddReq:{}", newsAddReq);
        //todo 检查参数合法性

        //todo 验证身份

        int result = newsService.updateNews(newsAddReq);
        log.info("out /news/update,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/publish")
    public CommonJsonResult publishNews(NewsAddReq newsAddReq) {
        log.info("into /news/publish,newsAddReq:{}", newsAddReq);
        //todo 检查参数合法性

        //todo 验证身份

        int result = newsService.publishNews(newsAddReq);
        log.info("out /news/publish,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/delete")
    public CommonJsonResult deleteNews(NewsDto newsDto) {
        log.info("into /news/delete,newsDto:{}", newsDto);
        //todo 检查参数合法性

        //todo 验证身份

        int result = newsService.deleteNews(newsDto);
        log.info("out /news/delete,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/addComment")
    public CommonJsonResult addComment(CommentReq commentReq) {
        log.info("into /news/addComment,commentReq:{}", commentReq);
        int result = newsService.addComment(commentReq);
        log.info("out /news/addComment,result:{}", result);
        return CommonJsonResult.success();
    }

    @PostMapping("/addReply")
    public CommonJsonResult addReply(ReplyReq replyReq) {
        log.info("into /news/addReply,replyReq:{}", replyReq);
        int result = newsService.addReply(replyReq);
        log.info("out /news/addReply,result:{}", result);
        return CommonJsonResult.success();
    }
}

