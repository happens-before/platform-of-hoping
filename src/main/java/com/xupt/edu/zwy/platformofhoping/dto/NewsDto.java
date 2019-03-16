package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午2:23
 */
@Data
public class NewsDto {
    private String newsId;
    private Integer status;
    private Date publishedTime;
}
