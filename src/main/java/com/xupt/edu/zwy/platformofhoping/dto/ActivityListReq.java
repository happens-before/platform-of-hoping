package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-18
 * @Time 下午3:30
 */
@Data
public class ActivityListReq {
    /**
     * 是否查询所有
     * findAll==0,则表示查询50条记录,findAll==1,则表示查询所有记录，相当于管理员查询
     */
    private int findAll;
}
