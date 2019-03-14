package com.xupt.edu.zwy.platformofhoping.common;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import static com.xupt.edu.zwy.platformofhoping.enums.ReturnCodes.ILLEGAL_ARGUMENT;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-3-14
 * @Time 下午3:57
 */
@Slf4j
public final class CheckArgumentUtil {

    private CheckArgumentUtil() {

    }

    /**
     * 验证newsId的合法性
     */
    public static void checkNewsId(String newsId) {
        if (newsId.length() != 32 || StringUtils.isBlank(newsId)) {
            throw new BusinessException(ILLEGAL_ARGUMENT, "新闻编号不合法，请检查");
        }
    }
}
