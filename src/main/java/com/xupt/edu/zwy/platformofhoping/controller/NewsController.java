package com.xupt.edu.zwy.platformofhoping.controller;

import com.xupt.edu.zwy.platformofhoping.common.CheckArgumentUtil;
import com.xupt.edu.zwy.platformofhoping.common.CommonJsonResult;
import com.xupt.edu.zwy.platformofhoping.dto.NewsHomeDto;
import com.xupt.edu.zwy.platformofhoping.dto.NewsInfoDto;
import com.xupt.edu.zwy.platformofhoping.service.INewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}

