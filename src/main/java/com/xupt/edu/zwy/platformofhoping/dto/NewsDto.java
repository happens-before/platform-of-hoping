package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.sql.Date;


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
    private String newsName;
    private Date updateTime;
}
