package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-16
 * @Time 下午3:46
 */
@Data
public class NewsAddReq {
    private String newsId;
    private String newsName;
    private String newsDescription;
    private String newsContent;
    private String newsCreator;
    private Integer status;
    private String pictureId;
    private String picturePath;
}
