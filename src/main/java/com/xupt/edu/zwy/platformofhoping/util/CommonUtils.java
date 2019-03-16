package com.xupt.edu.zwy.platformofhoping.util;

import java.util.UUID;

/**
 * @Descriptio 常用工具类
 * @Author wziyuan.wang
 * @Date 19-1-9
 * @Time 上午11:10
 */
public class CommonUtils {

    /**
     * 获取生成UUId
     *
     * @return
     */
    public static String getUUId32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

}
