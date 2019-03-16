package com.xupt.edu.zwy.platformofhoping.util;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午2:02
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
