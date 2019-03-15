package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午7:48
 */
@Data
public class NewsListReq {
    /**
     * 是否查询所有
     * findAll==0,则表示查询50条记录,findAll==1,则表示查询所有记录，相当于管理员查询
     */
    private int findAll;
    /**
     * 当前的页码
     */
    private Integer pageNum;
    /**
     * 搜索条件：新闻名称
     */
    private String newsName;
    /**
     * 搜索条件：新闻状态
     */
    private Integer status;
    /**
     * 搜索条件：用户姓名
     */
    private String newsCreator;
}
