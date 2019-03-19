package com.xupt.edu.zwy.platformofhoping.common;

import com.xupt.edu.zwy.platformofhoping.dto.ActivityListReq;
import com.xupt.edu.zwy.platformofhoping.dto.NewsListReq;
import com.xupt.edu.zwy.platformofhoping.enums.NewsStatusEnum;
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
     *
     * @Param newsId
     */
    public static void checkNewsId(String newsId) throws BusinessException {
        if (newsId.length() != 32 || StringUtils.isBlank(newsId)) {
            throw new BusinessException(ILLEGAL_ARGUMENT, "新闻编号不合法，请检查");
        }
    }

    /**
     * 验证commentId的合法性
     *
     * @Param commentId
     */
    public static void checkCommentId(String commentId) throws BusinessException {
        if (commentId.length() != 32 || StringUtils.isBlank(commentId)) {
            throw new BusinessException(ILLEGAL_ARGUMENT, "评论编号不合法，请检查");
        }
    }


    public static void checkActivityListReq(ActivityListReq activityListReq) throws BusinessException{

    }
}
