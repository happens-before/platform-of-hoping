package com.xupt.edu.zwy.platformofhoping.dto;

import com.xupt.edu.zwy.platformofhoping.model.Activity;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * Description:
 *
 * @author wanyuezhao
 * @Date 19-4-29
 * @Time 下午12:52
 */
@Data
public class ActivityDetailInfo {
    private Activity activity;
    private List<String> userNames;

    public void setActivityDetailInfo(Activity activity, List<String> userNames) {
        this.setActivity(activity);
        this.setUserNames(userNames);
    }
}
