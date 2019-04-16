package com.xupt.edu.zwy.platformofhoping.dto;

import lombok.Data;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-17
 * @Time 上午7:55
 */
@Data
public class AdminReq {
    /**
     * 当前的页码
     */
    private int pageNum;
    private String adminId;
    private String adminName;


}
