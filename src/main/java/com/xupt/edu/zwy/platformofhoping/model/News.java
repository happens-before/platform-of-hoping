package com.xupt.edu.zwy.platformofhoping.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-9
 * @Time 下午8:20
 */
@Data
public class News {
    private Integer id;
    private String newsId;
    private String newsName;
    private String newsDescription;
    private String newsContent;
    private String newsCreator;
    private Integer status;
    private Integer newsCount;
    private Date publishedTime;
    private Date createTime;
    private Date updateTime;
}
