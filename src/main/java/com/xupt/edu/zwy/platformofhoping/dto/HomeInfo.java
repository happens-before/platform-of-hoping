package com.xupt.edu.zwy.platformofhoping.dto;

import com.xupt.edu.zwy.platformofhoping.model.Activity;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-22
 * @Time 下午6:52
 */
@Data
public class HomeInfo {
    private List<NewsHomeDto> newsHomeDto;
    private List<Activity> activity;

    public void setHomeInfo(List<NewsHomeDto> newsHomeDto, List<Activity> activity) {
        this.setNewsHomeDto(newsHomeDto);
        this.setActivity(activity);
    }
}
